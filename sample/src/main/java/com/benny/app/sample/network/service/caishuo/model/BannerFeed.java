package com.benny.app.sample.network.service.caishuo.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by benny on 2/28/16.
 */

public class BannerFeed extends Feed {
    public Feed[] banners;

    public BannerFeed() {
        type = FeedType.BANNER;
    }

    List<Feed> banners() {
        return Arrays.asList(banners);
    }
}