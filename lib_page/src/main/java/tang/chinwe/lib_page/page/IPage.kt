package tang.chinwe.lib_page.page

import android.view.View

/**
 * 页面模板规范
 */
interface IPage :ILayout,IGetCallBack{

    fun onBMInitView(rootView: View)

    fun onBMInitLoading(loadingView: View)

    fun onBMInitContentView(contentView: View)

    fun onBMInitEmpty(emptyView: View)

    fun onBMInitError(errorView: View)

    fun onBMInitToolBar(toolBarView: View)

    fun onBMInitHead(headView: View)

    fun onBMInitFoot(footView: View)
}