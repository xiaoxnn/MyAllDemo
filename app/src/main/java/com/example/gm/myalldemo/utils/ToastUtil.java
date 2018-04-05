package com.example.gm.myalldemo.utils;

import android.content.Context;

/**
 * Created by gm on 2018/1/25.
 */

public class ToastUtil {

     public  static void  showLong(Context mContext,String msg){
          android.widget.Toast.makeText(mContext,msg, android.widget.Toast.LENGTH_LONG).show();
      }

    public  static void  showShort(Context mContext,String msg){
        android.widget.Toast.makeText(mContext,msg, android.widget.Toast.LENGTH_SHORT).show();
    }


}
