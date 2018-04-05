package com.example.gm.myalldemo.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gm.myalldemo.R;
import com.example.gm.myalldemo.bean.FileBean;
import com.example.gm.myalldemo.utils.FileUtils;


import java.util.List;

/**
 * Created by gm on 2018/1/25.
 */

public class OpenFileAdapter extends BaseQuickAdapter<FileBean, BaseViewHolder> {

    public OpenFileAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FileBean item) {
        helper.setText(R.id.tv_file_size, item.getSize()!=null?FileUtils.FormetFileSize(Long.parseLong(item.getSize())):"0");
        helper.setText(R.id.tv_file_name, FileUtils.getFileName(item.getPath()));

    }
}

