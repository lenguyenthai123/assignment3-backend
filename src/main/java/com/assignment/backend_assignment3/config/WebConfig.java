package com.assignment.backend_assignment3.config;

import com.assignment.backend_assignment3.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Sử dụng @Value để lấy giá trị của biến môi trường từ application.properties
    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("allowedOrigins: " + allowedOrigins);
        registry.addMapping("/**")  // Cho phép tất cả các endpoint
                .allowedOrigins(allowedOrigins)  // Địa chỉ của React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Các phương thức HTTP được phép
                .allowedHeaders("*")  // Cho phép tất cả các headers
                .allowCredentials(true) // Nếu bạn muốn gửi Cookie hoặc Authentication Header
                .maxAge(3600);  // Đặt thời gian cache của kết quả CORS (1 giờ)
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Đăng ký interceptor cho tất cả các endpoint
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")  // Áp dụng cho tất cả các endpoint
                .excludePathPatterns("/user/login", "/user/register", "/public/**");  // Loại trừ các đường dẫn không cần xác thực
    }
}