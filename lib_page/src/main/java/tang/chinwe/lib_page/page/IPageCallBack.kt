package tang.chinwe.lib_page.page

import android.view.View

/**
 * pageimpl开放给业务实现的接口
 */
interface IPageCallBack<IP : IPageView> {

    val parentLayout: Int

    fun createPage(parentView:View):IP

    /**
     * 标题栏
     */
    var toolbarLayout: Int?

    /**
     * 头部
     */
    var headLayout: Int?


    /**
     * 主体
     */
    var layout: Int?

    /**
     * 尾部
     */
    var footLayout: Int?
}