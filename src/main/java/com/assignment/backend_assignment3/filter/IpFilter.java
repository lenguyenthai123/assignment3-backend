package com.assignment.backend_assignment3.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.itextpdf.kernel.pdf.PdfName.Filter;

public class IpFilter implements Filter {

    // IP được phép
    private static final String ALLOWED_IP = "192.168.1.100";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Có thể bỏ trống nếu không cần cấu hình ban đầu
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String clientIp = httpRequest.getRemoteAddr(); // Lấy địa chỉ IP của client

        if (clientIp.equals(ALLOWED_IP)) {
            // Nếu IP trùng khớp, tiếp tục xử lý request
            chain.doFilter(request, response);
        } else {
            // Nếu không trùng khớp, trả về lỗi 403 Forbidden
            response.getWriter().write("Access Forbidden: Your IP is not allowed");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().flush();
        }
    }

    @Override
    public void destroy() {
        // Bỏ trống nếu không cần xử lý khi filter bị phá huỷ
    }
}