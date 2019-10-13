package tang.chinwe.lib_page

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import tang.chinwe.lib_page.immersion.ImmersionManager
import tang.chinwe.lib_page.lcee.ILcee
import tang.chinwe.lib_page.lcee.LceeManager
import tang.chinwe.lib_page.loading.ILoad
import tang.chinwe.lib_page.loading.LoadingManager
import tang.chinwe.lib_page.page.*
import tang.chinwe.lib_page.toolbar.IToolBar
import tang.chinwe.lib_page.toolbar.ToolBarManager

object PageTemplateLifecycleObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onLifecycleCreate(owner: LifecycleOwner?) {
        PageManager.pageInit(owner as? IPage<IPageView, IPageCallBack<IPageView>>)
        LceeManager.lceeInit(owner as? ILcee)
        ToolBarManager.initToolBar(owner as? IToolBar)
        ImmersionManager.initImmersionBar(owner as? Activity)
        LoadingManager.loadingInit(owner)
        (owner as? IRootLayout)?.onCreatePage()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onLifecycleDestroy(owner: LifecycleOwner?) {
        (owner as? ILoad)?.let {
            when (it) {
                is Activity -> {
                    it.bmDismiss()
                }
                is Fragment -> {
                    if ((it.activity as? ILoad)?.loading !== it.loading) {
                        //如果跟Activity不共享一个Loading的话，才关闭
                        it.bmDismiss()
                    }
                }
            }
        }


    }
}