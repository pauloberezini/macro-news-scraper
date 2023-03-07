package com.berezini.scraper.api.model;

import lombok.Data;

@Data
public class NewsItem {
    private String time;
    private String country;
    private String sentiment;
    private String volatility;
    private String eventName;
    private String actualInfo;
    private String forecastInfo;
    private String previousInfo;

    public NewsItem() {
    }

}
