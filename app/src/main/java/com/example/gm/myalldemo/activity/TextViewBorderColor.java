package com.example.gm.myalldemo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;

import com.example.gm.myalldemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gm on 2018/1/26.
 */

public class TextViewBorderColor extends AppCompatActivity {


    @BindView(R.id.tv_textview_border_color)
    TextView tvTextviewBorderColor;
    SpannableString msp = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_border_color);
        ButterKnife.bind(this);
        String msg = "京东测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot边框";
        msp = new SpannableString(msg);
        // 设置边框
        Drawable bg = getResources().getDrawable(R.drawable.text_background);
        msp.setSpan(new ImageSpan(bg) {
            @Override
            public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
                             int bottom, Paint paint) {
                Log.i("test","len"+text.toString() +"  "+start  +"   "+end +"   "+x+"   "+top+"   "+y);
                paint.setTypeface(Typeface.create("normal", Typeface.NORMAL));
                paint.setTextSize(50);
                int len = Math.round(paint.measureText(text, start, end));
                Log.i("test","len"+len);
                getDrawable().setBounds(0, 0, len, 60);
                super.draw(canvas, text, start, end, x, top, y, bottom, paint);
                paint.setColor(Color.MAGENTA);
                paint.setTypeface(Typeface.create("normal", Typeface.NORMAL));
                paint.setTextSize(40);
                canvas.drawText(text.subSequence(start, end).toString(), x + 8, y, paint);
            }
        }, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTextviewBorderColor.setText(msp);
       // tvTextviewBorderColor.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
