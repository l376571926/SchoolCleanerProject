package group.tonight.schoolcleaner.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import group.tonight.schoolcleaner.BR;
import group.tonight.schoolcleaner.R;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：订单确认
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 18:35
 * 版本：V0.0.1
 */
public class OrderConfirmActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_confirm);
        viewDataBinding.setVariable(BR.handler, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                Toast.makeText(this, "取消订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.commit:
                Toast.makeText(this, "确认购买", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
