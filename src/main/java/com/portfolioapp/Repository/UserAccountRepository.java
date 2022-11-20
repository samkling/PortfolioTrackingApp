package com.portfolioapp.Repository;

import com.portfolioapp.Entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRespository extends JpaRepository<UserAccountEntity, Integer> {
}
