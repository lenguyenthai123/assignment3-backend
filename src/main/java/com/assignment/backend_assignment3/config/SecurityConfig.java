//package com.assignment.backend_assignment3.config;
//
//import com.assignment.backend_assignment3.filter.IpCheckFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    private final IpCheckFilter ipCheckFilter;
//
//    public SecurityConfig(IpCheckFilter ipCheckFilter) {
//        this.ipCheckFilter = ipCheckFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Thêm custom filter kiểm tra địa chỉ IP
//                .addFilterBefore(ipCheckFilter, IpCheckFilter.class)
//                .authorizeHttpRequests((requests) -> requests
//                        .anyRequest().authenticated()  // Các yêu cầu phải được xác thực (hoặc bạn có thể thay đổi logic)
//                );
//
//        return http.build();
//    }
//}