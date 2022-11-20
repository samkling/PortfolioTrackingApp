package com.portfolioapp.Service;

import com.portfolioapp.Entity.UserAccountEntity;
import com.portfolioapp.Repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccountEntity> getAllUsers() {
        List<UserAccountEntity> users = this.userAccountRepository.findAll();
        return users;
    }
}
