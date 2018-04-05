package com.example.gm.myalldemo.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gm.myalldemo.R;
import com.example.gm.myalldemo.bean.HomeItem;


import java.util.List;

/**
 * Created by gm on 2018/1/25.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {

    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.tv_text, item.getTitle());

    }
}

