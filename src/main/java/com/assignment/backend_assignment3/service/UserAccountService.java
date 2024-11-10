package com.assignment.backend_assignment3.service;

import com.assignment.backend_assignment3.dto.ApiResponseDto;
import com.assignment.backend_assignment3.dto.UserAccountDto;

public interface UserAccountService {
    ApiResponseDto register(UserAccountDto userAccount);

    ApiResponseDto login(UserAccountDto userAccount);

    UserAccountDto loadUserById(Long id);
}
