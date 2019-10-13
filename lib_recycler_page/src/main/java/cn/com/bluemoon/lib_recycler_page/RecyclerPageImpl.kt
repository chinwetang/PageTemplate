package cn.com.bluemoon.lib_recycler_page

import android.view.View
import cn.com.bluemoon.lib_recycler_page.refresh.RefreshRecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter

class RecyclerPageImpl<ADAPTER : BaseQuickAdapter<*, *>> : IRecyclerPageView<ADAPTER> {
    override var pageHeadView: View? = null
    override var pageRecyclerView: RefreshRecyclerView<ADAPTER>? = null
}