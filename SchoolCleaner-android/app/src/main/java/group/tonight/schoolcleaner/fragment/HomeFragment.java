package group.tonight.schoolcleaner.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import group.tonight.schoolcleaner.BR;
import group.tonight.schoolcleaner.R;
import group.tonight.schoolcleaner.activity.ProductDetailActivity;
import group.tonight.schoolcleaner.databinding.FragmentHomeBinding;
import group.tonight.schoolcleaner.databinding.ListItemHomeFragmentBinding;
import group.tonight.schoolcleaner.model.DataListResponseBean;
import group.tonight.schoolcleaner.model.ProductBean;

/**
 * 公司：深圳市中泰智丰物联网科技有限公司
 * 项目：校园清道夫
 * 简述：首页
 * 作者：https://github.com/l376571926
 * 时间：2019/3/22 0022 17:08
 * 版本：V0.0.1
 */
public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<ProductBean> mDataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        FragmentHomeBinding binding = ((FragmentHomeBinding) viewDataBinding);
        mRecyclerView = binding.recyclerView;

        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        InputStream inputStream = getResources().openRawResource(R.raw.productlist);
        Type type = new TypeToken<DataListResponseBean<ProductBean>>() {
        }.getType();
        DataListResponseBean<ProductBean> dataListResponseBean = new Gson().fromJson(new InputStreamReader(inputStream), type);

        List<ProductBean> dataList = dataListResponseBean.getData();

        mDataList = new ArrayList<>();
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    public class DataBindingViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public DataBindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = viewDataBinding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    private RecyclerView.Adapter<DataBindingViewHolder> mAdapter = new RecyclerView.Adapter<DataBindingViewHolder>() {
        @NonNull
        @Override
        public DataBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DataBindingViewHolder(DataBindingUtil.inflate(getLayoutInflater(), R.layout.list_item_home_fragment, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DataBindingViewHolder holder, int position) {
            ViewDataBinding dataBinding = holder.getBinding();
            ListItemHomeFragmentBinding binding = ((ListItemHomeFragmentBinding) dataBinding);
            ImageView imageView = binding.image;

            ProductBean productBean = mDataList.get(position);
            dataBinding.setVariable(BR.data, productBean);

            Glide.with(imageView)
                    .load(productBean.getImage())
                    .into(imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), ProductDetailActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    };


}
