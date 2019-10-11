package tang.chinwe.lib_page.loading

interface ILoading {

    var loadingState: LoadingState?

    /**
     * 显示加载
     */
    fun bmShow() {
        check(loadingState==LoadingState.Dismiss){
            "你不能show一个已经被dismiss的dialog"
        }
        loadingState = LoadingState.Show
    }

    /**
     * 隐藏
     */
    fun bmHide() {
        check(loadingState==LoadingState.Dismiss){
            "你不能hide一个已经被dismiss的dialog"
        }
        loadingState = LoadingState.Hide
    }

    /**
     * 销毁
     */
    fun bmDismiss() {
        loadingState = LoadingState.Dismiss
    }

    /**
     * 设置文本
     */
    fun setMessage(message: String) {

    }
}

enum class LoadingState {
    DNS, Show, Hide, Dismiss;
}