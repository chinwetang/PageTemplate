package cn.com.bluemoon.lib_recycler_page.refresh

import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout

interface RefreshRecyclerCallback<ADAPTER : BaseQuickAdapter<*, *>> {

    fun onLoadmore(refreshlayout: RefreshLayout) {}

    fun onRefresh(refreshlayout: RefreshLayout) {}

    fun onBMInitSmartRefreshLayout(middlewareLayoutRefresh: SmartRefreshLayout) {}

    fun newAdapter(): ADAPTER

    /**
     * 大哥说留一个下拉刷新时会跟着下滑，但是上划时不会滑出屏幕的头部
     * 一般用不到，所以默认0
     * @return
     */
    fun getNoSlideHeadLayout(): Int? = null

    /**
     * 大哥说留一个下拉刷新时会跟着下滑，但是上划时不会滑出屏幕的头部
     * 一般用不到
     * @param slideHeadView
     */
    fun onBMInitNoSlideHeadView(slideHeadView: View?) {

    }

    /**
     * 作为一个item放在recyclerview里面的跟着滑动的head
     * @return
     */
    fun getRecyclerViewHeadLayout(): Int?=null

    /**
     * 作为一个item放在recyclerview里面的跟着滑动的foot
     * @return
     */
    fun getRecyclerViewFootLayout(): Int?=null

    /**
     * 作为一个item放在recyclerview里面的跟着滑动的head
     * @param headView
     */
    fun onBMInitRecyclerViewHeadView(headView: View?) {

    }

    /**
     * 作为一个item放在recyclerview里面的跟着滑动的foot
     * @param footView
     */
    fun onBMInitRecyclerViewFootView(footView: View) {

    }

    fun onBMInitSetting(recyclerView: RecyclerView?, adapter: ADAPTER) {}

    /**
     * 设置当空数据页的时候是否显示头部，默认显示
     */
    fun isHeadAndEmptyEnable(): Boolean {
        return false
    }


    /**
     * 设置当空数据页的时候是否显示尾部，默认显示
     */
    fun isFootAndEmptyEnable(): Boolean {
        return false
    }
}