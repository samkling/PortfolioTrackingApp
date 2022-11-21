package com.portfolioapp.Repository;

import com.portfolioapp.Entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity,Integer> {

}
