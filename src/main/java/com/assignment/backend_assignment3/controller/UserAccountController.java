package com.assignment.backend_assignment3.controller;

import com.assignment.backend_assignment3.dto.ApiResponseDto;
import com.assignment.backend_assignment3.dto.UserAccountDto;
import com.assignment.backend_assignment3.service.UserAccountService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

    private final String root = "/user";

    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation("Register new user")
    @PostMapping(value = root + "/register")
    public ResponseEntity<?> register(@RequestBody UserAccountDto userAccount, HttpServletRequest request) {
        ApiResponseDto responseDto = userAccountService.register(userAccount);
        return ResponseEntity.ok(responseDto);
    }

    @ApiOperation("Login user")
    @PostMapping(value = root + "/login")
    public ResponseEntity<?> login(@RequestBody UserAccountDto userAccount, HttpServletRequest request) {
        ApiResponseDto responseDto = userAccountService.login(userAccount);
        return ResponseEntity.ok(responseDto);
    }

}
