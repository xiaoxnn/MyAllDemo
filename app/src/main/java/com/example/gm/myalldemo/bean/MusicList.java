package com.example.gm.myalldemo.bean;

import java.util.List;

/**
 * Created by gm on 2018/3/29.
 */

public class MusicList {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * attachment_path : /audio
         * audioState : 0
         * audio_desc : 1
         * audio_file : gaobaiqiqiu.mp3
         * audio_file_name : audio/0b37056d-5a6f-44c8-a9ee-383023285c35.mp3
         * audio_title : 1
         * audio_url :
         * content : ﻿[ti:告白气球][ar:周杰伦][al:周杰伦的床边故事][by:Love][00:00.00]告白气球[00:04.55]词：方文山 曲：周杰伦[00:09.23]演唱：周杰伦[00:13.67]我爱歌词网 www.5ilrc.com[00:22.92]塞纳河畔 左岸的咖啡[00:26.04]我手一杯 品尝你的美[00:28.95]留下唇印的嘴[00:31.98][00:33.67]花店玫瑰 名字写错谁[00:36.76]告白气球 风吹到对街[00:39.61]微笑在天上飞[00:42.67][00:43.07]你说你有点难追 想让我知难而退[00:48.98]礼物不需挑最贵 只要香榭的落叶[00:54.38]营造浪漫的约会 不害怕搞砸一切[00:59.69]拥有你就拥有 全世界[01:04.26][01:04.54]亲爱的 爱上你 从那天起[01:10.98]甜蜜的很轻易[01:14.96]亲爱的 别任性 你的眼睛[01:21.76]在说我愿意[01:25.68][01:30.54]LRC编辑：Love QQ:8178617[01:44.12][01:48.18]塞纳河畔 左岸的咖啡[01:51.36]我手一杯 品尝你的美[01:54.31]留下唇印的嘴[01:57.44]�玫瑰 名字写错谁[02:01.71]告白气球 风吹到对街[02:04.78]微笑在天上飞[02:07.97][02:08.31]你说你有点难追 想让我知难而退[02:14.33]礼物不需挑最贵 只要香榭的落叶[02:19.62]营造浪漫的约会 不害怕搞砸一切[02:25.01]拥有你就拥有 全世界[02:29.60][02:29.90]亲爱的 爱上你 从那天起[02:36.48]甜蜜的很轻易[02:40.44]亲爱的 别任性 你的眼睛[02:47.09]在说我愿意[02:50.80][02:51.29]亲爱的 爱上你 恋爱日记[02:57.70]飘香水的回忆[03:01.53]一整瓶 的梦境 全都有你[03:08.39]搅拌在一起[03:12.34]亲爱的别任性 你的眼睛[03:20.33]在说我愿意[03:27.47]
         * course_id : All
         * course_level :
         * course_period :
         * id : e2388b5b-96c8-4fd9-97b2-2add4c24cd6f
         * img_file : JayChou.jpg
         * img_file_name : img/de2fefc8-ec38-478c-91a5-57d53b6906d2.jpg
         * isfav : F
         * keyword :
         * left_or_right :
         * lrc_file : gaobaiqiqiu.lrc
         * lrc_file_name : lrc/1db972f7-b751-4bb7-a2c7-1919d18ff478.lrc
         * openTo :
         * source :
         * upload_time : {}
         * userId :
         */

        private String attachment_path;
        private int audioState;
        private String audio_desc;
        private String audio_file;
        private String audio_file_name;
        private String audio_title;
        private String audio_url;
        private String content;
        private String course_id;
        private String course_level;
        private String course_period;
        private String id;
        private String img_file;
        private String img_file_name;
        private String isfav;
        private String keyword;
        private String left_or_right;
        private String lrc_file;
        private String lrc_file_name;
        private String openTo;
        private String source;
        private UploadTimeBean upload_time;
        private String userId;

        public String getAttachment_path() {
            return attachment_path;
        }

        public void setAttachment_path(String attachment_path) {
            this.attachment_path = attachment_path;
        }

        public int getAudioState() {
            return audioState;
        }

        public void setAudioState(int audioState) {
            this.audioState = audioState;
        }

        public String getAudio_desc() {
            return audio_desc;
        }

        public void setAudio_desc(String audio_desc) {
            this.audio_desc = audio_desc;
        }

        public String getAudio_file() {
            return audio_file;
        }

        public void setAudio_file(String audio_file) {
            this.audio_file = audio_file;
        }

        public String getAudio_file_name() {
            return audio_file_name;
        }

        public void setAudio_file_name(String audio_file_name) {
            this.audio_file_name = audio_file_name;
        }

        public String getAudio_title() {
            return audio_title;
        }

        public void setAudio_title(String audio_title) {
            this.audio_title = audio_title;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getCourse_level() {
            return course_level;
        }

        public void setCourse_level(String course_level) {
            this.course_level = course_level;
        }

        public String getCourse_period() {
            return course_period;
        }

        public void setCourse_period(String course_period) {
            this.course_period = course_period;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg_file() {
            return img_file;
        }

        public void setImg_file(String img_file) {
            this.img_file = img_file;
        }

        public String getImg_file_name() {
            return img_file_name;
        }

        public void setImg_file_name(String img_file_name) {
            this.img_file_name = img_file_name;
        }

        public String getIsfav() {
            return isfav;
        }

        public void setIsfav(String isfav) {
            this.isfav = isfav;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getLeft_or_right() {
            return left_or_right;
        }

        public void setLeft_or_right(String left_or_right) {
            this.left_or_right = left_or_right;
        }

        public String getLrc_file() {
            return lrc_file;
        }

        public void setLrc_file(String lrc_file) {
            this.lrc_file = lrc_file;
        }

        public String getLrc_file_name() {
            return lrc_file_name;
        }

        public void setLrc_file_name(String lrc_file_name) {
            this.lrc_file_name = lrc_file_name;
        }

        public String getOpenTo() {
            return openTo;
        }

        public void setOpenTo(String openTo) {
            this.openTo = openTo;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public UploadTimeBean getUpload_time() {
            return upload_time;
        }

        public void setUpload_time(UploadTimeBean upload_time) {
            this.upload_time = upload_time;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static class UploadTimeBean {
        }
    }
}
