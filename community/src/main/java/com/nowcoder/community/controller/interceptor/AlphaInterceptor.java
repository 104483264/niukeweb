package com.nowcoder.community.controller.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@component （把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）
//泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），
//我们就可以使用@Component来标注这个类。
@Component
public class AlphaInterceptor implements HandlerInterceptor {

    //拦截器可以说相当于是个过滤器：就是把不想要的或不想显示的内容给过滤掉。
    // 拦截器可以抽象出一部分代码可以用来完善原来的方法。同时可以减轻代码冗余，提高重用率，降低耦合度。
    private static final Logger logger = LoggerFactory.getLogger(AlphaInterceptor.class);
    // 在Controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("preHandle: " + handler.toString());
        return true;
    }

    // 在Controller之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("postHandle: " + handler.toString());
    }

    //在TemplatEngine之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("afterCompletion: " + handler.toString());
    }
}
