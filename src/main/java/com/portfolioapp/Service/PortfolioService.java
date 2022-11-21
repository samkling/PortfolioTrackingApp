package com.portfolioapp.Service;

import com.portfolioapp.DTO.HoldingDTO;
import com.portfolioapp.DTO.PortfolioDTO;
import com.portfolioapp.Entity.HoldingEntity;
import com.portfolioapp.Entity.PortfolioEntity;
import com.portfolioapp.Repository.PortfolioRepository;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final HoldingService holdingService;

    public PortfolioService(PortfolioRepository portfolioRepository, HoldingService holdingService) {
        this.portfolioRepository = portfolioRepository;
        this.holdingService = holdingService;
    }

    public PortfolioDTO convertPortfolio(PortfolioEntity portfolio, List<HoldingDTO> holdings) {
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setName(portfolio.getName());
        portfolioDTO.setHoldings(holdings);
        return portfolioDTO;
    }

//    Name,Ticker,Shares,Cost,Market,Basis,MKT Val,Day Change,Gain,%Gain,Dividend Yield,Div%,Income,% of Portfolio,Ticker
    public PortfolioDTO getUserPortfolio(final Integer userId) throws ParseException {
        Optional<PortfolioEntity> optionalPortfolioEntity = this.portfolioRepository.findById(userId);
        if (optionalPortfolioEntity.isEmpty()) {
            return null;
        }
        PortfolioEntity portfolio = optionalPortfolioEntity.get();

        List<HoldingEntity> holdings = this.holdingService.getHoldingsByPortfolioId(portfolio.getPortfolioId());
        List<HoldingDTO> holdingDTOList = this.holdingService.convertHoldingsList(holdings);

        return this.convertPortfolio(portfolio,holdingDTOList);
    }
}
