package cn.tedu.store.config;

import cn.tedu.store.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置拦截器链
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        HandlerInterceptor interceptor = new LoginInterceptor();

        //白名单集合
        List<String> paths = new ArrayList<>();

        //静态白名单
        paths.add("/js/**");
        paths.add("/css/**");
        paths.add("/images/**");
        paths.add("/bootstrap3/**");


        paths.add("/web/register.html");
        paths.add("/web/login.html");
        paths.add("/web/index.html");
        paths.add("/web/product.html");


        paths.add("/users/reg");
        paths.add("/users/login");
        paths.add("/districts/**");
        paths.add("/products/**");
        //addPathPatterns() 添加黑名单
        //.excludePathPatterns() 添加白名单
        registry.addInterceptor(interceptor).
                // "/**"所有界面都拦截
                        addPathPatterns("/**").
                excludePathPatterns(paths);
    }
}



