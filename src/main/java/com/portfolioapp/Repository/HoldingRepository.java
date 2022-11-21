package com.portfolioapp.Repository;

import com.portfolioapp.Entity.HoldingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<HoldingEntity,Integer> {

    @Query("SELECT h FROM HoldingEntity h WHERE h.portfolioId = :portfolioId")
    List<HoldingEntity> findByPortfolioId(Integer portfolioId);
}
