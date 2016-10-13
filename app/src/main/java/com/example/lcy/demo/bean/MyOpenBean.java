package com.example.lcy.demo.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lcy on 2016/10/7.
 */
public class MyOpenBean {

    String time;
    List<KaiFuBean.InfoBean> list = new LinkedList<>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<KaiFuBean.InfoBean> getDatas() {
        return list;
    }

    public void setData(KaiFuBean.InfoBean data) {
        this.list.add(data);
    }
}
