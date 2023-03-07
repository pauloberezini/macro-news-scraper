package com.berezini.scraper.api.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class News {
    private String date;
    private ArrayList<NewsItem> newsItems;

    public News() {
    }
}
