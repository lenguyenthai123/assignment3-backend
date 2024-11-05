package com.assignment.backend_assignment3.service.impl;

import com.assignment.backend_assignment3.dto.ApiResponseDto;
import com.assignment.backend_assignment3.service.DashBoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {

    @Override
    public ApiResponseDto getDashBoardData(HttpServletRequest request) {
        // Get some information to identify user from access token
        String username = request.getAttribute("username").toString();

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatusCode("200");
        apiResponseDto.setMessage("Success");
        apiResponseDto.setData("Hello " + username);

        return apiResponseDto;
    }
}
