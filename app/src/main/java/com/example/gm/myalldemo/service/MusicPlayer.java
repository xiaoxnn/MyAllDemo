package com.example.gm.myalldemo.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gm.myalldemo.content.MusicStatu;

/**
 * Created by gm on 2018/3/29.
 */

public class MusicPlayer extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {

    private int msg;
    private  MediaPlayer mediaPlayer;
    private  String url;   //播放链接
    private int currentTime;   //播放当前时间
    private int duration;      //音频文件的总长度
    private int percent;       //缓存的百分比
    private boolean isPlaying   ;  //播放状态
    public static final String MUSIC_CURRENT = "com.lzw.action.NET_MUSIC_CURRENT";
    public static final String MUSIC_DURATION = "com.lzw.action.NET_MUSIC_DURATION";
    public static final String MUSIC_PERCENT="com.lzw.action.NET_MUSIC_PERCENT";

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if(mediaPlayer != null&&mediaPlayer.isPlaying()) {
                    currentTime = mediaPlayer.getCurrentPosition();
                    Intent intent = new Intent();
                    intent.setAction(MUSIC_CURRENT);
                    intent.putExtra("currentTime", currentTime);
                    sendBroadcast(intent);
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
            if(msg.what==1){
                duration = mediaPlayer.getDuration();
                if (duration > 0) {
                    Intent intent = new Intent();
                    intent.setAction(MUSIC_DURATION);
//                    intent.putExtra("pos", mCurrentPosition);
                    Log.i("test", duration+"");
                    intent.putExtra("duration", duration);
                    sendBroadcast(intent);
                }
            }
            if(msg.what==2){
                Intent intent=new Intent();
                intent.setAction(MUSIC_PERCENT);
                intent.putExtra("percent", percent);
                sendBroadcast(intent);
            }
        };
    };


    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
         super.onStart(intent, startId);
         url= intent.getStringExtra("url");
         msg = intent.getIntExtra("MSG", 0);

          if(msg== MusicStatu.PLAY_STATU){
              //播放
              play(0);
          }
            if(msg== MusicStatu.PROGRESS_CHANGE){
                //播放
                currentTime = intent.getIntExtra("progress", -1);
                Log.i("test","bbbbbbb"+currentTime  +"     percent"+percent+"    "+percent*duration);
                if(currentTime>=0&&currentTime<percent*duration){
                    Log.i("test","aaaaaaaaaaaaaa");
                    mediaPlayer.seekTo(currentTime);
                }
            }
            if(msg== MusicStatu.IS_PLAYING){
                 isPlaying = intent.getBooleanExtra("isPlaying",false);
                 currentTime = intent.getIntExtra("progress", -1);
                  Log.i("test",   "isPlaying"+isPlaying  + " currentTime   "+currentTime);
                  pauseMusic();

            }
    }


    private void play(int currentTime) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            handler.sendEmptyMessage(0);
            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停播放
     */
    public void pauseMusic() {

        if(isPlaying){
            //播放
            try {
                mediaPlayer.seekTo(currentTime);
                mediaPlayer.start();
                handler.sendEmptyMessage(0);
                handler.sendEmptyMessage(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(mediaPlayer.isPlaying()){
            //暂停
            mediaPlayer.pause();
        }

    }


    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pauseMusic();
        Log.i("test","onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 缓存进度
     * @param mp
     * @param per
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int per) {
        percent=per;
        handler.sendEmptyMessage(2);
    }

    /**
     * 准备
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        Intent intent = new Intent();
        intent.setAction(MUSIC_DURATION);
        duration = mediaPlayer.getDuration();
        intent.putExtra("duration", duration);
        sendBroadcast(intent);
    }
}
