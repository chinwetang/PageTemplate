package tang.chinwe.lib_page.lcee

import android.view.View

interface ILcee :ILceeShow{

    var loadingView: View?
    var contentView: View?
    var emptyView: View?
    var errorView: View?

    var showState: ShowState

    override fun showLoading() {
        if (showState == ShowState.Loading)
            return
        showState = ShowState.Loading
    }

    override fun showErrorView() {
        if (showState == ShowState.Error)
            return
        showState = ShowState.Error
    }

    override fun showEmpty() {
        if (showState == ShowState.Empty)
            return
        showState = ShowState.Empty
    }

    override fun showContent() {
        if (showState == ShowState.Content)
            return
        showState = ShowState.Content
    }

}

enum class ShowState {
    Loading, Content, Error, Empty;
}
