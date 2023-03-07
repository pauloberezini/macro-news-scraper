package com.berezini.scraper.api.service;

import com.berezini.scraper.api.model.News;
import com.berezini.scraper.api.model.NewsCollectionDTO;
import com.berezini.scraper.api.model.NewsItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScraperServiceImplIntegrationTest {

    @Test
    void testGetNews() {
        ScraperServiceImpl scraperService = new ScraperServiceImpl();

        // Set the URL to a test page
        scraperService.setUrl("https://sslecal2.forexprostools.com/");

        // Call the method and check the response
        NewsCollectionDTO newsCollectionDTO = scraperService.getNews();
        assertNotNull(newsCollectionDTO);
        assertTrue(newsCollectionDTO.getNewsCollection().size() > 0);
        ArrayList<News> newsList = newsCollectionDTO.getNewsCollection();
        News news  = newsList.get(0);
        NewsItem newsItem = news.getNewsItems().get(0);
        assertTrue(newsItem.getCountry().length()==3);
        assertTrue(newsItem.getEventName().length()>0);
        System.out.println();

    }
}