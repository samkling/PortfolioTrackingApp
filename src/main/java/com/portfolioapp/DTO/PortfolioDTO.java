package com.portfolioapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioDTO {
    private String name;
    private List<HoldingDTO> holdings;
}
