package com.portfolioapp.Service;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class RestService {
    static final private String STOCK_INFORMATION_API_URL = "stockinformation.url";
    static final private String STOCK_PRICE_API_URL_START = "stockPriceChangeInformation.url.start";
    static final private String STOCK_PRICE_API_URL_END = "stockPriceChangeInformation.url.end";
    private final RestTemplate restTemplate;
    private final Environment environment;

    public RestService(RestTemplateBuilder restTemplateBuilder, Environment environment) {
        this.restTemplate = restTemplateBuilder.build();
        this.environment = environment;
    }

    public LinkedHashMap<String,Object> getStockInformation(String ticker) throws ParseException {
        String url = this.environment.getProperty(STOCK_INFORMATION_API_URL) + ticker;

        String response = this.restTemplate.getForObject(url, String.class);
        if (response == null) {
            System.err.println("Stock Information Data Not Found");
            return null;
        }
        JSONParser parser = new JSONParser(response);
        return parser.parseObject();
    }

    public LinkedHashMap<String,Object> getStockPriceData(String ticker) throws ParseException {
        String url = this.environment.getProperty(STOCK_PRICE_API_URL_START) + ticker + this.environment.getProperty(STOCK_PRICE_API_URL_END);
        String response = this.restTemplate.getForObject(url, String.class);
        if (response == null) {
            System.err.println("Stock Price Data Not Found");
            return null;
        }
        JSONParser parser = new JSONParser(response);
        return parser.parseObject();
    }
}
