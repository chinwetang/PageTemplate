package tang.chinwe.lib_page.page.page_default

import android.view.View
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.page.IPageCallBack
import tang.chinwe.lib_page.page.IPageView
import tang.chinwe.lib_page.utils.stubView

abstract class DefaultPageCallBack : IDefaultPageCallBack<DefaultPageImpl> {

    final override val parentLayout = R.layout.page_template_parent_layout

    override fun initLayout(defaultToolBarLayout: Int?, defaultHeadLayout: Int?, defaultLayout: Int?, defaultFootLayout: Int?) {
        toolbarLayout = toolbarLayout ?: defaultToolBarLayout
        headLayout = headLayout ?: defaultHeadLayout
        layout = layout ?: defaultLayout
        footLayout = layout ?: defaultFootLayout
    }

    override fun initPage(parentView: View, pageView: IPageView?) = DefaultPageImpl().apply {
        pageToolBarView = stubView(parentView, R.id.page_template_stub_toolbar, toolbarLayout)
        pageHeadView = stubView(parentView, R.id.page_template_stub_head, headLayout)
        pageLayoutView = stubView(parentView, R.id.page_template_stub_layout, layout)
        pageFootView = stubView(parentView, R.id.page_template_stub_foot, footLayout)

        (pageView as? IDefaultPageView)?.let {
            it.pageHeadView = pageHeadView
            it.pageFootView = pageFootView
            it.pageLayoutView = pageLayoutView
            it.pageToolBarView = pageToolBarView
        }
    }

    override var toolbarLayout: Int? = null

    override var headLayout: Int? = null

    override var footLayout: Int? = null

    override var layout: Int? = null
}