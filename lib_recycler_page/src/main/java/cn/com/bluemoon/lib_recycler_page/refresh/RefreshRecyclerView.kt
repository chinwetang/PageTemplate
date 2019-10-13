package cn.com.bluemoon.lib_recycler_page.refresh

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewStub
import cn.com.bluemoon.lib_recycler_page.R

import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


/**
 * 一个实现了下拉刷新和上拉加载
 */
class RefreshRecyclerView<ADAPTER : BaseQuickAdapter<*, *>>(context: Context, attrs: AttributeSet) : SmartRefreshLayout(context, attrs), OnRefreshListener, OnLoadmoreListener {
    private val refreshHeader: ViewStub? by lazy {
        findViewById(R.id.page_recycler_refresh_header) as? ViewStub
    }
    private val refresh: RecyclerView? by lazy {
        findViewById(R.id.page_recycler_refresh) as? RecyclerView
    }

    private lateinit var callback: RefreshRecyclerCallback<ADAPTER>

    lateinit var adapter: ADAPTER

    init {
        LayoutInflater.from(context).inflate(R.layout.page_recycler_view_refresh_recycler, this)
    }

    fun initView(c: RefreshRecyclerCallback<ADAPTER>) {
        this.callback = c
        setOnRefreshListener(this)
        setOnLoadmoreListener(this)

        setEnableHeaderTranslationContent(false)//是否下拉Header的时候向下平移列表或者内容
        //        setEnableFooterTranslationContent(false);//是否上拉Footer的时候向上平移列表或者内容

        callback.getNoSlideHeadLayout()?.let {
            refreshHeader?.layoutResource = it
            callback.onBMInitNoSlideHeadView(refreshHeader?.inflate())
        }

        adapter = callback.newAdapter().apply {
            bindToRecyclerView(refresh)
            setHeaderFooterEmpty(callback.isHeadAndEmptyEnable(), callback.isFootAndEmptyEnable())
        }

        refresh?.let {
            //默认为纵向 重要!setLayoutManager应该在setAdapter之后调用
            it.layoutManager = LinearLayoutManager(context)
            it.itemAnimator = DefaultItemAnimator()
            callback.getRecyclerViewHeadLayout()?.let { headLayout ->
                val headView = LayoutInflater.from(context).inflate(headLayout, null)
                callback.onBMInitRecyclerViewHeadView(headView)
                if (headView != null) {
                    adapter.addHeaderView(headView)
                }
            }
            callback.getRecyclerViewFootLayout()?.let { footLayout ->
                val footView = LayoutInflater.from(context).inflate(footLayout, null)
                callback.onBMInitRecyclerViewFootView(footView)
                if (footView != null) {
                    adapter.addFooterView(footView)
                }
            }
        }

        isEnableRefresh = false
        isEnableLoadmore = false

        callback.onBMInitSmartRefreshLayout(this)
        callback.onBMInitSetting(refresh, adapter)
    }

    override fun onLoadmore(refreshlayout: RefreshLayout) {
        callback.onLoadmore(refreshlayout)
    }

    override fun onRefresh(refreshlayout: RefreshLayout) {
        callback.onRefresh(refreshlayout)
    }
}
