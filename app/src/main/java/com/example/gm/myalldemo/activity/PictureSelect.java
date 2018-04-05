package com.example.gm.myalldemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.gm.myalldemo.R;
import com.example.gm.myalldemo.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by gm on 2018/2/8.
 */

public class PictureSelect extends AppCompatActivity {


    @BindView(R.id.tv_open_camare)
    TextView tvOpenCamare;
    @BindView(R.id.tv_look_image)
    TextView tvLookImage;
    private List<LocalMedia> selectList;
    private ArrayList<String> mlist;
    private int REQUEST_IMAGE=2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_open_camare, R.id.tv_look_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_open_camare:
//                PictureSelector.create(PictureSelect.this)
//                        .openGallery(PictureMimeType.ofImage())
//                        .maxSelectNum(4)// 最大图片选择数量 int
//                         .selectionMedia(selectList)
//                        .forResult(PictureConfig.CHOOSE_REQUEST);

                MultiImageSelector.create(PictureSelect.this)
                        .showCamera(true) // 是否显示相机. 默认为显示
                .count(4) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                    .multi() // 多选模式, 默认模式;
                    .origin(mlist) // 默认已选择图片. 只有在选择模式为多选时有效
                        .start(PictureSelect.this,REQUEST_IMAGE);
                break;
            case R.id.tv_look_image:
                if(selectList!=null&&selectList.size()!=0){
                    PictureSelector.create(PictureSelect.this).externalPicturePreview(1, selectList);
                //    PictureSelector.create(PictureSelect.this).e
                }else{
                    ToastUtil.showShort(PictureSelect.this,"亲添加图片");
                }

                break;
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择结果回调
//                     selectList = PictureSelector.obtainMultipleResult(data);
//                    // 例如 LocalMedia 里面返回三种path
//                    // 1.media.getPath(); 为原图path
//                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
//                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
//                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
////                    adapter.setList(selectList);
////                    adapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                // 获取返回的图片列表
                mlist = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
            }
        }
    }

}
