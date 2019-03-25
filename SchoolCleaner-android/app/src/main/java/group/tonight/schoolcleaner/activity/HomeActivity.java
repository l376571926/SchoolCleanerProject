package group.tonight.schoolcleaner.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import group.tonight.schoolcleaner.R;
import group.tonight.schoolcleaner.databinding.ActivityHomeBinding;
import group.tonight.schoolcleaner.fragment.HomeFragment;
import group.tonight.schoolcleaner.fragment.MyCollectionFragment;
import group.tonight.schoolcleaner.fragment.MyFragment;
import group.tonight.schoolcleaner.fragment.SellerFragment;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：主界面框架
 * 作者：https://github.com/l376571926
 * 时间：2019/3/25 0025 14:37
 * 版本：V0.0.1
 */
public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private HomeFragment mHomeFragment;
    private MyFragment mMyFragment;
    private MyCollectionFragment mMyCollectionFragment;
    private SellerFragment mSellerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        ActivityHomeBinding binding = ((ActivityHomeBinding) viewDataBinding);
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addOnTabSelectedListener(this);

        mHomeFragment = new HomeFragment();
        mMyFragment = new MyFragment();
        mMyCollectionFragment = new MyCollectionFragment();
        mSellerFragment = new SellerFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomeFragment, HomeFragment.class.getSimpleName())
                .add(R.id.fragment_container, mMyFragment, MyFragment.class.getSimpleName())
                .add(R.id.fragment_container, mMyCollectionFragment, MyCollectionFragment.class.getSimpleName())
                .add(R.id.fragment_container, mSellerFragment, SellerFragment.class.getSimpleName())
                .show(mHomeFragment)
                .hide(mMyFragment)
                .hide(mMyCollectionFragment)
                .hide(mSellerFragment)
                .commit();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (position == 0) {
            transaction
                    .show(mHomeFragment)
                    .hide(mMyFragment)
                    .hide(mMyCollectionFragment)
                    .hide(mSellerFragment)
                    .commit();
        } else if (position == 1) {
            transaction
                    .hide(mHomeFragment)
                    .show(mMyFragment)
                    .hide(mMyCollectionFragment)
                    .hide(mSellerFragment)
                    .commit();
        } else if (position == 2) {
            transaction
                    .hide(mHomeFragment)
                    .hide(mMyFragment)
                    .show(mMyCollectionFragment)
                    .hide(mSellerFragment)
                    .commit();
        } else if (position == 3) {
            transaction
                    .hide(mHomeFragment)
                    .hide(mMyFragment)
                    .hide(mMyCollectionFragment)
                    .show(mSellerFragment)
                    .commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
