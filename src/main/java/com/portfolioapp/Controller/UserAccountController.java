package com.portfolioapp.Controller;

import com.portfolioapp.Entity.UserAccountEntity;
import com.portfolioapp.Service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/allusers")
    @ResponseBody
    public List<UserAccountEntity> getAllUsers() {
        List<UserAccountEntity> users = this.userAccountService.getAllUsers();
        return users;
    }
}
