package tang.chinwe.lib_page.page

import android.view.View

/**
 * 页面模板规范
 */
interface IPageView {
    var pageToolBarView: View?
    var pageLayoutView: View?
    var pageHeadView: View?
    var pageFootView: View?
}