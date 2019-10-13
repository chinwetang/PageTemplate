package tang.chinwe.lib_page.page

import android.view.View

/**
 * pageimpl开放给业务实现的接口
 */
interface IPageCallBack<IP : IPageView> {

    val parentLayout: Int

    fun initLayout(defaultToolBarLayout:Int?,defaultHeadLayout:Int?,defaultLayout:Int?,defaultFootLayout:Int?)

    fun initPage(parentView:View,pageView:IPageView?):IP

}