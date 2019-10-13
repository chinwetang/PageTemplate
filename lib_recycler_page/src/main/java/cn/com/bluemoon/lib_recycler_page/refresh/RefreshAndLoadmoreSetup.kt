package cn.com.bluemoon.lib_recycler_page.refresh

interface RefreshAndLoadmoreSetup {

    /**
     * 设置是否启用上啦加载更多（默认启用）
     */
    fun setEnableLoadmore(enable: Boolean)

    /**
     * 是否启用下拉刷新（默认启用）
     */
    fun setEnableRefresh(enable: Boolean)


    /**
     * 完成刷新
     */
    fun finishRefresh()

    /**
     * 完成加载
     */
    fun finishLoadmore()

}