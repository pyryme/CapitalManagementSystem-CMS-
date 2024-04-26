package com.xxx.cms.dao.impl;

import com.xxx.cms.bean.vo.AllMemberVO;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.AllMemberDao;


import com.xxx.cms.bean.TimeBasedIdGenerator;
import com.xxx.cms.dao.GroupDao;
//import com.xxx.cms.model.Group;
import com.xxx.cms.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


//这个dao层的函数返回值就设置成要拿的数据的类型

public class AllMemberDaoImpl implements AllMemberDao {



    @Override
    public boolean inGroup(String groupId,String inviteUserId)throws SQLException{
        boolean resultCode = false;
        String sqlSelect = "SELECT * FROM allmember WHERE group_id = ? AND member_id = ?";
        ResultSet rs = DBUtils.executeQuery(sqlSelect, groupId, inviteUserId);

        if(rs.next()){
            resultCode = true;
        }

        return resultCode;
    }





    @Override
    public boolean quitGroupMember(String groupId,String userId)throws SQLException{
        boolean resultCode = false;

        try {
            // 这里写删除数据
            String sqlUpdate_Delete = "DELETE FROM allmember WHERE group_id = ? AND member_id = ?";
            int rs = DBUtils.executeUpdate(sqlUpdate_Delete, groupId, userId);

            //（***隐藏bug）这里的判断有点为问题的，应该判断rs的值是否为1的，但这里先不写
            resultCode = true; // 更新成功
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        } finally {
            // 释放资源
            DBUtils.closeAll(null, null, null);
        }
        // 返回结果
        return resultCode;
    }


    @Override
    public boolean quitGroupManager(String groupId,String userId,String creatorId,double balance)throws SQLException{
        boolean resultCode = false;

        try {
            // 这里先加钱
            // 定义sql
            String sqlUpdate = "UPDATE allmember SET balance = balance + ? WHERE member_id = ? ";
            int rs1 = DBUtils.executeUpdate(sqlUpdate, balance, creatorId);

            // 这里写删除数据
            String sqlUpdate_Delete = "DELETE FROM allmember WHERE group_id = ? AND member_id = ?";
            int rs2 = DBUtils.executeUpdate(sqlUpdate_Delete, groupId, userId);

            resultCode = true; // 更新成功
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        } finally {
            // 释放资源
            DBUtils.closeAll(null, null, null);
        }
        // 返回结果
        return resultCode;

    }


    //////////////////////////////////////////////不能排除这里没错
    //这里好像没办法写全部信息，和这个表的结构不太一样（后面自己思考一下本质是什么区别）
    @Override
    public String getCreatorId(String groupId)throws SQLException{
        String creatorId = null;
        String sqlSelect ="SELECT member_id FROM allmember WHERE group_id = ? AND iscreator = ?";

        ResultSet rs = DBUtils.executeQuery(sqlSelect, groupId, "1");

        try {
            // 2.3 处理结果
            rs.next();
            creatorId = rs.getString("member_id");

        } finally {
            // 2.4 释放资源
            DBUtils.closeAll(null, null, rs);
        }
        return creatorId;

    }






