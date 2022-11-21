package com.portfolioapp.Service;

import com.portfolioapp.DTO.HoldingDTO;
import com.portfolioapp.Entity.HoldingEntity;
import com.portfolioapp.Repository.HoldingRepository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HoldingService {
    private final HoldingRepository holdingRepository;
    private final RestService restService;

    public HoldingService(HoldingRepository holdingRepository, RestService restService) {
        this.holdingRepository = holdingRepository;
        this.restService = restService;
    }
    public List<HoldingEntity> getHoldingsByPortfolioId(Integer portfolioId) {
        return this.holdingRepository.findByPortfolioId(portfolioId);
    }

    public List<HoldingDTO> convertHoldingsList(List<HoldingEntity> holdingEntityList) throws ParseException {
        List<HoldingDTO> holdingDTOList = new ArrayList<>();
        double totalValue = 0.0;

        for(HoldingEntity holding: holdingEntityList) {

            HoldingDTO holdingDTO = new HoldingDTO();

            if ( holding.getTicker().equals("$CASH") ) { //constant for cash
                holdingDTO = this.getCashHoldingDTO(holding.getShares());
                totalValue += Double.parseDouble(holding.getShares());
            } else {
                Map<String,String> stockInfo = this.getStockInformation(holding.getTicker());
                double positionBasis = Double.parseDouble(holding.getCostBasis()) * Double.parseDouble(holding.getShares());
                double marketValue = Double.parseDouble(stockInfo.get("marketPrice")) * Double.parseDouble(holding.getShares());
                double dollarGain = marketValue - positionBasis;
                double percentGain = dollarGain / positionBasis;
                double positionIncome = Double.parseDouble(holding.getShares()) * Double.parseDouble(stockInfo.get("dividendYield"));
                System.out.println(holding.getTicker());
                System.out.println(stockInfo.get("name"));
                String stockName = stockInfo.get("name").contains("-") ? stockInfo.get("name").split("-")[0] : stockInfo.get("name");

                holdingDTO.setName(stockName);
                holdingDTO.setTicker(stockInfo.get("ticker"));
                holdingDTO.setShares(holding.getShares());
                holdingDTO.setCostBasis("$ " + this.roundAmount(holding.getCostBasis()));
                holdingDTO.setMarketPrice("$ " + this.roundAmount(stockInfo.get("marketPrice")));
                holdingDTO.setPositionBasis("$ " + this.roundAmount(String.valueOf(positionBasis)));
                holdingDTO.setMarketValue("$ " + this.roundAmount(String.valueOf(marketValue)));
                holdingDTO.setDayChange("$ " + this.roundAmount(stockInfo.get("netChange")));
                holdingDTO.setDollarGain("$ " + this.roundAmount(String.valueOf(dollarGain)));
                holdingDTO.setPercentGain(this.roundAmount(String.valueOf(percentGain)) + " %");
                holdingDTO.setDividendYield("$ " + this.roundAmount(stockInfo.get("dividendYield")));
                holdingDTO.setDividendPercent(this.roundAmount(stockInfo.get("dividendPercent")) + " %");
                holdingDTO.setPositionIncome("$ " + this.roundAmount(String.valueOf(positionIncome)));
                totalValue += marketValue;
            }
            holdingDTOList.add(holdingDTO);
        }
        System.out.println("total value: " + totalValue);
        for (HoldingDTO holdingDTO : holdingDTOList) {
            double currentHoldingValue = Double.parseDouble(holdingDTO.getMarketValue().substring(1));
            double percentOfPortfolio = currentHoldingValue / totalValue * 100.0;
            holdingDTO.setPercentOfPortfolio(this.roundAmount(String.valueOf(percentOfPortfolio)) + " %");
        }

        return holdingDTOList;
    }

    public Map<String,String> getStockInformation(String ticker) throws ParseException {
        Map<String,Object> stockInfoDataMap = this.restService.getStockInformation(ticker);
        Map<String,Object> stockPriceDataMap = this.restService.getStockPriceData(ticker);
        HashMap<String, Object> stockPriceMap = (LinkedHashMap<String, Object>) stockPriceDataMap.get(ticker);
        HashMap<String,Object> stockInfoMap = (LinkedHashMap<String, Object>) stockInfoDataMap.get(ticker);
        HashMap<String, Object> fundamentalMap = (LinkedHashMap<String, Object>) stockInfoMap.get("fundamental");

        Map<String,String> stockInformation = new HashMap<>();
        stockInformation.put("name", (String) stockInfoMap.get("description"));
        stockInformation.put("ticker", (String) fundamentalMap.get("symbol"));
        stockInformation.put("dividendYield", fundamentalMap.get("dividendAmount").toString());
        stockInformation.put("dividendPercent",fundamentalMap.get("dividendYield").toString());
        stockInformation.put("netChange", stockPriceMap.get("netChange").toString());
        stockInformation.put("marketPrice", stockPriceMap.get("closePrice").toString());

        return stockInformation;
    }

    public String roundAmount(String amount) {
        double rounded = Math.round(Double.parseDouble(amount) * 100.0) / 100.0;
        return String.valueOf(rounded);
    }

    public HoldingDTO getCashHoldingDTO(String cashAmount) {
        HoldingDTO cashHolding = new HoldingDTO();
        cashHolding.setTicker("cash");
        cashHolding.setName("cash");
        cashHolding.setShares(cashAmount);
        cashHolding.setCostBasis("$ 1");
        cashHolding.setMarketPrice("$ 1");
        cashHolding.setPositionBasis("$ " + cashAmount);
        cashHolding.setMarketValue("$ " + cashAmount);
        cashHolding.setDayChange("$ 0");
        cashHolding.setDollarGain("$ 0");
        cashHolding.setPercentGain("0 %");
        cashHolding.setDividendPercent("0 %");
        cashHolding.setDividendYield("$ 0");
        cashHolding.setPositionIncome("$ 0");
        return cashHolding;
    }
}
