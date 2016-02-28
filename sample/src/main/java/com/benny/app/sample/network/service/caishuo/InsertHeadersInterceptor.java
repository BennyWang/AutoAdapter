package com.benny.app.sample.network.service.caishuo;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by benny on 9/6/15.
 */

public class InsertHeadersInterceptor implements Interceptor {
    private String apiKey;
    public InsertHeadersInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("X-Client-Key", apiKey);
        requestBuilder.addHeader("X-SN-Code", "adsadsadasdasdasdsa");
        requestBuilder.addHeader("X-Client-Version", "1.0.0");
        return chain.proceed(requestBuilder.build());
    }
}
