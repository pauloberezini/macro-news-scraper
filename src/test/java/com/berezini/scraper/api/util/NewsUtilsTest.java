package com.berezini.scraper.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewsUtilsTest {

    @Test
    public void testGetVolatilityInfoLow() {
        String result = NewsUtils.getVolatilityInfo("Low Volatility Expected");
        assertEquals("*", result);
    }

    @Test
    public void testGetVolatilityInfoModerate() {
        String result = NewsUtils.getVolatilityInfo("Moderate Volatility Expected");
        assertEquals("**", result);
    }

    @Test
    public void testGetVolatilityInfoHigh() {
        String result = NewsUtils.getVolatilityInfo("High Volatility Expected");
        assertEquals("***", result);
    }

    @Test
    public void testGetVolatilityInfoInvalid() {
        String result = NewsUtils.getVolatilityInfo("Invalid Volatility");
        assertEquals("", result);
    }
}