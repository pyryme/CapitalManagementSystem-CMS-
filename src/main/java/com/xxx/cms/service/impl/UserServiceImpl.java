package com.xxx.cms.service.impl;


import com.xxx.cms.bean.Result;
import com.xxx.cms.dao.impl.UserDaoImpl;
import com.xxx.cms.service.UserService;
import com.xxx.cms.util.MD5Utils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author fangyufan
 */
public class UserServiceImpl implements UserService {
    private final UserDaoImpl  userDao = new UserDaoImpl();

    @Override
    public Result signup(String name, String pw1, String pw2) throws SQLException {
        //1.校验用户名格式
        //如果不符合：返回结果封装类并注明错误信息
        String userNameFormat = ".{1,30}";
        if (!name.matches(userNameFormat)) {
            System.out.println("USCIMPL 名字不符合正则化  username:"+name);
            return Result.fail("用户名格式错误！请重新输入！", null);
        }
        System.out.println("USCIMPL name right");

        //2.校验两次密码是否一致
        //如果不一致：返回结果封装类并注明错误信息
        if (!pw1.equals(pw2)) {
            return Result.fail("您输入的两次密码不一致，请重新注册！", null);
        }


        //3.校验密码格式
        //如果不符合：返回结果封装类并注明错误信息
        String passwordFormat = "\\w{6,20}";
        if (!pw1.matches(passwordFormat)) {
            return Result.fail("密码格式错误！请重新输入！", null);
        }

        //4.调用dao层方法插入数据
        //4.1对密码进行MD5加盐加密
        String md5WithSalt = MD5Utils.getMD5WithSalt(pw1);
        //4.2创建变量ret用于判断插入数据是否成功----------------核心行
        boolean ret = userDao.signup(name, md5WithSalt);
        //4.3如果插入数据失败：说明用户名已被使用，返回结果封装类并注明错误信息
        if (!ret) {
            return Result.fail("用户名已被使用，请重新注册！", null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("注册成功！", null);
        }
    }

    @Override
    public Result login(String name, String pw) throws SQLException {
        //1.查询用户是否存在
        System.out.println("USCIMPL into login success");

        //#1数据类型解释：这个把string类型的参数name传入了DAO.isUser里面
        if (!userDao.isUser(name)){
            System.out.println("USCIMPL 用户不存在");
            return Result.fail("该用户不存在！请重新登录！",null);
        }

        //2.校验密码是否正确
        //2.1获取输入的用户名的对应的密码
        //#1数据类型解释：这个把string类型的参数name传入了DAO.getPassword()里面
        String md5WithSaltPassword = userDao.getPassword(name);
        //2.2对输入的密码进行加密，并与数据库中记录的数据进行校验
        String inputMD5WithSalt = MD5Utils.getMD5WithSalt(pw,md5WithSaltPassword);
        //2.3如果密码错误，返回结果封装类并注明错误信息
        if (!inputMD5WithSalt.equals(md5WithSaltPassword)) {
            return Result.fail("密码错误！请重新登录！", null);
        }
        //2.4如果密码正确：返回结果封装类并注明成功信息、用户id
        else {
            //获取用户id
            Integer useId = userDao.getUserId(name);
            return Result.success("登录成功！", useId);
        }
    }

    @Override
    public String getUserName(Integer userId) throws SQLException {
        //调用dao层方法获取用户名
        return userDao.getUserName(userId);
    }


    @Override
    public String getUserAvatar(String username) throws SQLException {
        //调用dao层方法获取用户名
        return userDao.getUserAvatar(username);
    }
/////////////////////////////////////////////////////////临时标记

    @Override
    public String getUserPhone(String username) throws SQLException {
        //调用dao层方法获取用户名
        return userDao.getUserPhone(username);
    }

    @Override
    public Result changeSaveImage(String name,String avatar)  throws SQLException{
        //创建变量ret用于判断数据是否成功执行----------------核心行
        boolean ret = userDao.changeSaveImage(name,avatar);
        if (!ret) {
            return Result.fail("图片上传更新失败！", null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("图片更新成功！", null);
        }
    }

    @Override
    public Result updatePhoneUsername(String oldname,String newname,String phoneNumber)throws SQLException{
        //创建变量ret用于判断数据是否成功执行----------------核心行
        boolean ret = userDao.updatePhoneUsername(oldname,newname,phoneNumber);
        if (!ret) {
            return Result.fail("手机号和用户名更改失败！", null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("手机号和用户名更改成功！", null);
        }
    }







//    @Override
//    public Integer getIsBarModerator(Integer userId, String barModeratorUserName) throws SQLException {
//        //调用dao层方法执行获取贴吧吧主用户id
//        Integer barModeratorUserId = userDao.getUserId(barModeratorUserName);
//        //如果贴吧吧主id和当前用户id一致，则返回1：表示当前用户为该贴吧吧主；否则返回0：表示不是吧主
//        if (barModeratorUserId.equals(userId)) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public PageBean<User> getPageBean(Integer barId, Integer currentCount, Integer countPerPage) throws SQLException {
//        //调用dao层的方法获取成员的集合、成员的总数
//        ArrayList<User> users = userDao.getUsers(barId, currentCount, countPerPage);
//        Integer totalCount = userDao.getTotalCount(barId);
//
//        //返回结果
//        return new PageBean<>(totalCount, users);
//    }
}
