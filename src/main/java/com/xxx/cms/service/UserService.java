package com.xxx.cms.service;

import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
import com.xxx.cms.model.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 1.用户注册的service业务层
     *
     * @param name 获取到的要注册的用户名
     * @param pw1  获取到的要注册的密码
     * @param pw2  获取到的再次输入的注册的密码
     * @return 返回结果封装类
     * @throws SQLException mysql操作中可能遇到的异常
     */
    Result signup(String name, String pw1, String pw2) throws SQLException;


    /**
     * 2.用户登录的service业务层
     *
     * @param name 获取到的要登录的用户名
     * @param pw   获取到的要登录的密码
     * @return 返回结果封装类
     * @throws SQLException mysql操作中可能遇到的异常
     */
    Result login(String name, String pw) throws SQLException;

    /**
     * 3.获取用户名
     *
     * @param userId 用户id
     * @return 返回用户名
     * @throws SQLException mysql操作中可能遇到的异常
     */
    String getUserName(String userId) throws SQLException;


    String getUserAvatar(String username) throws SQLException;

    String getUserPhone(String username) throws SQLException;

    /**
     *4.上传更改的图片
     *
     * @param avatar 获取到的图片的路径
     * @return 返回结果封装类
     * @throws SQLException mysql操作中可能遇到的异常
     */
    Result changeSaveImage(String name,String avatar)  throws SQLException;


    /**
     * 5.上传更改的名字和手机号
     *
     * @param oldname
     * @param newname
     * @param phoneNumber
     * @return
     */
    Result updatePhoneUsername(String oldname,String newname,String phoneNumber)throws SQLException;




//    /**
//     * 4.获取当前用户是否为该贴吧吧主
//     *
//     * @param userId               用户id
//     * @param barModeratorUserName 贴吧吧主用户名
//     * @return 返回整数表示当前用户是否为该贴吧吧主
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
//    Integer getIsBarModerator(Integer userId, String barModeratorUserName) throws SQLException;
//
//    /**
//     * 5.获取贴吧成员的集合
//     *
//     * @param barId        贴吧id
//     * @param currentCount 当前要开始查询的页码
//     * @param countPerPage 每一页要查询多少条数据
//     * @return 返回当前页贴吧的封装类对象
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
//    PageBean<User> getPageBean(Integer barId, Integer currentCount, Integer countPerPage) throws SQLException;
}
