package tang.chinwe.lib_page.lcee


interface ILcee : ILceeShow {

    var lceeShow: ILceeShow?

    /**
     * 加载页
     */
    fun loadingLayout(): Int? = null

    /**
     * 内容页
     */
    fun contentLayout(): Int? = null

    /**
     * 空数据页
     */
    fun emptyLayout(): Int? = null

    /**
     * 错误页
     */
    fun errorLayout(): Int? = null

    override fun showContent() {
        super.showContent()
        lceeShow?.showContent()
    }

    override fun showEmpty() {
        super.showEmpty()
        lceeShow?.showEmpty()
    }

    override fun showErrorView() {
        super.showErrorView()
        lceeShow?.showErrorView()
    }

    override fun showLoading() {
        super.showLoading()
        lceeShow?.showLoading()
    }
}


