package com.skydragon.gplay.gplayapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.skydragon.gplay.gplayapp.R;
import com.skydragon.gplay.gplayapp.entity.ChannelInfo;
import com.skydragon.gplay.gplayapp.entity.LabelInfo;
import com.skydragon.gplay.gplayapp.ui.BannerLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class GameLayoutListviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mAdvertPath;
    private List<LabelInfo> mLabelInfo;
    private List<ChannelInfo> mChannelInfo;
    private final int GAMELAYOUTMODUL = 7;
    private LayoutInflater minflater;
    private String mImgeButton;
    private DisplayImageOptions options;

    private final int TYPE_BANNER = 0;
    private final int TYPE_LABEL = 1;
    private final int TYPE_CHANNEL = 2;
    private final int TYPE_IMAGEBUTTON = 3;

    private LabelGridViewAdapter labelGridViewAdapter;
    private ChannelGridViewAdapter channelGridViewAdapter;

    public GameLayoutListviewAdapter(Context context, List<String> advertPath, List<LabelInfo> labelInfo, List<ChannelInfo> channelInfo, String imageButton) {
        minflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mAdvertPath = advertPath;
        this.mLabelInfo = labelInfo;
        this.mChannelInfo = channelInfo;
        this.mImgeButton = imageButton;
    }

    @Override
    public int getCount() {
        return GAMELAYOUTMODUL;
    }

    @Override
    public Object getItem(int position) {
//        switch (position) {
//            case 0:
//                return  mAdvertPath;
//
//            case 1:
//                return mLabelInfo;
//
//            case 5:
//                return mImgeButton;
//
//            default:
//                return mChannelInfo;
//        }
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolderBanner viewHolderBanner = null;
        ViewHolderLabel viewHolderLabel = null;
        ViewHolderChannel viewHolderChannel = null;
        ViewHolderImageView viewHolderImageView = null;

        int type = getItemViewType(position);
        if (convertView != null) {
            switch (type) {
                case TYPE_BANNER:
                    viewHolderBanner = (ViewHolderBanner) convertView.getTag();
                    viewHolderBanner.mBannerLayout.setViewUrls(mAdvertPath);
                    break;
                case TYPE_CHANNEL:
                    viewHolderChannel = (ViewHolderChannel) convertView.getTag();
                    switch (position) {
                        case 2:
                            viewHolderChannel.channelName.setText("最近在玩");
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "最近在玩");
                            break;
                        case 3:
                            viewHolderChannel.channelName.setText("热门精品");
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "热门精品");
                            break;
                        case 4:
                            viewHolderChannel.channelName.setText("新游推荐");
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "新游推荐");
                            break;
                        case 6:
                            viewHolderChannel.channelName.setText("趣味小游戏");
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "趣味小游戏");
                            break;
                        default:
                            break;
                    }

                    viewHolderChannel.more.setText("更多>>");
                    viewHolderChannel.mChannelGridView.setAdapter(channelGridViewAdapter);
                    break;
                case TYPE_LABEL:
                    viewHolderLabel = (ViewHolderLabel) convertView.getTag();
                    labelGridViewAdapter = new LabelGridViewAdapter(mContext, mLabelInfo);
                    viewHolderLabel.mLabelGridview.setAdapter(labelGridViewAdapter);
                    break;
                case TYPE_IMAGEBUTTON:
                    viewHolderImageView = (ViewHolderImageView) convertView.getTag();
                    options = new DisplayImageOptions.Builder()
                            .showImageOnLoading(R.drawable.image_default)
                            .showImageForEmptyUri(R.drawable.image_default)
                            .showImageOnFail(R.drawable.image_default)
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .considerExifParams(true)
                            .bitmapConfig(Bitmap.Config.RGB_565)
                            .build();
                    ImageLoader.getInstance().displayImage(mImgeButton,viewHolderImageView.mImageView,options);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE_BANNER:
                    convertView = minflater.inflate(R.layout.gamelayout_banner, viewGroup, false);
                    viewHolderBanner = new ViewHolderBanner();
                    viewHolderBanner.mBannerLayout = (BannerLayout) convertView.findViewById(R.id.gamelayout_banner);
                    viewHolderBanner.mBannerLayout.setViewUrls(mAdvertPath);
                    convertView.setTag(viewHolderBanner);
                    break;

                case TYPE_CHANNEL:
                    convertView = minflater.inflate(R.layout.gamelayout_channel_item, viewGroup, false);
                    viewHolderChannel = new ViewHolderChannel();
                    viewHolderChannel.channelName = (TextView) convertView.findViewById(R.id.gamelayout_channel_left);
                    viewHolderChannel.more = (TextView) convertView.findViewById(R.id.gamelayout_channel_right);
                    viewHolderChannel.mChannelGridView = (GridView) convertView.findViewById(R.id.gamelayout_channel_gridview);
                    switch (position) {
                        case 2:
                            viewHolderChannel.channelName.setText("最近在玩");
                            viewHolderChannel.channelName.setTextColor(Color.rgb(232, 111, 118));
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "最近在玩");
                            break;
                        case 3:
                            viewHolderChannel.channelName.setText("热门精品");
                            viewHolderChannel.channelName.setTextColor(Color.rgb(255, 139, 49));
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "热门精品");
                            break;
                        case 4:
                            viewHolderChannel.channelName.setText("新游推荐");
                            viewHolderChannel.channelName.setTextColor(Color.rgb(69, 173, 124));
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "新游推荐");
                            break;
                        case 6:
                            viewHolderChannel.channelName.setText("趣味小游戏");
                            viewHolderChannel.channelName.setTextColor(Color.rgb(28, 119, 226));
                            channelGridViewAdapter = new ChannelGridViewAdapter(mContext, mChannelInfo, "趣味小游戏");
                            break;
                        default:
                            break;
                    }
                    viewHolderChannel.more.setText("更多>>");
                    viewHolderChannel.mChannelGridView.setAdapter(channelGridViewAdapter);
                    convertView.setTag(viewHolderChannel);
                    break;
                case TYPE_LABEL:
                    convertView = minflater.inflate(R.layout.game_layout_label, viewGroup, false);
                    viewHolderLabel = new ViewHolderLabel();
                    viewHolderLabel.mLabelGridview = (GridView) convertView.findViewById(R.id.game_layout_labelgv);
                    labelGridViewAdapter = new LabelGridViewAdapter(mContext, mLabelInfo);
                    viewHolderLabel.mLabelGridview.setAdapter(labelGridViewAdapter);
                    convertView.setTag(viewHolderLabel);
                    break;
                case TYPE_IMAGEBUTTON:
                    convertView = minflater.inflate(R.layout.gamelayout_imagebutton, viewGroup, false);
                    viewHolderImageView = new ViewHolderImageView();
                    viewHolderImageView.mImageView = (ImageView) convertView.findViewById(R.id.game_layout_imb);
                    options = new DisplayImageOptions.Builder()
                            .showImageOnLoading(R.drawable.ic_stub)
                            .showImageForEmptyUri(R.drawable.image_default)
                            .showImageOnFail(R.drawable.image_default)
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .considerExifParams(true)
                            .bitmapConfig(Bitmap.Config.RGB_565)
                            .build();
                    ImageLoader.getInstance().displayImage(mImgeButton,viewHolderImageView.mImageView,options);
                    convertView.setTag(viewHolderImageView);
                    break;
                default:
                    break;
            }

        }
        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        int itemType;
        switch (position) {
            case 0:
                itemType = 0;
                break;
            case 1:
                itemType = 1;
                break;
            case 5:
                itemType = 3;
                break;
            default:
                itemType = 2;
                break;
        }
        return itemType;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    private final class ViewHolderBanner {
        BannerLayout mBannerLayout;
    }

    private final class ViewHolderLabel {
        GridView mLabelGridview;
    }

    private final class ViewHolderChannel {
        TextView channelName;
        TextView more;
        GridView mChannelGridView;
    }

    private final class ViewHolderImageView {
        ImageView mImageView;
    }
}
