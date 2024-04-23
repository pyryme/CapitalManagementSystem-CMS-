package com.xxx.cms.dao;


import com.xxx.cms.model.User;

import java.sql.SQLException;
import java.util.ArrayList;


public interface UserDao {
    /**
     * 1.用户的注册操作
     *
     * @param name 获取到的要注册的用户名
     * @param pw   获取到的要注册的用户密码
     * @return 返回注册是否成功的boolean类型变量，成功则返回true
     * @throws SQLException mysql操作中可能遇到的异常
     */
    boolean signup(String name, String pw) throws SQLException;

    /**
     * 2.查询用户是否存在
     *
     * @param name 获取到的要查询的用户名
     * @return 返回boolean类型变量表示用户是否存在
     * @throws SQLException mysql操作中可能遇到的异常
     */
    boolean isUser(String name)throws SQLException;

    /**
     * 3.获取指定用户的密码
     *
     * @param name 获取到的要登录的用户名
     * @return 返回指定用户的密码
     * @throws SQLException mysql操作中可能遇到的异常
     */
    String getPassword(String name) throws SQLException;

    /**
     * 4.获取用户id
     *
     * @param name 用户的用户名
     * @return 返回用户id
     * @throws SQLException mysql操作中可能遇到的异常
     */
    Integer getUserId(String name) throws SQLException;

    /**
     * 5.获取用户名
     *
     * @param userId 用户id
     * @return 返回用户名
     * @throws SQLException mysql操作中可能遇到的异常
     */



    String getUserName(Integer userId) throws SQLException;

    String getUserAvatar(String username) throws SQLException;

    String getUserPhone(String username) throws SQLException;













    /**
     *6.上传图片的文件路径
     *
     * @param avatar 图片的路径
     * @return 返回boolean表示图片修改是否成功
     * @throws SQLException
     */
    boolean changeSaveImage(String name,String avatar) throws SQLException;

    /**
     * 7.上传更改的手机号和用户名
     *
     * @param oldname
     * @param newname
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    boolean updatePhoneUsername(String oldname,String newname,String phoneNumber) throws SQLException;












//    /**
//     * 6.分页查询获取贴吧成员的集合
//     *
//     * @param barId        贴吧id
//     * @param currentCount 当前要开始查询的页码
//     * @param countPerPage 每一页要查询多少条数据
//     * @return 返回吧的集合
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
//    ArrayList<User> getUsers(Integer barId, Integer currentCount, Integer countPerPage) throws SQLException;
//
//    /**
//     * 7.获取贴吧用户的总数
//     *
//     * @param barId 贴吧id
//     * @return 返回贴吧用户总数
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
//    Integer getTotalCount(Integer barId) throws SQLException;
}
