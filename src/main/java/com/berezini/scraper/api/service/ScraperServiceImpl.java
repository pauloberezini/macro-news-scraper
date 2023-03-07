package com.berezini.scraper.api.service;

import com.berezini.scraper.api.model.News;
import com.berezini.scraper.api.model.NewsCollectionDTO;
import com.berezini.scraper.api.model.NewsItem;
import com.berezini.scraper.api.util.NewsUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class ScraperServiceImpl implements IScraperService {

    @Value("${news.macro.url}")
    private String url;

    static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
    static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public NewsCollectionDTO getNews() {
        try {

            Document doc = Jsoup.connect(url).get();

            // Parse the HTML response and extract the relevant data
            Elements links = doc.select("#ecEventsTable > tbody > tr");

            NewsCollectionDTO newsCollectionDTO = new NewsCollectionDTO();
            News news = new News();

            ArrayList<News> list = new ArrayList<>();
            ArrayList<NewsItem> newsItems = new ArrayList<>();


            for (Element link : links) {
                Node node = link.selectFirst("td").childNodes().get(0);
                try {

                    LocalDate date = LocalDate.parse(node.outerHtml(), inputFormatter);
                    String newDate = (date.format(outputFormatter));
                    if(news.getDate() == null ){
                        news.setDate(newDate);
                    }
                    if(!news.getDate().equals(newDate)){
                        list.add(news);
                        news = new News();
                        news.setDate(newDate);
                    }

                    continue;
                } catch (Exception e) {
                    //do nothing, this is not a date
                }

                String country = NewsUtils.getCountry(link);
                String time = NewsUtils.getTime(link);
                String eventName = NewsUtils.getEventName(link);
                String actualInfo = NewsUtils.getActualInfo(link);
                String forecastInfo = NewsUtils.getForecastInfo(link);
                String previousInfo = NewsUtils.getPreviousInfo(link);
                String sentimentInfo = NewsUtils.getSentimentInfo(link);
                String volatilityInfo = NewsUtils.getVolatilityInfo(sentimentInfo);
                if (country.isEmpty()) {
                    continue;
                }

                //Set all data to item
                NewsItem newsItem = new NewsItem();
                newsItem.setCountry(country);
                newsItem.setTime(time);
                newsItem.setEventName(eventName);
                newsItem.setActualInfo(actualInfo);
                newsItem.setForecastInfo(forecastInfo);
                newsItem.setPreviousInfo(previousInfo);
                newsItem.setSentiment(sentimentInfo);
                newsItem.setVolatility(volatilityInfo);

                //Generate response
                newsItems.add(newsItem);
                news.setNewsItems(newsItems);
            }
            newsCollectionDTO.setNewsCollection(list);
            return newsCollectionDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}