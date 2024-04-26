package com.xxx.cms.dao.impl;


import com.xxx.cms.bean.TimeBasedIdGenerator;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.GroupDao;
//import com.xxx.cms.model.Group;
import com.xxx.cms.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDaoImpl implements GroupDao {


    @Override
    public GroupVO getGroupDetails(String groupName) throws SQLException{
        GroupVO groupVO = new GroupVO();

        //2.1定义sql
        String sqlSelect = "SELECT group_id, group_scale, direction, is_public FROM `groups`  WHERE group_name = ? ";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sqlSelect, groupName);

        try {
            // 处理结果
            if (rs.next()) {
                // 从结果集中获取数据
                String groupId = rs.getString("group_id");
                String groupScale = rs.getString("group_scale");
                String direction = rs.getString("direction");
                String isPublic = rs.getString("is_public");

                // 创建GroupVO对象并设置属性值
                groupVO = new GroupVO(groupId, groupName, groupScale, direction, isPublic);
            }
        } finally {
            // 释放资源
            DBUtils.closeAll(null, null, rs);
        }

        System.out.println("GDAOIMPL groupVO.name   :"+groupVO.getGroupName());
        // 返回GroupVO对象
        return groupVO;

    }






    @Override
    public boolean createGroup(String groupName,String groupScale,String direction,String isPublic) throws SQLException{
        boolean resultCode = true;
        System.out.println("GDIMPL ----groupName  :"+groupName);
        System.out.println("GDIMPL ----direction  :"+direction);
        //2.检查用户名是否重复
        //2.1定义sql
        String sqlSelect = "select * from `groups` where group_name= ? ";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sqlSelect, groupName);
        //2.3如果查询到该用户名
        if (rs.next()) {
            resultCode = false;
        }
        //2.4如果没有查到该用户名
        else {
            System.out.println("GDIMPL Function_CreateGroup  no this user is right");
            //将一整套信息都存进去
            //屎山代码，加上groupid，但无法再写入session中（要调用session需要HttpServletRequest req这个参数，所以只能在servlet层进行）
            String group_id = TimeBasedIdGenerator.generateId();


            //定义sql
            String sqlInsert = "insert into `groups` (group_id,group_name,group_scale,direction,is_public) values(?,?,?,?,?)";
            //执行增加数据操作
            DBUtils.executeUpdate(sqlInsert, group_id, groupName,groupScale,direction,isPublic);


        }
        //2.5释放资源
        DBUtils.closeAll(null, null, rs);
//        System.out.println("resultCode  :"+resultCode);

        //3.返回结果
        return resultCode;

    }


    @Override
    public String getGroupId(String groupName)throws SQLException {
        //1.定义用户id变量
        String groupId;

        //2.执行JDBC操作
        //2.1定义sql
        String sql = "select group_id from `groups` where group_name= ? ";
        //2.2执行查询操作
        ResultSet rs = DBUtils.executeQuery(sql, groupName);
        //2.3处理结果
        rs.next();
        groupId = rs.getString("group_id");
        //2.4释放资源
        DBUtils.closeAll(null, null, rs);

        //3.返回用户id
        return groupId;

    }


    @Override
    public ArrayList<String> getUnJoinGroups2() throws SQLException{
        // 1. 定义字符串数组变量
        ArrayList<String> groupNames = new ArrayList<>();

        String sql = "SELECT group_name FROM `groups` WHERE is_public = ? ";
        ResultSet rs = DBUtils.executeQuery(sql,  "1");
        try {
            // 2.3 处理结果
            while (rs.next()) {
                String groupName = rs.getString("group_name");
                groupNames.add(groupName);
            }
        } finally {
            // 2.4 释放资源
            DBUtils.closeAll(null, null, rs);
        }
        return groupNames;
    }




}
//    @Override
//    public ArrayList<Group> getJoinedGroups(String username) throws SQLException {
//        // 定义结果集合
//        ArrayList<Group> joinedGroups = new ArrayList<>();
//
//        //获取数据库连接
//        Connection conn = DBUtils.getConn();
//
//        // 定义查询SQL语句
//        String sql = "SELECT groups FROM user_group_membership WHERE username = ?";
//
//        // 执行查询操作
//        ResultSet rs = DBUtils.executeQuery(sql, username);
//
//        joinedGroups=getJoinedGroups(rs);
//
//        // 释放资源
//        DBUtils.closeAll(conn, null, rs);
//
//        // 返回结果
//        return joinedGroups;
//    }
//
//    // 其他群组相关的DAO方法，如创建群组、加入群组、退出群组等
//}
//
//
//
//    @Override
//    public ArrayList<Group> getBarsFromRs(ResultSet rs) throws SQLException {
//        //创建贴吧集合
//        ArrayList<Group> groups = new ArrayList<>();
//
//        while (rs.next()) {
//            //获取查询到的吧名，并以此创建吧的对象
////            Integer groupId = rs.getInt("bar_id");
//            Integer groupId = 1;
//            String groupName = rs.getString("group_name");
//            String groupDescription = new UserDaoImpl().getUserName(rs.getInt("direction"));
//            String creatorUsername = "未写";
//            String groupPictureSrc = "未写";
//            Group group = new Group(groupId,groupName,  groupDescription,  creatorUsername,  groupPictureSrc);
//            //将获取的的吧的对象添加到吧的集合中
//            groups.add(group);
//        }
//
//        //返回结果
//        return groups;
//    }

