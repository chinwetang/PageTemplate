package tang.chinwe.lib_page.page

/**
 * 用于配置具体layout
 */
interface ILayout {


    /**
     * 标题栏
     */
    fun toolbarLayout(): Int? = null

    /**
     * 头部
     */
    fun headLayout(): Int? = null

    /**
     * 尾部
     */
    fun footLayout(): Int? = null

    /**
     * 主体
     */
    fun layout(): Int? = null
}