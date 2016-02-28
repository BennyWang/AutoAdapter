package com.benny.app.sample.network.service.caishuo;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.benny.app.sample.network.service.caishuo.model.Stock;
import com.benny.app.sample.network.service.caishuo.model.User;

import java.util.List;

/**
 * Created by benny on 9/5/15.
 */

public class CaishuoService {
    private static final String BASE_TESTING = "http://testing.caishuo.com";
    private static final String BASE_H5_TESTING = "http://m.testing.caishuo.com";

    private static final String BASE_OFFICE = "https://office.caishuo.com";
    private static final String BASE_H5_OFFICE = "http://m.office.caishuo.com";

    private static final String BASE_PROD = "https://www.caishuo.com";
    private static final String BASE_H5_PROD = "https://m.caishuo.com";

    private static final String API_KEY_PROD = "adasdasdasdasdasdasdasdasdasdasdasd";
    private static final String API_KEY_DEV = "123456";

    private static final String API_BASE = BASE_TESTING;
    private static final String H5_BASE = BASE_H5_TESTING;
    private static final String API_KEY = API_KEY_DEV;

    private static CaishuoService instance;

    public static CaishuoService getInstance() {
        if(instance == null) instance = new CaishuoService();
        return instance;
    }

    public ICaishuoService service;

    private CaishuoService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new InsertHeadersInterceptor(API_KEY_DEV));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_TESTING)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();

        service = retrofit.create(ICaishuoService.class);
    }

    public Observable<User> login(String identifier, String password) {
        return service.login(identifier, password).map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Stock>> followedStocks(String id) {
        return service.followedStocks(id, 50).map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Stock> stock(String id)  {
        return service.stock(id).map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }



}
