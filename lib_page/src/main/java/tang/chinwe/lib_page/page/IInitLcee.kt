package tang.chinwe.lib_page.page

import tang.chinwe.lib_page.page.IPage

interface IInitPage {
     fun <IP : IPageView, IPC : IPageCallBack<IP>> pageInit(page: IPage<IP, IPC>?)
}