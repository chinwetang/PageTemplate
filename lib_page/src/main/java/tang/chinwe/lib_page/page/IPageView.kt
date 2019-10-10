package tang.chinwe.lib_page.page

import android.view.View

/**
 * 页面模板规范
 */
interface IPageView {
    var toolBarView: View?
    var layoutView: View?
    var headView: View?
    var footView: View?
}