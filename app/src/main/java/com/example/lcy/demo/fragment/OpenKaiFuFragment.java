package com.example.lcy.demo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.OpenKaiFuAdapter;
import com.example.lcy.demo.bean.KaiFuBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.http.MyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenKaiFuFragment extends Fragment {

    @BindView(R.id.open_kaiFu_listView)
    ExpandableListView open_kaiFu_listView;

    OpenKaiFuAdapter adapter;
    List<String> keys;
    Map<String, List<KaiFuBean.InfoBean>> result;
    Context context;

    public OpenKaiFuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_kai_fu, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void initData() {
        MyService service = HttpUtils.getMyService();
        service.getJtkaifu().enqueue(new Callback<KaiFuBean>() {
            @Override
            public void onResponse(Call<KaiFuBean> call, Response<KaiFuBean> response) {
                KaiFuBean bean = response.body();
                formatResult(bean);
                adapter = new OpenKaiFuAdapter(keys, result, context);
                open_kaiFu_listView.setAdapter(adapter);
                for (int i = 0; i < keys.size(); i++) {
                    open_kaiFu_listView.expandGroup(i);
                }
            }
            @Override
            public void onFailure(Call<KaiFuBean> call, Throwable t) {

            }
        });
    }

    private void formatResult(KaiFuBean kaiFuBean) {
        keys = new ArrayList<>();
        result = new HashMap<>();
        List<KaiFuBean.InfoBean> infos = kaiFuBean.getInfo();
        for (KaiFuBean.InfoBean infoBean : infos) {
            String thiskey = infoBean.getAddtime();
            if (result.containsKey(thiskey)) {
                result.get(thiskey).add(infoBean);
            } else {
                keys.add(thiskey);
                List<KaiFuBean.InfoBean> infoBeanList = new ArrayList<>();
                infoBeanList.add(infoBean);
                result.put(thiskey, infoBeanList);
            }
        }
    }
}

