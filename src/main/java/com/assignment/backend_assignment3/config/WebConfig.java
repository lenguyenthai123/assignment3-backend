package com.assignment.backend_assignment3.config;

import com.assignment.backend_assignment3.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Value("${ip.custom}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOrigins(allowedOrigins) // Cho phép yêu cầu từ React (localhost:3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP được cho phép
                .allowedHeaders("*") // Cho phép tất cả các header
                .allowCredentials(true); // Cho phép gửi thông tin xác thực (cookie, authentication headers)
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Đăng ký interceptor cho tất cả các endpoint
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**")  // Áp dụng cho tất cả các endpoint
                .excludePathPatterns("/user/login", "/user/register", "/public/**");  // Loại trừ các đường dẫn không cần xác thực
    }
}