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
    private AuthenticationInterceptor authInterceptor;

    @Value("${allow.origin}")
    private String ALLOWED_ORIGINS;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOrigins(ALLOWED_ORIGINS) // Chỉ cho phép từ frontend React chạy ở localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các HTTP method được phép
                .allowedHeaders("*") // Cho phép mọi header
                .allowCredentials(true) // Cho phép gửi cookie (nếu cần)
                .maxAge(3600); // Cache thời gian preflight trong 1 giờ
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register"); // Loại trừ các URL không cần xác thực
    }
}