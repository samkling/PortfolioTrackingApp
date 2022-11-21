package com.portfolioapp.Controller;

import com.portfolioapp.DTO.PortfolioDTO;
import com.portfolioapp.Service.PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public PortfolioDTO getPortfolioByUserId(final @PathVariable Integer userId) {
        try {
            return this.portfolioService.getUserPortfolio(userId);
        } catch (Exception e) {
            System.err.println("Error Getting Portfolio By User ID");
            System.err.println(e.getMessage());
            return new PortfolioDTO();
        }
    }
}
