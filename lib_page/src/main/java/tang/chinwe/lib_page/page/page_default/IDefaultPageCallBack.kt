package tang.chinwe.lib_page.page.page_default

import tang.chinwe.lib_page.page.IPageCallBack

interface IDefaultPageCallBack<IP:IDefaultPageView> : IPageCallBack<IP> {

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