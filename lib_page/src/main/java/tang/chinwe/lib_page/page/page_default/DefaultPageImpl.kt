package tang.chinwe.lib_page.page.page_default

import android.view.View
import tang.chinwe.lib_page.page.IPageView

open class DefaultPageImpl : IDefaultPageView {

    override var pageToolBarView: View? = null

    override var pageLayoutView: View? = null

    override var pageHeadView: View? = null

    override var pageFootView: View? = null

}