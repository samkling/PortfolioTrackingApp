package com.portfolioapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@Table(name = "holding", schema = "public", catalog = "postgres")
public class HoldingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "holding_id")
    private int holdingId;
    @Basic
    @Column(name = "ticker")
    private String ticker;
    @Basic
    @Column(name = "shares")
    private String shares;
    @Basic
    @Column(name = "cost_basis")
    private String costBasis;
    @Basic
    @Column(name = "portfolio_id")
    private int portfolioId;
    @Basic
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoldingEntity that = (HoldingEntity) o;
        return holdingId == that.holdingId && portfolioId == that.portfolioId && Objects.equals(ticker, that.ticker) && Objects.equals(shares, that.shares) && Objects.equals(costBasis, that.costBasis) && Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holdingId, ticker, shares, costBasis, portfolioId, createdOn);
    }
}
