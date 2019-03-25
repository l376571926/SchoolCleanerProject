package group.tonight.schoolcleaner.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import group.tonight.schoolcleaner.BR;
import group.tonight.schoolcleaner.model.DataBean;
import group.tonight.schoolcleaner.R;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：注册
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 17:10
 * 版本：V0.0.1
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBean mDataBean;
    private ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mViewDataBinding.setVariable(BR.handler, this);

        mDataBean = new DataBean();
        mViewDataBinding.setVariable(BR.data, mDataBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                String account = mDataBean.getAccount();
                String password = mDataBean.getPassword();

                Toast.makeText(this, account + " " + password, Toast.LENGTH_SHORT).show();

                finish();
                break;
            default:
                break;
        }
    }
}
