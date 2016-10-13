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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {
    
    @BindView(R.id.open_tab)
    TabLayout tabLayout;
    @BindView(R.id.open_viewpager)
    ViewPager viewPager;

    OpenFragmentAdapter adapter;
    List<String>titleList=new ArrayList<>();
    List<Fragment>fragmentList=new ArrayList<>();

    public OpenFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_open, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {

        titleList.add("开服");
        titleList.add("开测");
        for (int i=0;i<titleList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
            if (i==0){
                fragmentList.add(new OpenKaiFuFragment());
            }else {
                fragmentList.add(new OpenTestFragment());
            }
        }
        adapter=new OpenFragmentAdapter(getFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
