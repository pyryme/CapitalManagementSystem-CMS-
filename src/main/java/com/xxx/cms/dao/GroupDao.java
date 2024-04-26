package com.xxx.cms.dao;


//import com.xxx.cms.model.Group;

import com.xxx.cms.bean.vo.GroupVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDao {


    boolean createGroup(String groupName,String groupScale,String direction,String isPublic) throws SQLException;


    String getGroupId(String groupName)throws SQLException;

    ArrayList<String> getUnJoinGroups2() throws SQLException;

    GroupVO getGroupDetails(String groupName) throws SQLException;















        /**
         * 1.获取用户已加入的群组信息
         *
         * @param username 用户名
         * @return 返回用户已加入的群组名称列表
         * @throws SQLException mysql操作中可能遇到的异常
         */
//    ArrayList<Group> getJoinedGroups(String username) throws SQLException;

//    /**
//     * 2.其他群组相关的操作方法可以在这里添加
//     *
//     * @param groupName 群组名称
//     * @param username  用户名
//     * @return 返回操作是否成功的boolean类型变量
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
    // 示例方法：加入群组
//    boolean joinGroup(String groupName, String username) throws SQLException;

//    /**
//     * 3.退出群组
//     *
//     * @param groupName 群组名称
//     * @param username  用户名
//     * @return 返回操作是否成功的boolean类型变量
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
////    boolean leaveGroup(String groupName, String username) throws SQLException;
//
//    ArrayList<Group> getBarsFromRs(ResultSet rs) throws SQLException;
    // 如果有其他群组相关的操作方法，可以在这里继续添加
}
