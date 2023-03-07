package com.berezini.scraper.api.util;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class NewsUtils {

    public static String getVolatilityInfo(String sentimentInfo) {
        if(sentimentInfo.equalsIgnoreCase("Low Volatility Expected")){
            return "*";
        }else if(sentimentInfo.equalsIgnoreCase("Moderate Volatility Expected")){
            return "**";
        }else if (sentimentInfo.equalsIgnoreCase("High Volatility Expected")){
            return "***";
        }else return "";
    }

    public static String getCountry(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(3).childNodes().get(1);
            return node.getWholeText().replace(" ","");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getTime(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(1).childNodes().get(0);
            return node.getWholeText();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getEventName(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(7).childNodes().get(0);
            return node.getWholeText();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPreviousInfo(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(13).childNodes().get(0);
            return node.getWholeText();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getActualInfo(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(9).childNodes().get(0);
            return node.getWholeText();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getForecastInfo(Element link) {
        try {
            TextNode node = (TextNode) link.childNodes().get(11).childNodes().get(0);
            return node.getWholeText();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSentimentInfo(Element link) {
        try {
            return link.childNodes().get(5).attr("title");
        } catch (Exception e) {
            return "";
        }
    }
}
