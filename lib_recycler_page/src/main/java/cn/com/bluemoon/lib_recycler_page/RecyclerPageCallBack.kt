package cn.com.bluemoon.lib_recycler_page

import android.view.View
import cn.com.bluemoon.lib_recycler_page.refresh.RefreshRecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import tang.chinwe.lib_page.page.IPageView
import tang.chinwe.lib_page.utils.stubView

abstract class RecyclerPageCallBack<ADAPTER : BaseQuickAdapter<*, *>> : IRecyclerPageCallBack<RecyclerPageImpl<ADAPTER>, ADAPTER> {

    override fun initPage(parentView: View, pageView: IPageView?) = RecyclerPageImpl<ADAPTER>().apply {
        pageRecyclerView = parentView.findViewById<RefreshRecyclerView<ADAPTER>>(R.id.refresh_recycler)
                ?.apply {
                    initView(this@RecyclerPageCallBack)
                }
        pageHeadView = stubView(parentView, R.id.page_template_stub_head, headLayout)
        (pageView as? IRecyclerPageView<ADAPTER>)?.let {
            it.pageRecyclerView = pageRecyclerView
            it.pageHeadView = pageHeadView
        }
    }

    override fun initLayout(defaultToolBarLayout: Int?, defaultHeadLayout: Int?, defaultLayout: Int?, defaultFootLayout: Int?) {
        headLayout = headLayout ?: defaultHeadLayout
    }

    override var headLayout: Int? = null
    override val parentLayout = R.layout.page_recycler_parent
}