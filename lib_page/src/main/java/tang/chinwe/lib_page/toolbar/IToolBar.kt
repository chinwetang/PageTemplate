package tang.chinwe.lib_page.toolbar

import android.view.View

/**
 * ToolBar的具体属性抽象
 */
interface IToolBar {
    fun leftText(): Int? = null
    fun rightText(): Int? = null
    fun leftIcon(): Int? = null
    fun rightIcon(): Int? = null
    fun titleText(): Int? = null
    fun titleIcon(): Int? = null
    /**
     * 单位：像素
     */
    fun drawablePadding():Int?=null
    /**
     * 返回true代表被消费
     */
    fun onClickLeft(view: View) = false

    /**
     * 返回true代表被消费
     */
    fun onClickRight(view: View) = false

    /**
     * 初始化并且隐藏
     */
    fun hideToolBar() = false
}