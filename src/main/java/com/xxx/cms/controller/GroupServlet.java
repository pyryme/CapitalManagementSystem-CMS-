//package com.xxx.cms.controller;
//
//
//import com.xxx.cms.bean.PageBean;
//import com.xxx.cms.bean.vo.GroupVO;
//import com.xxx.cms.constant.ResultConst;
//import com.xxx.cms.model.Group;
//import com.xxx.cms.service.impl.GroupServiceImpl;
//import com.xxx.cms.service.impl.UserServiceImpl;
//import com.alibaba.fastjson.JSON;
//import com.xxx.cms.bean.Result;
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.nio.file.Paths;
//import java.sql.SQLException;
//import java.sql.SQLOutput;
//import java.util.Collection;
//import java.util.List;
//import java.util.UUID;
//@WebServlet("/Group/*")
//public class GroupServlet extends BaseServlet {
//
//    private final GroupServiceImpl groupService = new GroupServiceImpl();
//
//    /**
//     * 获取用户已加入的群组信息
//     *
//     * @param req  请求体对象
//     * @param resp 响应体对象
//     */
//    public void getJoinedGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
//        // 创建返回结果封装类对象
//        Result result;
//
//        // 获取用户名
//        String username = req.getParameter("username");
//
//        try {
//            // 调用service层方法获取已加入的群组信息 ///////////////////////////////////////////标记，这里报错
//            PageBean<Group> pageBean= groupService.getJoinedGroups(username,1,1);
////            result = new Result(ResultConst.SUCCESS, "获取已加入的群组信息成功", joinedGroups);
//// 将数据传递给前端页面进行展示
//        PrintWriter writer = resp.getWriter();
//        String jsonString = JSON.toJSONString(pageBean);
//        writer.write(jsonString);
//
//        } catch (SQLException e) {
//            result = new Result(ResultConst.FAIL, "获取已加入的群组信息失败", null);
//            throw new RuntimeException(e);
//        }
//
//        // 将数据返回给前端
//
//
////        resp.getWriter().write(JSON.toJSONString(result));
//    }
//
//    // ... 其他方法，如添加、修改、删除群组等
//}