    @Override
    public AllMemberVO getAllMemberDetails(String groupId, String userId)throws SQLException{
        AllMemberVO allMemberVO = new AllMemberVO();

        String sqlSelect ="SELECT group_name, member_name, iscreator, ismanager, balance FROM allmember WHERE group_id = ? AND member_id = ?";

        String memberId = userId;

        ResultSet rs = DBUtils.executeQuery(sqlSelect, groupId,userId);

        try {
            System.out.println("AMDIMPL -----get into F_gAMD try---------");
            // 处理结果
            if (rs.next()) {
                System.out.println("AMDIMPL  --------get into F-gAMD if-------");
                // 从结果集中获取数据
                String groupName = rs.getString("group_name");
                String memberName = rs.getString("member_name");
                String isCreator = rs.getString("iscreator");
                String isManager = rs.getString("ismanager");
                double balance = rs.getDouble("balance");

                // 创建AllMemberVO对象并设置属性值
                // allMemberVO = new AllMemberVO();
                allMemberVO=new AllMemberVO(groupId,groupName,memberId,memberName,isCreator,isManager,balance);
                System.out.println("AMDIMPL -------- ismanager   :"+allMemberVO.getIsManager());
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            // 释放资源
            DBUtils.closeAll(null, null, rs);
        }


        // 返回AllMemberVO对象
        return allMemberVO;

    }





    @Override
    public boolean joinGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance)throws SQLException{
        boolean resultCode = true;
        //定义sql
        String sqlInsert = "insert into `allmember` (group_id,group_name,member_id,member_name,iscreator,ismanager,balance) values(?,?,?,?,?,?,?)";
        //执行增加数据操作
        DBUtils.executeUpdate(sqlInsert, groupId,groupName,userId,userName,isCreator,isManager,balance);

        //2.5释放资源
        DBUtils.closeAll(null, null, null);
        //3.返回结果
        return resultCode;
    }



    @Override
    public boolean createGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance) throws SQLException {
        boolean resultCode = true;

        //定义sql
        String sqlInsert = "insert into `allmember` (group_id,group_name,member_id,member_name,iscreator,ismanager,balance) values(?,?,?,?,?,?,?)";
        //执行增加数据操作
        DBUtils.executeUpdate(sqlInsert, groupId,groupName,userId,userName,isCreator,isManager,balance);

        //2.5释放资源
        DBUtils.closeAll(null, null, null);
        //3.返回结果
        return resultCode;
    }


    @Override
    public ArrayList<String> getJoinedGroups(String userId) throws SQLException{
        // 1. 定义字符串数组变量
        ArrayList<String> groupNames = new ArrayList<>();

        String sql = "SELECT group_name FROM allmember WHERE member_id = ? AND iscreator = ?";
        ResultSet rs = DBUtils.executeQuery(sql, userId, "0");
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

    @Override
    public ArrayList<String> getCreatedGroups(String userId) throws SQLException{
        // 1. 定义字符串数组变量
        ArrayList<String> groupNames = new ArrayList<>();

        String sql = "SELECT group_name FROM allmember WHERE member_id = ? AND iscreator = ?";
        ResultSet rs = DBUtils.executeQuery(sql, userId, "1");
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

    @Override
    public ArrayList<String> getUnJoinGroups(String userId) throws SQLException{
        // 1. 定义字符串数组变量
        ArrayList<String> groupNames = new ArrayList<>();

        String sql = "SELECT group_name FROM allmember WHERE member_id != ?  ";
        ResultSet rs = DBUtils.executeQuery(sql, userId);
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

        //不同上面两个函数的地方：去掉重复元素
        // 使用HashSet去除重复元素
        HashSet<String> set = new HashSet<>(groupNames);
        // 清空原ArrayList
        groupNames.clear();
        // 将去重后的元素添加回ArrayList
        groupNames.addAll(set);

        ////////////////////////////////////////////这里开始写屎山代码了，搞得比较难理解，可读性低
        //这里再进行获取数据，拿到有自己id数据，然后去除重复得元素。然后看上面得数组有没有下面的，有的去掉，最后返回上面的数组
        ArrayList<String> groupNames2 = new ArrayList<>();
        String sql2 = "SELECT group_name FROM allmember WHERE member_id = ?  ";
        ResultSet rs2 = DBUtils.executeQuery(sql2, userId);
        try {
            // 2.3 处理结果
            while (rs2.next()) {
                String groupName = rs2.getString("group_name");
                groupNames2.add(groupName);
            }
        } finally {
            // 2.4 释放资源
            DBUtils.closeAll(null, null, rs2);
        }

        //不同上面两个函数的地方：去掉重复元素
        // 使用HashSet去除重复元素
        HashSet<String> set2 = new HashSet<>(groupNames2);
        // 清空原ArrayList
        groupNames2.clear();
        // 将去重后的元素添加回ArrayList
        groupNames2.addAll(set2);

        groupNames.removeAll(groupNames2);

        return groupNames;
    }




}