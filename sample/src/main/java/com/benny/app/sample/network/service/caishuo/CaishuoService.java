package com.benny.app.sample.network.service.caishuo;


import com.benny.app.sample.network.service.caishuo.model.BannerFeed;
import com.benny.app.sample.network.service.caishuo.model.Feed;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
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

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private static CaishuoService instance;

    public static CaishuoService getInstance() {
        if(instance == null) instance = new CaishuoService();
        return instance;
    }

    public ICaishuoService service;

    private CaishuoService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new InsertHeadersInterceptor(API_KEY_DEV));

        final RuntimeTypeAdapterFactory<Feed.Item> typeFactory = RuntimeTypeAdapterFactory.of(Feed.Item.class, "type")
                .registerSubtype(Feed.StockItem.class, "stock")
                .registerSubtype(Feed.NewsItem.class, "news")
                .registerSubtype(Feed.BasketItem.class, "basket")
                .registerSubtype(Feed.TopicItem.class, "topic")
                .registerSubtype(Feed.PlateItem.class, "plate");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_TESTING)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat(DATE_FORMAT).registerTypeAdapterFactory(typeFactory).create()))
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

    public Observable<List<Feed> > feedUp(String lastId, int limit) {
        return service.feedUp(lastId, limit).map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Feed> > feedDown(String startId, String endId, int limit) {
        return service.feedDown(startId, endId, limit).map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BannerFeed> banners() {
        return service.banners().map(it -> it.data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
