package com.example.springboot_vue_backend.config;


import com.example.springboot_vue_backend.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //作用是对所有路径应用拦截器，除了/index.html。
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {//把我们自己的url和文件夹对应起来
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "F:/DeskTop/Spring+Vue/workspace/img/");
    }
}