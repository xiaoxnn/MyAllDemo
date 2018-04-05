package com.example.gm.myalldemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.gm.myalldemo.R;
import com.example.gm.myalldemo.bean.MusicList;
import com.example.gm.myalldemo.content.MusicFile;
import com.example.gm.myalldemo.content.MusicStatu;
import com.example.gm.myalldemo.service.MusicPlayer;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import me.wcy.lrcview.LrcView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by gm on 2018/3/27.
 */

public class PlayMusic  extends AppCompatActivity {

    LrcView mPlayMusic;    //歌唱显示
    SeekBar mseekbar;      //歌词进度条
    private Intent servierIntent;  //播放的服务
    private TextView isPlay;
    private int currentTime;   //播放当前时间
    private List<MusicList.DataBean>  mmlist;   //数据集合

    private  boolean isPlaying=false   ;         //正在播放

    public static final String UPDATE_ACTION = "com.lzw.action.NET_UPDATE_ACTION";
    public static final String CTL_ACTION = "com.lzw.action.NET_CTL_ACTION";
    public static final String MUSIC_CURRENT = "com.lzw.action.NET_MUSIC_CURRENT";
    public static final String MUSIC_DURATION = "com.lzw.action.NET_MUSIC_DURATION";
    public static final String MUSIC_PERCENT="com.lzw.action.NET_MUSIC_PERCENT";


    private PlayerReceiver playerReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_play_music);

         OkHttpUtils
                .post()
                .url("http://192.168.1.96:8060/ABIE/student/audioAction!queryAudioList.action?")
                .addParams("source", "0")
                .addParams("level", "Hello ABC")
                .addParams("padFlag", "1")
                .addParams("userId", "1660405")
                .build()
                .execute(callback);


             mPlayMusic=findViewById(R.id.music_play);
             mseekbar=findViewById(R.id.seekbar);
             findViewById(R.id.tv_up).setOnClickListener(UponClickListener);
             findViewById(R.id.tv_next).setOnClickListener(nextonClickListener);
             isPlay=findViewById(R.id.tv_play);
             isPlay.setOnClickListener(playonClickListener);



                mseekbar.setOnSeekBarChangeListener(OnSeekBarChangeListener);
                playerReceiver = new PlayerReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(UPDATE_ACTION);
                filter.addAction(MUSIC_CURRENT);
                filter.addAction(MUSIC_DURATION);
                filter.addAction(MUSIC_PERCENT);
                registerReceiver(playerReceiver, filter);


         //   mPlayMusic.loadLrc(MusicFile.fileString);
             mPlayMusic.setOnPlayClickListener(new LrcView.OnPlayClickListener() {
                @Override
                public boolean onPlayClick(long time) {
                    Log.i("test","time"+time);
              //      mPlayMusic.updateTime(time);
                    currentTime= (int) time;
                    if(isPlaying){
                        broadcast_progress();
                    }else{
                        isPlaying=true;
                        broadcast_paly();
                    }
                    isPlay.setText(isPlaying?"暂停":"播放");
                    return true;
                }
            });
    }

        Callback callback=new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson mgson = new Gson();
                MusicList mlist = mgson.fromJson(response.toString(), MusicList.class);
                if (mlist.getData() != null && mlist.getData().size() != 0) {
                      mmlist= mlist.getData();
                      play();
                }
            }
        };


    /**
     * 更改进度条广播
     */
    private void  broadcast_progress(){
        servierIntent = new Intent();
        servierIntent.setClass(PlayMusic.this, MusicPlayer.class);
        servierIntent.setAction("om.example.gm.myalldemo.music");
        servierIntent.putExtra("url","http://192.168.1.96:8060/ABIE/files/audio/audio/0b37056d-5a6f-44c8-a9ee-383023285c35.mp3");
        servierIntent.putExtra("MSG", MusicStatu.PROGRESS_CHANGE);
        //   intent.putExtra("position", mCurrentPosition);
        servierIntent.putExtra("progress", currentTime);
        startService(servierIntent);

    }

    /**
     * 播放暂停
     */
    private void  broadcast_paly(){
        servierIntent = new Intent();
        servierIntent.setClass(PlayMusic.this, MusicPlayer.class);
        servierIntent.setAction("om.example.gm.myalldemo.music");
        servierIntent.putExtra("url","http://192.168.1.96:8060/ABIE/files/audio/audio/0b37056d-5a6f-44c8-a9ee-383023285c35.mp3");
        servierIntent.putExtra("MSG", MusicStatu.IS_PLAYING);
        //   intent.putExtra("position", mCurrentPosition);
        servierIntent.putExtra("progress", currentTime);
        servierIntent.putExtra("isPlaying",isPlaying);
        startService(servierIntent);
    }

    public class PlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MUSIC_CURRENT)) {

                currentTime = intent.getIntExtra("currentTime", -1);
                Log.i("test","currentTime"+currentTime);
                mPlayMusic.updateTime(currentTime);

 //               currentProgress.setText(MediaUtil.formatTime(currentTime));
//                musicProgress.setProgress(currentTime);
                mseekbar.setProgress(currentTime);
            } else if (action.equals(MUSIC_DURATION)) {
                int duration = intent.getIntExtra("duration", -1);
                 Log.i("test","duration"+duration);
                mseekbar.setMax(duration);
  //              mPlayMusic.loadLrc(mmlist.get(0).getContent());
//                musicProgress.setMax(duration);
//                if(mNetSongBean.getDuration()==-1){
//                    mNetSongBean.setDuration(duration);
//                }

//                finalProgress.setText(MediaUtil.formatTime(duration));
            }else if(action.equals(MUSIC_PERCENT)){
                int percent=intent.getIntExtra("percent",-1);
                mseekbar.setSecondaryProgress(mseekbar.getMax()*percent/100);

            }
        }
    }


    private void play(){
        servierIntent = new Intent();
        servierIntent.setAction("com.example.gm.myalldemo.music");
        servierIntent.setClass(this,MusicPlayer.class);
        servierIntent.putExtra("url", "http://192.168.1.96:8060/ABIE/files/audio/audio/0b37056d-5a6f-44c8-a9ee-383023285c35.mp3");
       // intent.putExtra("position", mCurrentPosition);
        servierIntent.putExtra("MSG", MusicStatu.PLAY_STATU);
        startService(servierIntent);
        mPlayMusic.loadLrc(mmlist.get(0).getContent());
        isPlaying=true;
        isPlay.setText("暂停");
//        isPause=false;
    }

    SeekBar.OnSeekBarChangeListener OnSeekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currentTime=progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            broadcast_progress();
        }
    };

    /**
     * 上一曲
     */
    View.OnClickListener UponClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    /**
     * 播放暂停
     */
    View.OnClickListener playonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isPlay.setText(isPlaying?"播放":"暂停");
            isPlaying=!isPlaying;
            broadcast_paly();
        }
    };

    /**
     * 下一曲
     */
    View.OnClickListener nextonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(servierIntent!=null){
            stopService(servierIntent);
        }
        if (playerReceiver != null) {
            unregisterReceiver(playerReceiver);
            playerReceiver = null;
        }
    }
}
