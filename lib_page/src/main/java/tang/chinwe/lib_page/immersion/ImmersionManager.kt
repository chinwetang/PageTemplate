package tang.chinwe.lib_page.immersion

import android.app.Activity
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.RelativeLayout
import com.gyf.immersionbar.ImmersionBar
import tang.chinwe.lib_page.Platform
import tang.chinwe.lib_page.R

/**
 * 沉浸式管理类
 */
object ImmersionManager : IInitImmersionBar {

    const val EXTRA_INIT_IMMERSION_BAR = "isInitImmersionBar"

    //默认状态栏的颜色
    var defaultStatusBarColorResource = R.color.page_template_windowBackground
    //默认是不开启
    var defaultIsImmersion = false
    //默认的加载策略
    var defaultInitImmersionBar = object : IInitImmersionBar {
        override fun initImmersionBar(activity: Activity?) {
            if(activity==null)
                return
            if (!Platform.DEPENDENCY_IMMERSION_BAR)
                return

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.findViewById<View>(R.id.page_template_toolbar_layout)?.takeIf {
                    it !is RelativeLayout && (!Platform.DEPENDENCY_CONSTRAINT_LAYOUT || it !is ConstraintLayout)
                }?.let {
                    ImmersionBar.with(activity).titleBar(it, true).init()
                } ?: ImmersionBar.with(activity).fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                        .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                        .apply {
                            //一级控制，全局
                            var color = defaultStatusBarColorResource
                            (activity as? IImmersionBar)?.statusBarColorResource()?.let {
                                //二级控制，class
                                color = it
                            }
                            statusBarColor(color)
                        }.init()
            }
        }

    }

    override fun initImmersionBar(activity: Activity?) {
        if (activity == null)
            return
        //一级控制，全局控制
        var isImmersion = defaultIsImmersion

        //二级控制，Class控制
        if (activity is IIsInitImmersionBar) {
            isImmersion = activity.isImmersionBar()
        }

        /**
         * 三级控制，Object控制
         * 如果含有[EXTRA_INIT_IMMERSION_BAR]
         */
        isImmersion = activity.intent.getBooleanExtra(EXTRA_INIT_IMMERSION_BAR, isImmersion)

        if (isImmersion) {
            if (activity is IInitImmersionBar) {
                //二级控制，class控制
                activity.initImmersionBar(activity)
            } else {
                //一级控制，全局
                defaultInitImmersionBar.initImmersionBar(activity)
            }
        }
    }
}