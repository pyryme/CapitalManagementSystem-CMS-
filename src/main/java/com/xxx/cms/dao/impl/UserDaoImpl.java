package com.xxx.cms.dao.impl;


import com.xxx.cms.dao.UserDao;
import com.xxx.cms.model.User;
import com.xxx.cms.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDaoImpl implements UserDao {
    @Override
    public boolean signup(String name, String pw) throws SQLException {
        //1.定义结果变量
        boolean result = true;

        //2.检查用户名是否重复
        //2.1定义sql
        String sqlSelect = "select * from users where username=?";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sqlSelect, name);
        //2.3如果查询到该用户名
        if (rs.next()) {
            result = false;
        }
        //2.4如果没有查到该用户名
        else {
            System.out.println("UDIMPL Function_signup  no this user is right");
            //将用户名和密码存储到user表中
            //定义sql
            String sqlInsert = "insert into users (username,password) values(?,?)";
            //执行增加数据操作
            DBUtils.executeUpdate(sqlInsert, name, pw);
        }
        //2.5释放资源
        DBUtils.closeAll(null, null, rs);

        //3.返回结果
        return result;
    }

    @Override
    public boolean isUser(String name) throws SQLException {
        System.out.println("UDIMPL into Function_isUser");
        //1.定义结果变量
        boolean result = false;

        //2.执行JDBC操作
        //2.1定义sql
        //#1数据类型解释：sql的数据类型写成string，并且包含?（这里好像可以防止sql注入）
        String sql = "select * from users where username=?";
        System.out.println("UDIMPL define and init sql");

        //2.2执行查询操作---------------------关键行
        //#1数据类型解释：把string类型的sql语句 传入DBUtils.executeQuery(sql, name)中。
        //   用resultset这个类来实例化一个rs，然后将这个执行的语句赋值给这个东西，对数据库不同的操作就决定了怎么处理这个rs
        //   这里是查询，就用if的语句来判断
        ResultSet rs = DBUtils.executeQuery(sql, name);
        System.out.println("UDIMPL init rs"+rs);
        //2.3处理结果
        if (rs.next()) {
            result = true;
        }
        //2.4释放资源-------------------------
        DBUtils.closeAll(null, null, rs);

        //3.返回结果
        System.out.println("UDIMPL finish Function_isUser,return:"+result);
        return result;
    }

    @Override
    public String getPassword(String name) throws SQLException {
        //1.执行JDBC操作
        //1.1定义sql
        String sql = "select password from users where username=?";
        //1.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, name);
        //1.3处理结果
        rs.next();
        String password = rs.getString("password");
        //1.4释放资源--------------------------
        DBUtils.closeAll(null, null, rs);

        //2.返回指定用户密码
        return password;
    }


    @Override
    public Integer getUserId(String name) throws SQLException {
        //1.执行JDBC操作
        //1.1定义sql
        String sql = "select user_id from users where username=?";
        //1.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, name);
        //1.3处理结果
        rs.next();
        Integer userId = rs.getInt("user_id");
        //1.4释放资源
        DBUtils.closeAll(null, null, rs);

        //2.返回用户id
        return userId;

    }

    @Override
    public String getUserName(Integer userId) throws SQLException {
        //1.定义用户id变量
        String userName;

        //2.执行JDBC操作
        //2.1定义sql
        String sql = "select username from users where user_id=?";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, userId);
        //2.3处理结果
        rs.next();
        userName = rs.getString("username");
        //2.4释放资源
        DBUtils.closeAll(null, null, rs);

        //3.返回用户id
        return userName;

    }


    @Override
    public String getUserAvatar(String username) throws SQLException {
        //1.设置返回值的定义
        String avatar;

        //2.执行JDBC操作
        //2.1定义sql
        String sql = "SELECT avatar FROM users WHERE username = ? ";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, username);
        //2.3处理结果
        rs.next();
        avatar = rs.getString("avatar");
        //2.4释放资源
        DBUtils.closeAll(null, null, rs);

        //3.返回用户id
        return avatar;

    }
