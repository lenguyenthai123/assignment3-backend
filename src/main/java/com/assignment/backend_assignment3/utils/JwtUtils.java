package com.assignment.backend_assignment3.utils;

import com.assignment.backend_assignment3.domain.UserAccount;
import com.assignment.backend_assignment3.repository.UserAccountRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Hàm tạo UUID
    public String generateUUIDToken() {
        // Sử dụng UUID để tạo một token
        return UUID.randomUUID().toString();
    }

    // Hàm kiểm tra xem có UserAccount nào chứa token không
    public String isTokenPresentInDb(String token) {
        // Kiểm tra trong cơ sở dữ liệu (DB) bằng cách sử dụng repository
        Optional<UserAccount> userAccount = userAccountRepository.findByAccessToken(token);
        return userAccount.map(UserAccount::getEmail).orElse(null);
    }
}