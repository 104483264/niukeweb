package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {

    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密
    // hello -> abc123def456
    // hello + 3e4a8 -> abc123def456abc
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //AJAX
    //Asynchronous JavaScript and XML
    //异步的JavaScript与XML，不是一-i 门新技术，只是一个新的术语。
    //使用AJAX，网页能够将增量更新呈现在页面上，而不需要刷新整个页面。
    //虽然X代表XML,但目前JSON的使用比XML更加普遍。
    //https://developer.mozilla.org/zh- CN/docs/Web/Guide/AJAX

    //① 服务器给浏览器返回编码（code），比如0代表什么意思，1代表什么意思
    //② 服务器给浏览器返回提示信息（msg），成果或失败
    //③ 还可能返回业务数据（map)

    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","chen");
        map.put("age",25);
        System.out.println(getJSONString(0,"ok",map));
    }
}
