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
 * 简述：卖家入口
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 17:12
 * 版本：V0.0.1
 */
public class SellerFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_seller, container, false);
        viewDataBinding.setVariable(BR.handler, this);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                Toast.makeText(v.getContext(), "确认出售", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                Toast.makeText(v.getContext(), "取消出售", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
