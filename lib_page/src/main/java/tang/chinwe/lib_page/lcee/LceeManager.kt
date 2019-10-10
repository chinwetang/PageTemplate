package tang.chinwe.lib_page.lcee

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewStub
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.utils.stubView

object LceeManager : IInitLcee {

    const val EXTRA_INIT_LOADING = "isInitLcee"

    var defaultLoadingLayout: Int? = null
    var defaultContentLayout: Int? = null
    var defaultEmptyLayout: Int? = null
    var defaultErrorLayout: Int? = null

    //默认不开启
    var defaultIsInitLcee = false

    var defaultInitLcee = object : IInitLcee {
        override fun lceeInit(lcee: ILcee?) {
            if (lcee == null)
                return
            val context: Context?
            val rootView: View?
            when (lcee) {
                is Activity -> {
                    context = lcee
                    rootView = lcee.window.decorView
                }
                is Fragment -> {
                    context = lcee.context
                    rootView = lcee.view
                }
                else -> {
                    return
                }
            }
            if (context == null || rootView == null)
                return
            lcee.lceeShow = DefaultLceeShow(
                stubView(
                    rootView,
                    R.id.page_template_stub_loading,
                    lcee.loadingLayout() ?: defaultLoadingLayout
                ),
                stubView(
                    rootView,
                    R.id.page_template_stub_content,
                    lcee.contentLayout() ?: defaultContentLayout
                ),
                stubView(
                    rootView,
                    R.id.page_template_stub_empty,
                    lcee.emptyLayout() ?: defaultEmptyLayout
                ),
                stubView(
                    rootView,
                    R.id.page_template_stub_error,
                    lcee.errorLayout() ?: defaultErrorLayout
                )
            )
        }
    }




    override fun lceeInit(lcee: ILcee?) {
        if (lcee == null)
            return
        //一级控制，全局控制
        var isInitLcee = defaultIsInitLcee
        //二级控制，Class控制
        if (lcee is IIsInitLcee) {
            isInitLcee = lcee.initLcee()
        }
        /**
         * 三级控制，Object控制
         */
        isInitLcee = when (lcee) {
            is Activity -> {
                lcee.intent.getBooleanExtra(EXTRA_INIT_LOADING, isInitLcee).apply {
                    //用完就遗弃，避免脏数据
                    lcee.intent.removeExtra(EXTRA_INIT_LOADING)
                }
            }
            is Fragment -> {
                lcee.arguments?.getBoolean(EXTRA_INIT_LOADING, isInitLcee)?.apply {
                    //用完就遗弃，避免脏数据
                    lcee.arguments?.remove(EXTRA_INIT_LOADING)
                } ?: isInitLcee
            }
            else -> {
                isInitLcee
            }
        }
        if (isInitLcee) {
            if (lcee is IInitLcee) {
                //二级配置，class控制
                lcee.lceeInit(lcee)
            } else {
                //一级配置，全局
                defaultInitLcee.lceeInit(lcee)
            }
        }
    }

}