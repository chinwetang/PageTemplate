package tang.chinwe.lib_page.loading

interface ILoading {

    var state: LoadingState

    /**
     * 显示加载
     */
    fun bmShow() {
        check(state==LoadingState.Dismiss){
            "你不能show一个已经被dismiss的dialog"
        }
        state = LoadingState.Show
    }

    /**
     * 隐藏
     */
    fun bmHide() {
        check(state==LoadingState.Dismiss){
            "你不能hide一个已经被dismiss的dialog"
        }
        state = LoadingState.Hide
    }

    /**
     * 销毁
     */
    fun bmDismiss() {
        state = LoadingState.Dismiss
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