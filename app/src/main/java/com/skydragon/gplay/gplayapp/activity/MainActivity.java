package com.skydragon.gplay.gplayapp.activity;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.skydragon.gplay.gplayapp.R;
import com.skydragon.gplay.gplayapp.entity.TabEntity;
import com.skydragon.gplay.gplayapp.listener.CustomTabEntity;
import com.skydragon.gplay.gplayapp.ui.CommonTabLayout;
import com.skydragon.gplay.gplayapp.ui.TopBar;
import com.skydragon.gplay.gplayapp.utils.ViewFindUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.topbar)
    TopBar mTopBar;
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles = {"游戏", "圈子", "礼包", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_more_unselect,
            R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_more_select, R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private CommonTabLayout mTabLayout_3;
    private Fragment gameFragment=new GameFragment();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragments2.add(gameFragment);

        for (String title : mTitles) {

        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mDecorView = getWindow().getDecorView();
        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);
        mTabLayout_3.setTabData(mTabEntities, this, R.id.fragment_change, mFragments2);
        mTabLayout_3.setCurrentTab(0);
        mTabLayout_3.showDot(1);

        mTopBar.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarRightClick(View v) {
                Toast.makeText(MainActivity.this, "点击右边", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarLeftClick(View v) {
                Toast.makeText(MainActivity.this, "点击左边", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
