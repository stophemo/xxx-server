//package com.stp.xxx.config.filter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class ResourcesConfig implements WebMvcConfigurer {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // 设置访问源地址，（*）表示匹配所有。
//        config.addAllowedOriginPattern("*");
//        // 设置访问源请求头
//        config.addAllowedHeader("*");
//        // 设置访问源请求方法
//        config.addAllowedMethod("*");
//        // 有效期 1800秒，设置预检请求（OPTIONS请求）的缓存时间，1800秒意味着客户端可以缓存这个设置1800秒
//        config.setMaxAge(1800L);
//        // 添加映射路径，拦截一切请求
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        // 返回新的CorsFilter
//        return new CorsFilter(source);
//    }
//}