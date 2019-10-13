package tang.chinwe.lib_page.page

/**
 *
 */
interface IPage<IP : IPageView, IPC : IPageCallBack<IP>>:IPageView,IRootLayout {

    var page: IP?

    fun newPageCallBack(): IPC

}