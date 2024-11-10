package com.assignment.backend_assignment3.interceptor;

import com.assignment.backend_assignment3.config.JwtTokenProvider;
import com.assignment.backend_assignment3.dto.UserAccountDto;
import com.assignment.backend_assignment3.service.UserAccountService;
import com.assignment.backend_assignment3.service.mapstruct.UserAccountMapper;
import com.assignment.backend_assignment3.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserAccountService service;

    @Autowired
    private UserAccountMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // Lấy jwt từ request
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // Lấy id user từ chuỗi jwt
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                // Lấy thông tin người dùng từ id
                UserAccountDto foundUser = service.loadUserById(userId);

                if (foundUser != null) {
                    // Nếu người dùng hợp lệ, set thông tin cho Security Context
                    UserContext.setCurrentUser(foundUser);
                    return true;
                }
            }
        } catch (Exception ex) {
            log.info("failed on set user authentication");

        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Bạn không có quyền truy cập vào tài nguyên này");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Implementation details
        UserContext.clear();
        return;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
