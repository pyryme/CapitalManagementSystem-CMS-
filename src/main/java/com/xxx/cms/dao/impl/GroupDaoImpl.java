//package com.xxx.cms.dao.impl;
//
//
//import com.xxx.cms.dao.GroupDao;
//import com.xxx.cms.model.Group;
//import com.xxx.cms.util.DBUtils;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class GroupDaoImpl implements GroupDao {
//
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
//
//}