package tang.chinwe.lib_page.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.FragmentActivity
import android.view.View
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.lcee.ILceeShow
import tang.chinwe.lib_page.lcee.ShowState
import tang.chinwe.lib_page.loading.ILoading
import tang.chinwe.lib_page.loading.LoadingState
import tang.chinwe.lib_page.page.IPageCallBack
import tang.chinwe.lib_page.page.IPageView
import tang.chinwe.lib_page.page.IRootLayout

abstract class PageFragmentActivity<IP : IPageView, IPC : IPageCallBack<IP>> : FragmentActivity(), IPageActivity<IP, IPC> {
    /**
     * Page相关
     */
    override var page: IP? = null
    /**
     * Lcee相关
     */
    override var lceeShow: ILceeShow? = null
    override var showState: ShowState? = null
    override var pageLoadingView: View? = null
    override var pageContentView: View? = null
    override var pageEmptyView: View? = null
    override var pageErrorView: View? = null
    /**
     * Loading相关
     */
    override var loading: ILoading? = null
    override var loadingState: LoadingState? = null


    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootLayout())
    }

    override fun rootLayout() = R.layout.page_template_root

    companion object
}