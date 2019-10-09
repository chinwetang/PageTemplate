package tang.chinwe.lib_page.lcee

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.expand.gone
import tang.chinwe.lib_page.expand.visible

/**
 * 默认策略
 */
class DefaultLceeStrategy(
    override var loadingView: View?,
    override var contentView: View?,
    override var emptyView: View?,
    override var errorView: View?,
    override var showState: ShowState = ShowState.Content
) : ILcee {
    init {
        loadingView?.visible(showState == ShowState.Loading)
        contentView?.visible(showState == ShowState.Content)
        emptyView?.visible(showState == ShowState.Empty)
        errorView?.visible(showState == ShowState.Error)
    }

    override fun showLoading() {
        super.showLoading()
        loadingView?.visible()
        contentView?.gone()
        emptyView?.gone()
        errorView?.gone()
    }

    override fun showEmpty() {
        super.showEmpty()
        loadingView?.gone()
        contentView?.gone()
        emptyView?.visible()
        errorView?.gone()
    }

    override fun showErrorView() {
        super.showErrorView()
        loadingView?.gone()
        contentView?.gone()
        emptyView?.gone()
        errorView?.visible()
    }

    private val dp_50 by lazy {
        val resource = loadingView?.resources ?: contentView?.resources
        return@lazy resource?.getDimension(R.dimen.page_height_50dp) ?: 100f
    }

    private val duration by lazy {
        val resource = loadingView?.resources ?: contentView?.resources
        return@lazy resource?.getInteger(R.integer.lce_content_view_show_animation_time)?.toLong() ?: 100L
    }

    override fun showContent() {
        super.showContent()
        if (showState == ShowState.Loading) {
            emptyView?.gone()
            errorView?.gone()

            val set = AnimatorSet()
            val contentFadeIn = ObjectAnimator.ofFloat(
                contentView,
                "alpha", 0f, 1f
            )
            val contentTranslateIn = ObjectAnimator.ofFloat(
                contentView, "translationY", dp_50, 0f
            )

            val loadingFadeOut = ObjectAnimator.ofFloat(
                loadingView,
                "alpha", 1f, 0f
            )
            val loadingTranslateOut = ObjectAnimator.ofFloat(
                loadingView, "translationY", 0f, -dp_50
            )

            set.playTogether(
                contentFadeIn, contentTranslateIn, loadingFadeOut,
                loadingTranslateOut
            )
            set.duration = duration

            set.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationStart(animation: Animator) {
                    contentView?.translationY = 0f
                    loadingView?.translationY = 0f
                    contentView?.visible()
                }

                override fun onAnimationEnd(animation: Animator) {
                    loadingView?.gone()
                    loadingView?.alpha = 1f
                    contentView?.translationY = 0f
                    loadingView?.translationY = 0f
                }
            })

            set.start()
        } else {
            loadingView?.gone()
            contentView?.visible()
            emptyView?.gone()
            errorView?.gone()
        }
    }
}