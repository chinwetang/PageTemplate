package cn.com.bluemoon.lib_recycler_page

import android.view.View
import cn.com.bluemoon.lib_recycler_page.refresh.RefreshRecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import tang.chinwe.lib_page.page.IPageView

interface IRecyclerPageView<ADAPTER : BaseQuickAdapter<*, *>> : IPageView {
    var pageHeadView: View?
    var pageRecyclerView: RefreshRecyclerView<ADAPTER>?
}