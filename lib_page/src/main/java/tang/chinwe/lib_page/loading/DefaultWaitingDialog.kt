package tang.chinwe.lib_page.loading

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.gyf.immersionbar.ImmersionBar
import tang.chinwe.lib_page.Platform
import tang.chinwe.lib_page.R

class DefaultWaitingDialog(context: Context) :
    Dialog(context, R.style.page_template_default_Dialog), ILoading {

    override var loadingState:LoadingState? = LoadingState.DNS

    /**
     * 初始化界面与控件
     */
    init {
        //设置Windows沉浸式
        if (context is Activity && Platform.DEPENDENCY_IMMERSION_BAR) {
            ImmersionBar.with(context as Activity, this).init()
        }

        setContentView(R.layout.page_template_dialog_progress)
        // 保证全屏宽，因为默认宽高为WRAP_CONTENT
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes = lp
    }

    override fun setMessage(message: String) {
        val tvMessage = findViewById<View>(R.id.message) as? TextView
        tvMessage?.text = message
    }

    override fun dismiss() {
        super.dismiss()
        if (context is Activity) {
            ImmersionBar.destroy(context as Activity, this)
        }
    }

    override fun bmHide() {
        super.bmHide()
        hide()
    }

    override fun bmDismiss() {
        super.bmDismiss()
        dismiss()
    }

    override fun bmShow() {
        super.bmShow()
        show()
    }
}