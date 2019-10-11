package tang.chinwe.lib_page.page.page_default

import android.view.View
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.page.IPageCallBack
import tang.chinwe.lib_page.utils.stubView

abstract class DefaultPageCallBack : IPageCallBack<DefaultPageImpl> {

    override val parentLayout = R.layout.page_template_parent_layout

    override fun createPage(parentView: View) = DefaultPageImpl().apply {
        pageToolBarView = stubView(parentView, R.id.page_template_stub_toolbar, toolbarLayout)
        pageHeadView = stubView(parentView, R.id.page_template_stub_head, headLayout)
        pageLayoutView = stubView(parentView, R.id.page_template_stub_layout, layout)
        pageFootView = stubView(parentView, R.id.page_template_stub_foot, footLayout)
    }

    override var toolbarLayout: Int? = null

    override var headLayout: Int? = null

    override var footLayout: Int? = null
}