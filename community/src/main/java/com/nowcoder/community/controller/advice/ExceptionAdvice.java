package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)//annotations = Controller.class 表示只扫描Controller这个包下
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    //@ExceptionHandler({Exception.class})，修饰的方法表示处理异常的方法，括号内的参数表示处理的错误类型
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常: " + e.getMessage());
        //遍历所有异常信息，记入日志
        for(StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }

        //通过 request.getHeader("x-requested-with");获得请求的方式
        String xRequestedWith = request.getHeader("x-requested-with");
        //如果是异步请求，响应一个Json字符串显示服务器异常
        //否则响应错误路径
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1, "服务器异常!"));
        }else {
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }

}
