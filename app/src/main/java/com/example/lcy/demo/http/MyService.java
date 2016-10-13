package com.example.lcy.demo.http;

import com.example.lcy.demo.bean.GiftDetailBean;
import com.example.lcy.demo.bean.GiftListBean;
import com.example.lcy.demo.bean.HotBean;
import com.example.lcy.demo.bean.KaiFuBean;
import com.example.lcy.demo.bean.NewDetailBean;
import com.example.lcy.demo.bean.NewGameBean;
import com.example.lcy.demo.bean.OpenDetailBean;
import com.example.lcy.demo.bean.SearchBean;
import com.example.lcy.demo.bean.TextBean;
import com.example.lcy.demo.bean.ThreeDetailBean;
import com.example.lcy.demo.bean.WeekThreeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lcy on 2016/9/28.
 */
public interface MyService {

    @GET("/majax.action?method=getGiftList")
    Call<GiftListBean>getGiftList(@Query("pageno")int pageno);

    @GET("/majax.action?method=getGiftInfo")
    Call<GiftDetailBean>getGiftInfo(@Query("id")String id);

    @GET("/majax.action?method=getJtkaifu")
    Call<KaiFuBean>getJtkaifu();

    @GET("/majax.action?method=getWebfutureTest")
    Call<TextBean>getWebfutureTest();

    @GET("/majax.action?method=getAppInfo")
    Call<OpenDetailBean>getAppInfo(@Query("id")String id);

    //
    @POST("/majax.action?method=searchGift")
    Call<SearchBean>searchGift(@Query("key")String key);

    @GET("/majax.action?method=hotpushForAndroid")
    Call<HotBean>hotpushForAndroid();
    //
    @GET("/majax.action?method=bdxqs")
    Call<WeekThreeBean>bdxqs(@Query("pageNo")int pageNo);

    @GET("/majax.action?method=getWeekll")
    Call<NewGameBean>getWeekll(@Query("pageNo")int pageNo);

    @GET("/majax.action?method=bdxqschild")
    Call<ThreeDetailBean>bdxqschild(@Query("id")int id);

    @GET("/majax.action?method=getWeekllChid")
    Call<NewDetailBean>getWeekllChid(@Query("id")int id);
}
