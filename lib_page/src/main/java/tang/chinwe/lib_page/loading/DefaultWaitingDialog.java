package tang.chinwe.lib_page.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;

import tang.chinwe.lib_page.R;


/**
 * loading弹窗
 *
 * @author LK
 */
public class DefaultWaitingDialog extends Dialog implements ILoading {


    public DefaultWaitingDialog(Context context) {
        super(context, R.style.page_template_default_Dialog);
        initView();
    }

    /**
     * 初始化界面与控件
     */
    private void initView() {
        //设置Windows沉浸式
        if (getContext() instanceof Activity) {
            ImmersionBar.with((Activity) getContext(), this).init();
        }

        setContentView(R.layout.page_template_dialog_progress);
        // 保证全屏宽，因为默认宽高为WRAP_CONTENT
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
    }

    @Override
    public void setMessage(String message) {
        TextView tvMessage = (TextView) findViewById(R.id.message);
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (getContext() instanceof Activity) {
            ImmersionBar.destroy((Activity) getContext(), this);
        }
    }

    @Override
    public void bmDismiss() {
        dismiss();
    }

    @Override
    public void bmShow() {
        show();
    }
}