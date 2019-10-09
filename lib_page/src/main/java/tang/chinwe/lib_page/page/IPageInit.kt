package tang.chinwe.lib_page.page

import android.view.View

interface IPageInit {
    fun onPageInit(rootView: View)
    fun onPageInitBefore() {}
    fun onPageInitAfter() {}
}