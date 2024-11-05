//package com.assignment.backend_assignment3.filter;
//
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.io.IOException;
//import java.util.Enumeration;
//
//public class IpFilter implements Filter {
//    private final String allowedOrigins;
//
//    // Constructor injection
//    public IpFilter(String allowedOrigins) {
//        this.allowedOrigins = allowedOrigins;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        System.out.println("Allowed Origins: " + allowedOrigins);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            System.out.println("allowedOrigins: " + allowedOrigins);
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            String allow = allowedOrigins;
//            String clientIp = httpRequest.getRemoteAddr(); // Lấy địa chỉ IP của client
//            String xForwardedForHeader = ((HttpServletRequest) request).getHeader("X-Forwarded-For");
//            String referer = httpRequest.getHeader("referer");
//            System.out.println("IpAddress: " + httpRequest.getRemoteAddr());
//            System.out.println("Client goi den la:  " + xForwardedForHeader);
//            System.out.println("Host:  " + ((HttpServletRequest) request).getHeader("Host"));
//            System.out.println("X-Real-IP:  " + ((HttpServletRequest) request).getHeader("X-Real-IP"));
//
//            // Chuyển ServletRequest thành HttpServletRequest để lấy header
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//
//            // Lấy tất cả các header từ yêu cầu
//            Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
//
//            System.out.println("Headers:");
//            while (headerNames.hasMoreElements()) {
//                String headerName = headerNames.nextElement();
//                String headerValue = httpServletRequest.getHeader(headerName);
//                System.out.println(headerName + ": " + headerValue);
//            }
//
//
//            if (referer.startsWith(allowedOrigins)) {
//                // Nếu IP trùng khớp, tiếp tục xử lý request
//                chain.doFilter(request, response);
//            } else {
//                // Nếu không trùng khớp, trả về lỗi 403 Forbidden
//                response.getWriter().write("Access Forbidden: Your IP is not allowed");
//                response.setContentType("text/plain");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().flush();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // Bỏ trống nếu không cần xử lý khi filter bị phá huỷ
//    }
//}