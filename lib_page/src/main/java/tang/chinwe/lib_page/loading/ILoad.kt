package tang.chinwe.lib_page.loading

interface ILoad : ILoading {
    var loading: ILoading?

    fun loadMessage(): Int? = null
    fun loadViewId(): Int? = null
    fun cancelable(): Boolean? = null

    override fun bmShow() {
        super.bmShow()
        loading?.bmShow()
    }

    override fun bmDismiss() {
        super.bmDismiss()
        loading?.bmDismiss()
    }

    override fun bmHide() {
        super.bmHide()
        loading?.bmHide()
    }

    override fun setMessage(message: String) {
        loading?.setMessage(message)
    }
}
