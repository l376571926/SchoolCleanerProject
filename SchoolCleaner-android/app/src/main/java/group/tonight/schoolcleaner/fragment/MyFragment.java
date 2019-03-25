package group.tonight.schoolcleaner.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import group.tonight.schoolcleaner.BR;
import group.tonight.schoolcleaner.R;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：我的
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 17:10
 * 版本：V0.0.1
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_my, container, false);
        viewDataBinding.setVariable(BR.handler,this);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_list:
                Toast.makeText(v.getContext(), "买入物品的订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sell_list:
                Toast.makeText(v.getContext(), "出售物品的订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                Toast.makeText(v.getContext(), "退出登录", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
