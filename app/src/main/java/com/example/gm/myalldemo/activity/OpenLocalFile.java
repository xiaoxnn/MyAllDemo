package com.example.gm.myalldemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gm.myalldemo.R;
import com.example.gm.myalldemo.adapter.OpenFileAdapter;
import com.example.gm.myalldemo.bean.FileBean;
import com.example.gm.myalldemo.utils.FileManager;
import com.example.gm.myalldemo.utils.ToastUtil;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gm on 2018/1/25.
 */

public class OpenLocalFile extends AppCompatActivity {

    @BindView(R.id.tv_open_file)
    Button tvOpenFile;
    @BindView(R.id.rl_open_loacl_file)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_open_file2)
    Button tvOpenFile2;
    @BindView(R.id.tv_open_file3)
    Button tvOpenFile3;
    @BindView(R.id.trl_)
    TwinklingRefreshLayout TwinklingRL;
    private List<FileBean> mData = new ArrayList<>();
    private BaseQuickAdapter homeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_local_file);
        ButterKnife.bind(this);
        TwinklingRL.setPureScrollModeOn();
        initAdapter();
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.activity_close);
    }

    @OnClick({R.id.tv_open_file, R.id.tv_open_file2, R.id.tv_open_file3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_open_file:
                List<FileBean> ss = FileManager.getInstance(this).queryFiles(new String[]{".doc"});
                homeAdapter.setNewData(ss);
                break;
            case R.id.tv_open_file2:
                List<FileBean> ss2 = FileManager.getInstance(this).queryFiles(new String[]{".docx"});
                homeAdapter.setNewData(ss2);
                break;
            case R.id.tv_open_file3:
                List<FileBean> ss3 = FileManager.getInstance(this).queryFiles(new String[]{".xlsx", ".pdf"});
                homeAdapter.setNewData(ss3);
                break;
        }
    }


    private void initAdapter() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        homeAdapter = new OpenFileAdapter(R.layout.open_file_item_view, mData);
        homeAdapter.openLoadAnimation();
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showShort(OpenLocalFile.this, "选择了" + position);
            }
        });
        mRecyclerView.setAdapter(homeAdapter);
    }


}