package com.assignment.backend_assignment3.service.impl;

import com.assignment.backend_assignment3.dto.ApiResponseDto;
import com.assignment.backend_assignment3.dto.UserAccountDto;
import com.assignment.backend_assignment3.service.DashBoardService;
import com.assignment.backend_assignment3.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {

    @Override
    public ApiResponseDto getDashBoardData(HttpServletRequest request) {
        // Get some information to identify user from access token
        UserAccountDto user = UserContext.getCurrentUser();
        String username = request.getAttribute("username").toString();

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatusCode("200");
        apiResponseDto.setMessage("Success");
        apiResponseDto.setData("Hello " + username);

        return apiResponseDto;
    }
}
