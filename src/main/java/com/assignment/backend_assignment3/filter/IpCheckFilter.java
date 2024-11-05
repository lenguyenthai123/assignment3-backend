//package com.assignment.backend_assignment3.filter;
//
//
//import java.io.IOException;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.beans.factory.annotation.Value;
//
//@Component
//public class IpCheckFilter extends OncePerRequestFilter {
//
//    // Tải giá trị của IP từ file cấu hình hoặc biến môi trường
//    @Value("${allowed.ip}")
//    private String ALLOWED_IP;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // Lấy địa chỉ IP của client
//        String clientIp = request.getRemoteAddr();
//
//        // Kiểm tra địa chỉ IP có khớp với địa chỉ IP được cho phép hay không
//        if (!ALLOWED_IP.equals(clientIp)) {
//            // Nếu IP không khớp, trả về lỗi 403 Forbidden
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Bạn không có quyền truy cập vào tài nguyên này");
//            return;
//        }
//
//        // Nếu IP khớp, tiếp tục chuỗi lọc
//        filterChain.doFilter(request, response);
//    }
//}