package com.berezini.scraper.api.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NewsCollectionDTO {

    private ArrayList<News> newsCollection;

    public NewsCollectionDTO() {
        this.newsCollection = new ArrayList<>();
    }

}
