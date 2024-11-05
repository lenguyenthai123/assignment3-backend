//package com.assignment.backend_assignment3.config;
//
//import com.assignment.backend_assignment3.filter.IpFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Value;
//
//@Configuration
//public class FilterConfig {
//    @Value("${ip.custom}")
//    private String allowedOrigins;
//
//    @Bean
//    public FilterRegistrationBean<IpFilter> ipFilter() {
//        FilterRegistrationBean<IpFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new IpFilter(allowedOrigins));
//        registrationBean.addUrlPatterns("/*"); // Áp dụng filter cho tất cả URL
//
//        return registrationBean;
//    }
//}