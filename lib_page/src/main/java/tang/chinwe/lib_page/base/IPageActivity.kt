package tang.chinwe.lib_page.base

import tang.chinwe.lib_page.immersion.IImmersionBar
import tang.chinwe.lib_page.lcee.ILcee
import tang.chinwe.lib_page.loading.ILoad
import tang.chinwe.lib_page.page.IPage
import tang.chinwe.lib_page.page.IPageCallBack
import tang.chinwe.lib_page.page.IPageView
import tang.chinwe.lib_page.page.IRootLayout
import tang.chinwe.lib_page.toolbar.IToolBar

interface IPageActivity<IP : IPageView, IPC : IPageCallBack<IP>> : IPage<IP, IPC>, IImmersionBar, ILcee, ILoad, IToolBar, IRootLayout