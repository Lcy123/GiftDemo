package com.example.lcy.demo.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.fragment.GiftFragment;
import com.example.lcy.demo.fragment.HotFragment;
import com.example.lcy.demo.fragment.OpenFragment;
import com.example.lcy.demo.fragment.SpecialFragment;
import com.example.lcy.demo.view.MySlidingPaneLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.home_tab_home_rb)
    RadioButton mGiftRb;
    @BindView(R.id.home_tab_open_rb)
    RadioButton mOpenRb;
    @BindView(R.id.home_tab_hot_rb)
    RadioButton mHotRb;
    @BindView(R.id.home_tab_special_rb)
    RadioButton mSpecialRb;

    @BindView(R.id.relative_layout_content)
    RelativeLayout relative_layout_content;
    @BindView(R.id.gift_image)
    ImageView gift_image;
    @BindView(R.id.gift_title)
    TextView gift_title;
    @BindView(R.id.gift_search)
    TextView gift_seach;

    @BindView(R.id.sliding_layout)
    MySlidingPaneLayout sliding_Layout;

    GiftFragment mFragmentGift;
    OpenFragment mFragmentOpen;
    HotFragment mFragmentHot;
    SpecialFragment mFragmentSpecial;

    Fragment mCurrentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragments();
        addFragment();

        sliding_Layout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        sliding_Layout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                relative_layout_content.setScaleY(1-slideOffset*0.5f);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        gift_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("=====","===111===");
                if (sliding_Layout.isOpen()){
                    sliding_Layout.closePane();
                }else{
                    sliding_Layout.openPane();
                }
            }
        });

    }

    private void initFragments() {
        mFragmentGift= new GiftFragment();
        mFragmentHot= new HotFragment();
        mFragmentOpen= new OpenFragment();
        mFragmentSpecial= new SpecialFragment();
    }

    private  void addFragment(){
        FragmentTransaction trans=this.getSupportFragmentManager().beginTransaction();
        trans.add(R.id.home_content_layout,mFragmentGift).commit();
        mCurrentFragment=mFragmentGift;
    }

    private void switchFragment(Fragment fragment){
        FragmentTransaction trans=this.getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()){
            trans.hide(mCurrentFragment).show(fragment).commit();
        }
        else {
            trans.hide(mCurrentFragment).add(R.id.home_content_layout,fragment).commit();
        }
        mCurrentFragment=fragment;
    }

    @OnClick(R.id.home_tab_home_rb)
    void clickGift(){
        switchFragment(mFragmentGift);
        gift_title.setText("礼包精灵");
        gift_seach.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.home_tab_open_rb)
    void clickOpen(){
        switchFragment(mFragmentOpen);
        gift_title.setText("开服");
        gift_seach.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.home_tab_hot_rb)
    void clickHot(){
        switchFragment(mFragmentHot);
        gift_title.setText("热门游戏");
        gift_seach.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.home_tab_special_rb)
    void clickSpecial(){
        switchFragment(mFragmentSpecial);
        gift_title.setText("独家企划");
        gift_seach.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gift_search:
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
