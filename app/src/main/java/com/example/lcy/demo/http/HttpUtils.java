package com.example.lcy.demo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lcy on 2016/9/28.
 */
public class HttpUtils {

    public static final String BASE_URL = "http://www.1688wan.com";
    private static MyService myService;

    public static MyService getMyService() {
        if (myService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            myService = retrofit.create(MyService.class);
        }
        return myService;
    }
}
