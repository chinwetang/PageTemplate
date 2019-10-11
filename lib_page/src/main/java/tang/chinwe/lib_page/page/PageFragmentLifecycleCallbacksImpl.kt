package tang.chinwe.lib_page.page

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import tang.chinwe.lib_page.page.IPage
import tang.chinwe.lib_page.page.PageManager

class PageFragmentLifecycleCallbacksImpl : FragmentManager.FragmentLifecycleCallbacks() {
    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        PageManager.pageInit(f as? IPage<IPageView, IPageCallBack<IPageView>>)
    }
}