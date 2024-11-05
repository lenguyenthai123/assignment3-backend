package com.assignment.backend_assignment3.interceptor;

import com.assignment.backend_assignment3.repository.UserAccountRepository;
import com.assignment.backend_assignment3.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private UserAccountRepository repository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lấy Authorization header
        String authHeader = request.getHeader("Authorization");

        // Kiểm tra xem header có chứa Bearer token hay không
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Lấy token sau "Bearer "

            try {
                // Xác thực token
                String username = jwtUtils.isTokenPresentInDb(token);
                if (username != null) {
                    // Nếu token hợp lệ, tiếp tục xử lý yêu cầu
                    request.setAttribute("username", username);  // Lưu thông tin username vào request
                    return true;
                }
            } catch (Exception e) {
                // Trường hợp token không hợp lệ hoặc hết hạn
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized: Invalid or expired token");
                return false;
            }
        }

        // Nếu không có header Authorization hoặc token không hợp lệ, trả về lỗi 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: Missing or invalid Authorization header");
        return false;
    }
}
