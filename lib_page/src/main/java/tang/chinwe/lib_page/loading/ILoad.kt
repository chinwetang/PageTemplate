package tang.chinwe.lib_page.loading

interface ILoad {
    var loading: ILoading?

    fun loadMessage():Int?=null
    fun loadViewId():Int?=null
    fun cancelable():Boolean?=null
}
