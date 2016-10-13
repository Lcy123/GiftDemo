package com.example.lcy.demo.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.OpenFragmentAdapter;
import com.example.lcy.demo.adapter.SpecialAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends Fragment {

    @BindView(R.id.special_viewPager)
    ViewPager special_viewPager;
    @BindView(R.id.special_tabLayout)
    TabLayout special_tabLayout;

    SpecialAdapter adapter;
    List<String> titleList=new ArrayList<>();
    List<Fragment>fragmentList=new ArrayList<>();

    public SpecialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_special, container, false);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData() {
        titleList.add("暴打星期三");
        titleList.add("新游周刊");
        for (int i=0;i<titleList.size();i++){
            special_tabLayout.addTab(special_tabLayout.newTab().setText(titleList.get(i)));
            if (i==0){
                fragmentList.add(new ThreeFragment());
            }else {
                fragmentList.add(new NewFragment());
            }
        }
        adapter=new SpecialAdapter(getFragmentManager(),fragmentList,titleList);
        special_viewPager.setAdapter(adapter);
        special_tabLayout.setupWithViewPager(special_viewPager);
    }

}
