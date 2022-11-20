package com.portfolioapp.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "holdings", schema = "public", catalog = "postgres")
public class HoldingsEntity {
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

    public int getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(int holdingId) {
        this.holdingId = holdingId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getCostBasis() {
        return costBasis;
    }

    public void setCostBasis(String costBasis) {
        this.costBasis = costBasis;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoldingsEntity that = (HoldingsEntity) o;
        return holdingId == that.holdingId && portfolioId == that.portfolioId && Objects.equals(ticker, that.ticker) && Objects.equals(shares, that.shares) && Objects.equals(costBasis, that.costBasis) && Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holdingId, ticker, shares, costBasis, portfolioId, createdOn);
    }
}
