package tang.chinwe.lib_page.loading

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import tang.chinwe.lib_page.R

object LoadingManager : IInitLoading {

    private const val EXTRA_INIT_LOADING = "isInitLoading"

    //默认不开启
    var defaultIsLoading = false

    var defaultInitLoading = object : IInitLoading {
        override fun loadingInit(load: ILoad?, context: Context?) {
            if (load == null || context == null || context !is Activity || load.loading != null)
                return
            if (load is Fragment) {
                load.loading = (load.activity as? ILoad)?.loading
            }
            if (load.loading == null) {
                val waitDialog = DefaultWaitingDialog(context)
                waitDialog.setMessage(
                    context.getString(
                        load.loadMessage() ?: R.string.page_template_data_loading
                    )
                )
                waitDialog.setCancelable(load.cancelable() ?: false)
                load.loadViewId()?.let {
                    waitDialog.setContentView(it)
                }
                load.loading = waitDialog
            }
        }
    }

    override fun loadingInit(load: ILoad?, context: Context?) {
        if (load == null || context == null || context !is Activity || load.loading != null)
            return
        //一级控制，全局控制
        var isInitLoading = defaultIsLoading
        //二级控制，Class控制
        if (load is IIsInitLoading) {
            isInitLoading = load.initLoading()
        }
        /**
         * 三级控制，Object控制
         */
        isInitLoading = when (load) {
            is Activity -> {
                load.intent.getBooleanExtra(EXTRA_INIT_LOADING, isInitLoading).apply {
                    //用完就遗弃，避免脏数据
                    load.intent.removeExtra(EXTRA_INIT_LOADING)
                }
            }
            is Fragment -> {
                load.arguments?.getBoolean(EXTRA_INIT_LOADING, isInitLoading)?.apply {
                    //用完就遗弃，避免脏数据
                    load.arguments?.remove(EXTRA_INIT_LOADING)
                } ?: isInitLoading
            }
            else -> {
                isInitLoading
            }
        }

        if (isInitLoading) {
            if (load is IInitLoading) {
                //二级控制，class控制
                load.loadingInit(load, context)
            } else {
                //一级控制，全局
                defaultInitLoading.loadingInit(load, context)
            }
        }

    }
}