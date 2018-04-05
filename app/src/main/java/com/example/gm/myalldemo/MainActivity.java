package com.example.gm.myalldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gm.myalldemo.activity.OpenLocalFile;
import com.example.gm.myalldemo.activity.PictureSelect;
import com.example.gm.myalldemo.activity.PlayMusic;
import com.example.gm.myalldemo.activity.PlayVideoActivity;
import com.example.gm.myalldemo.activity.TextViewBorderColor;
import com.example.gm.myalldemo.adapter.HomeAdapter;
import com.example.gm.myalldemo.bean.HomeItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_main_)
    RecyclerView mRecyclerView;
    private List<HomeItem> mData;
    String[] model = new String[]{"仿QQ打开本地文件","TextView给指定文字添加边框","图片选择","视频播放","播放音乐"};
    Class[]  activityClass=new Class[]{OpenLocalFile.class, TextViewBorderColor.class, PictureSelect.class, PlayVideoActivity.class, PlayMusic.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initAdapter();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HomeItem mh = new HomeItem();
            mh.setTitle(i < model.length ? model[i] : "title" + i);
            mData.add(mh);
        }
    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mData);
        homeAdapter.openLoadAnimation();
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openActivity(position);
            }
        });
        mRecyclerView.setAdapter(homeAdapter);
    }

    private void openActivity(int position){
        if(position<activityClass.length){
            Intent intent = new Intent(MainActivity.this,activityClass[position]);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_open,0);
        }else{
            Toast.makeText(MainActivity.this,"title"+position,Toast.LENGTH_LONG).show();
        }

    }

}
