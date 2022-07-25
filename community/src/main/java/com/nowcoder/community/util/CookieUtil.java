package com.nowcoder.community.util;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//获取cookie该操作后面会经常复用，故将其封装起来
public class CookieUtil {
    public static String getValue(HttpServletRequest request,String name){
        if (request == null || name == null) {
            throw new IllegalArgumentException("参数为空!");
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
