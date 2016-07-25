package com.skydragon.gplay.gplayapp.entity;

/**
 * Created by Administrator on 2016/7/16.
 */
public class ChannelInfo {
    private String channelName;
    private String channelGameIconPath;
    private String channelGameName;

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "channelName='" + channelName + '\'' +
                ", channelGameIconPath='" + channelGameIconPath + '\'' +
                ", channelGameName='" + channelGameName + '\'' +
                '}';
    }

    public ChannelInfo(String channelName, String channelGameName, String channelGameIconPath) {
        this.channelName = channelName;
        this.channelGameName = channelGameName;
        this.channelGameIconPath = channelGameIconPath;
    }

    public ChannelInfo() {
        super();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelGameName() {
        return channelGameName;
    }

    public void setChannelGameName(String channelGameName) {
        this.channelGameName = channelGameName;
    }

    public String getChannelGameIconPath() {
        return channelGameIconPath;
    }

    public void setChannelGameIconPath(String channelGameIconPath) {
        this.channelGameIconPath = channelGameIconPath;
    }


}
