package tang.chinwe.lib_page.page.page_default

import android.view.View
import tang.chinwe.lib_page.page.IPageView

interface IDefaultPageView :IPageView{
    var pageToolBarView: View?
    var pageLayoutView: View?
    var pageHeadView: View?
    var pageFootView: View?
}
