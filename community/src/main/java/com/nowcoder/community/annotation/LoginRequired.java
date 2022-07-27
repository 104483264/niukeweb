package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 没登陆之前，不能让用户通过输入路径访问不该访问的页面。
 * 众多请求都有同样的逻辑，使用拦截器。
 * 通过添加注解，自定义注解。
 *
 * 元注解：
 *
 * @Target：自定义的注解可以声明在哪些地方
 * @Retention：保留时间或有效时间
 * @Document：生成文档时是否加入文档
 * @Inherited：子类继承时是否也有效
 * 读取注解（通过反射）：
 *
 * Method.getDeclearedAnnotations：获取方法上所有注解
 * Method.getAnnotation(Class annotationClass)：获取指定类型的注解
 */


@Target(ElementType.METHOD) //@Target：自定义的注解可以声明在哪些地方
@Retention(RetentionPolicy.RUNTIME) //@Retention：保留时间或有效时间

public @interface LoginRequired {

}
