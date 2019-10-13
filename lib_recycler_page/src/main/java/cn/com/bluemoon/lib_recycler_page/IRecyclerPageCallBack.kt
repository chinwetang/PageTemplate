package cn.com.bluemoon.lib_recycler_page

import android.view.View
import cn.com.bluemoon.lib_recycler_page.refresh.RefreshRecyclerCallback
import com.chad.library.adapter.base.BaseQuickAdapter
import tang.chinwe.lib_page.page.IPageCallBack
import tang.chinwe.lib_page.page.IPageView

interface IRecyclerPageCallBack<IP : IRecyclerPageView<ADAPTER>, ADAPTER : BaseQuickAdapter<*, *>> : IPageCallBack<IP>, RefreshRecyclerCallback<ADAPTER>{
    /**
     * 头部
     */
    var headLayout: Int?
}