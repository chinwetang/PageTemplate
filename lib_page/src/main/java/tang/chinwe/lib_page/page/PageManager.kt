package tang.chinwe.lib_page.page

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import tang.chinwe.lib_page.lcee.IIsInitPage

/**
 * Page管理类
 */
object PageManager : IInitPage {

    const val EXTRA_INIT_PAGE = "isInitPage"

    var defaultLayout: Int? = null
    var defaultHeadLayout: Int? = null
    var defaultToolBarLayout: Int? = null
    var defaultFootLayout: Int? = null

    var defaultIsPage = false

    var defaultInitPage = object : IInitPage {

        override fun <IP : IPageView, IPC : IPageCallBack<IP>> pageInit(page: IPage<IP, IPC>?) {
            if (page == null)
                return
            val context: Context?
            val rootView: View?
            when (page) {
                is Activity -> {
                    context = page
                    rootView = page.window.decorView
                }
                is Fragment -> {
                    context = page.context
                    rootView = page.view
                }
                else -> {
                    return
                }
            }
            if (context == null || rootView == null)
                return
            val callBack = page.newPageCallBack().apply {
                headLayout = headLayout ?: defaultHeadLayout
                toolbarLayout = toolbarLayout ?: defaultToolBarLayout
                footLayout = footLayout ?: defaultFootLayout
                layout = layout ?: defaultLayout
                createPage()
            }
            page.page = callBack.page
        }
    }

    override fun <IP : IPageView, IPC : IPageCallBack<IP>> pageInit(page: IPage<IP, IPC>?) {
        //一级控制，全局控制
        var isInitPage = defaultIsPage
        //二级控制，Class控制
        if (page is IIsInitPage) {
            isInitPage = page.initPage()
        }
        /**
         * 三级控制，Object控制
         */
        isInitPage = when (page) {
            is Activity -> {
                page.intent.getBooleanExtra(EXTRA_INIT_PAGE, isInitPage).apply {
                    //用完就遗弃，避免脏数据
                    page.intent.removeExtra(EXTRA_INIT_PAGE)
                }
            }
            is Fragment -> {
                page.arguments?.getBoolean(EXTRA_INIT_PAGE, isInitPage)?.apply {
                    //用完就遗弃，避免脏数据
                    page.arguments?.remove(EXTRA_INIT_PAGE)
                } ?: isInitPage
            }
            else -> {
                isInitPage
            }
        }

        if (isInitPage) {
            if (page is IInitPage) {
                //二级控制，class控制
                page.pageInit(page)
            } else {
                //一级控制，全局
                defaultInitPage.pageInit(page)
            }
        }
    }


}