package com.skydragon.gplay.gplayapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.skydragon.gplay.gplayapp.R;
import com.skydragon.gplay.gplayapp.entity.ChannelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ChannelGridViewAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private Context mContext;
    private List<ChannelInfo> mChannelInfo;
    private List<ChannelInfo> mGameInfo;
    private String mChannelName;

    private String gameIconPath;
    private String gameName;
    private Bitmap bitmap;
    private DisplayImageOptions options;
    public ChannelGridViewAdapter(Context context, List<ChannelInfo> channelInfoList,String channelName) {
        minflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mChannelInfo = channelInfoList;
        this.mChannelName=channelName;
        mGameInfo=getGameInfo();
    }

    @Override
    public int getCount() {
        return mGameInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = minflater.inflate(R.layout.gamelayout_channelgrid_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.game_layout_channelgrid_ImageView);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.game_layout_channelgrid_text);
            convertView.setTag(viewHolder);
        }
        gameIconPath=mGameInfo.get(position).getChannelGameIconPath();
        gameName=mGameInfo.get(position).getChannelGameName();
        viewHolder.mTextView.setText(gameName);
//        bitmap = BitmapFactory.decodeFile(gameIconPath);
//        viewHolder.mImageButton.setImageBitmap(bitmap);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_default)
                .showImageForEmptyUri(R.drawable.image_default)
                .showImageOnFail(R.drawable.image_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(gameIconPath,viewHolder.mImageView,options);
        return convertView;
    }

    private final class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    private List<ChannelInfo> getGameInfo(){
        mGameInfo=new ArrayList<ChannelInfo>();
        for (int i=0;i<mChannelInfo.size();i++){
            if (mChannelName.equals(mChannelInfo.get(i).getChannelName())) {
                mGameInfo.add(mChannelInfo.get(i));
            }
        }
        return mGameInfo;
    }
}