//////////////////////////////////////////////临时标记

    @Override
    public String getUserPhone(String username) throws SQLException {
        //1.设置返回值的定义
        String phone;

        //2.执行JDBC操作
        //2.1定义sql
        String sql = "SELECT phone FROM users WHERE username = ? ";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, username);
        //2.3处理结果
        rs.next();
        phone = rs.getString("phone");
        //2.4释放资源
        DBUtils.closeAll(null, null, rs);

        //3.返回用户id
        return phone;

    }

    @Override
    public boolean changeSaveImage(String name,String avatar) throws SQLException{

        boolean result=false;

        String sqlSelect = "select * from users where username=?";
        //执行查询操作
        ResultSet rs = DBUtils.executeQuery(sqlSelect, name);
        //如果查询到该用户名,就设置true的返回值，并且执行更新操作
        if (rs.next()) {
            result = true;

            String sql ="UPDATE users SET avatar = ? WHERE username = ?";
            DBUtils.executeUpdate(sql,avatar,name);
            DBUtils.closeAll(null, null, rs);
        }


//        String sql ="UPDATE users SET avatar = ? WHERE username = ?";
//        DBUtils.executeUpdate(sql,avatar,name);
//        DBUtils.closeAll(null, null, rs);

        return result;
    }

    @Override
    public boolean updatePhoneUsername(String oldname,String newname,String phoneNumber) throws SQLException{
        boolean result=false;

        String sqlSelect = "select * from users where username=?";
        //执行查询操作
        ResultSet rs = DBUtils.executeQuery(sqlSelect, oldname);
        //如果查询到该用户名,就设置true的返回值，并且执行更新操作
        if (rs.next()) {
            result = true;

            String sql1 ="UPDATE users SET username = ? WHERE username = ?";
            DBUtils.executeUpdate(sql1,newname,oldname);

            String sql2 = "UPDATE users SET phone = ? WHERE username = ?";
            DBUtils.executeUpdate(sql2,phoneNumber,newname);

            DBUtils.closeAll(null, null, rs);
        }


        return result;
    }





//    @Override
//    public ArrayList<User> getUsers(Integer barId, Integer currentCount, Integer countPerPage) throws SQLException {
//        //创建获取到的吧的集合
//        ArrayList<User> users = new ArrayList<>();
//
//        //获取数据库连接
//        Connection conn = DBUtils.getConn();
//        //定义sql
//        String sql = "select * from user_bar_membership where bar_id = ? order by membership_id limit ?,?";
//        //执行查询操作
//        ResultSet rs = DBUtils.executeQuery(sql, barId, (currentCount - 1) * countPerPage, countPerPage);
//        //处理结果
//        while (rs.next()) {
//            //获取查询到的用户信息
//            Integer userId = rs.getInt("user_id");
//            String userName = getUserName(userId);
//            //将获取到的用户的对象添加到用户的集合中
//            users.add(new User(userId, userName));
//        }
//        //释放资源
//        DBUtils.closeAll(conn, null, rs);
//
//        //返回结果
//        return users;
//    }
//
//    @Override
//    public Integer getTotalCount(Integer barId) throws SQLException {
//        //创建记录贴吧用户总数的对象
//        int totalCount = 0;
//
//        //获取数据库连接
//        Connection conn = DBUtils.getConn();
//        //定义sql
//        String sql = "select count(user_id) from user_bar_membership where bar_id = ?";
//        //执行查询操作
//        ResultSet rs = DBUtils.executeQuery(sql, barId);
//        //处理结果
//        while (rs.next()) {
//            totalCount = rs.getInt("count(user_id)");
//        }
//        //释放资源
//        DBUtils.closeAll(conn, null, rs);
//
//        //返回结果
//        return totalCount;
//    }
}