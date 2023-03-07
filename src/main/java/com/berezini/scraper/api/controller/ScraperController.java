package com.berezini.scraper.api.controller;

import com.berezini.scraper.api.model.NewsCollectionDTO;
import com.berezini.scraper.api.service.IScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    private IScraperService scraperService;

    @GetMapping(path = "/getNews")
    public NewsCollectionDTO getNews()  {
        return scraperService.getNews();
    }

}