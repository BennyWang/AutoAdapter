package com.benny.app.sample.network.service.caishuo;

import com.benny.app.sample.network.service.caishuo.model.*;
import retrofit2.http.*;
import rx.Observable;
import java.util.List;


/**
 * Created by benny on 9/6/15.
 */

public interface ICaishuoService {
    String API_VERSION = "/api/v1";

    @FormUrlEncoded
    @POST(API_VERSION + "/login")
    Observable<CaishuoEnveloped<User> > login(@Field("email_or_mobile") String identifier, @Field("password") String password);

    @GET(API_VERSION + "/users/{id}/following_stocks")
    Observable<CaishuoEnveloped<List<Stock> > > followedStocks(@Path("id") String id, @Query("per_page") int perPage);

    @GET(API_VERSION + "/stocks/{id}")
    Observable<CaishuoEnveloped<Stock> > stock(@Path("id") String id);

    @GET(API_VERSION + "/feeds/up")
    Observable<CaishuoEnveloped<List<Feed> > > feedUp(@Query("last_id") String lastId, @Query("limit") int limit);

    @GET(API_VERSION + "/feeds/up")
    Observable<CaishuoEnveloped<List<Feed> > > feedDown(@Query("start_id") String startId, @Query("end_id") String endId, @Query("limit") int limit);

    @GET(API_VERSION + "/baskets/discovery")
    Observable<CaishuoEnveloped<BannerFeed> > banners();
}
