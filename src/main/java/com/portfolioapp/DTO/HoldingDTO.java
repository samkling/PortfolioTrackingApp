package com.portfolioapp.DTO;

import lombok.Data;

@Data
public class HoldingDTO {
    private String name;
    private String ticker;
    private String shares;
    private String costBasis;
    private String marketPrice;
    private String positionBasis;
    private String marketValue;
    private String dayChange;
    private String dollarGain;
    private String percentGain;
    private String dividendYield;
    private String dividendPercent;
    private String positionIncome;
    private String percentOfPortfolio;
}
