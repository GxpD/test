package com.skydragon.gplay.gplayapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.skydragon.gplay.gplayapp.Adapter.GameLayoutListviewAdapter;
import com.skydragon.gplay.gplayapp.Adapter.LabelGridViewAdapter;
import com.skydragon.gplay.gplayapp.R;
import com.skydragon.gplay.gplayapp.entity.ChannelInfo;
import com.skydragon.gplay.gplayapp.entity.LabelInfo;
import com.skydragon.gplay.gplayapp.net.LoadPageInfoApi;
import com.skydragon.gplay.gplayapp.ui.BannerLayout;
import com.skydragon.gplay.gplayapp.ui.RefreshableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class GameFragment extends Fragment {

    private List<LabelInfo> labelInfoList;
    private List<ChannelInfo> channelInfoList;
    private String imageButtonPath = "http://static.encal.cn/image/578cca228b0d5.png";
    private RefreshableView refreshableView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        LabelInfo labelInfo1 = new LabelInfo();
        labelInfo1.setLabelName("找你妹");
        labelInfo1.setLabelImagePath("http://static.encal.cn/image/3icon.jpg");
        LabelInfo labelInfo2 = new LabelInfo();
        labelInfo2.setLabelName("找你爹");
        labelInfo2.setLabelImagePath("http://static.encal.cn/image/3icon.jpg");
        LabelInfo labelInfo3 = new LabelInfo();
        labelInfo3.setLabelName("找你爹");
        labelInfo3.setLabelImagePath("http://static.encal.cn/image/3icon.jpg");
        LabelInfo labelInfo4 = new LabelInfo();
        labelInfo4.setLabelName("找你爹");
        labelInfo4.setLabelImagePath("http://static.encal.cn/image/3icon.jpg");

        labelInfoList = new ArrayList<LabelInfo>();
        labelInfoList.add(labelInfo1);
        labelInfoList.add(labelInfo2);
        labelInfoList.add(labelInfo3);
        labelInfoList.add(labelInfo4);

        channelInfoList = new ArrayList<ChannelInfo>();
        ChannelInfo channelInfo1 = new ChannelInfo();
        channelInfo1.setChannelName("最近在玩");
        channelInfo1.setChannelGameIconPath("http://static.encal.cn/image/3icon.jpg");
        channelInfo1.setChannelGameName("坑爹游戏1");
        ChannelInfo channelInfo2 = new ChannelInfo();
        channelInfo2.setChannelName("热门精品");
        channelInfo2.setChannelGameIconPath("http://static.encal.cn/image/3icon.jpg");
        channelInfo2.setChannelGameName("坑爹游戏2");
        ChannelInfo channelInfo3 = new ChannelInfo();
        channelInfo3.setChannelName("新游推荐");
        channelInfo3.setChannelGameIconPath("http://static.encal.cn/image/3icon.jpg");
        channelInfo3.setChannelGameName("坑爹游戏3");
        ChannelInfo channelInfo4 = new ChannelInfo();
        channelInfo4.setChannelName("趣味小游戏");
        channelInfo4.setChannelGameIconPath("http://static.encal.cn/image/3icon.jpg");
        channelInfo4.setChannelGameName("坑爹游戏4");
        channelInfoList.add(channelInfo1);
        channelInfoList.add(channelInfo2);
        channelInfoList.add(channelInfo3);
        channelInfoList.add(channelInfo4);

        final List<String> urls = new ArrayList<String>();
        urls.add("http://static.encal.cn/image/ad1.jpg");
        urls.add("http://static.encal.cn/image/578cca228b0d5.png");
        urls.add("http://static.encal.cn/image/578cc914c8686.png");
//        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
//        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
//        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");


        View view = inflater.inflate(R.layout.game_layout_list, container, false);
        refreshableView= (RefreshableView) view.findViewById(R.id.refreshable_view);
        ListView listView = (ListView) view.findViewById(R.id.game_layout_listview);
        GameLayoutListviewAdapter gameLayoutListviewAdapter = new GameLayoutListviewAdapter(getContext(), urls, labelInfoList, channelInfoList, imageButtonPath);
        listView.setAdapter(gameLayoutListviewAdapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);
        return view;

    }

}
