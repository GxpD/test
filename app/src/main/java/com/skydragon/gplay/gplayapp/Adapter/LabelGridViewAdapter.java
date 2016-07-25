package com.skydragon.gplay.gplayapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.skydragon.gplay.gplayapp.R;
import com.skydragon.gplay.gplayapp.entity.LabelInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class LabelGridViewAdapter extends BaseAdapter  {
    private LayoutInflater minflater;
    private Context mContext;
    private List<LabelInfo> mLabelInfo;
    private String labelIconPath;
    private String labelName;
    private DisplayImageOptions options;

    public LabelGridViewAdapter(Context context, List<LabelInfo> labelInfo) {
        minflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mLabelInfo = labelInfo;
    }

    @Override
    public int getCount() {
        return mLabelInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mLabelInfo.get(position);
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
            convertView = minflater.inflate(R.layout.gamelayout_labelgrid_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.ImageView = (ImageView) convertView.findViewById(R.id.game_layout_labelgrid_ImageView);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.game_layout_labelgrid_text);
            convertView.setTag(viewHolder);
        }
        labelIconPath = mLabelInfo.get(position).getLabelImagePath();
        labelName = mLabelInfo.get(position).getLabelName();
        viewHolder.mTextView.setText(labelName);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_default)
                .showImageForEmptyUri(R.drawable.image_default)
                .showImageOnFail(R.drawable.image_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(labelIconPath,viewHolder.ImageView,options);
        Log.d("dataString","dataJson:"+viewHolder.ImageView.getTag());
        return convertView;
    }






    private final class ViewHolder {
        ImageView ImageView;
        TextView mTextView;
    }


}
