package com.xxx.cms.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        //1.设置字符编码,防止中文汉字报错
        try {
            System.out.println("into try1");
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=utf-8");
            System.out.println("try1 success");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        //2.获取访问路径的参数（方法名）
        //获取uri
        String uri = req.getRequestURI();
        //通过截取字符串获取参数
        String[] strs = uri.split("/");
        String methodName = strs[strs.length - 1];

        //3.通过反射执行方法
        //获取class字节码对象
        Class<? extends BaseServlet> cls = this.getClass();

        try {////////////////////////////////////////////////////////////////////这里有问题
            System.out.println("into try2");
            //获取方法对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println("get method success"+method);
            //执行方法
            method.invoke(this, req, resp);//？？？？？？？？？？？？？？？？是不是在根据请求的情况下转到User服务器的login函数去
            System.out.println("method invoke success");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("try2 get method or method.invoke fail");
            throw new RuntimeException(e);
        }
    }
}
