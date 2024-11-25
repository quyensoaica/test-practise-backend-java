package com.practise.test.config;

import com.practise.test.middleware.AuthenticationMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationMiddleware> authenticationFilter() {
        FilterRegistrationBean<AuthenticationMiddleware> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationMiddleware());  // Đảm bảo bạn có filter này
        registrationBean.addUrlPatterns("/api/*");  // Định nghĩa các URL cần filter
        return registrationBean;
    }
}
