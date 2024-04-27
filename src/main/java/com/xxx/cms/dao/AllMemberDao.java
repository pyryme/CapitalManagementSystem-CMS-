package com.xxx.cms.dao;

import com.xxx.cms.bean.vo.AllMemberVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public interface AllMemberDao {
    ArrayList<AllMemberVO> getAllMemberDetails_Array(ArrayList<String> arrayList_allCreatedGroupName)throws SQLException;

    ArrayList<AllMemberVO> getCreatedGroups_all(String userId)throws SQLException;

    boolean inGroup(String groupId,String inviteUserId)throws SQLException;

    boolean quitGroupMember(String groupId,String userId)throws SQLException;

    boolean quitGroupManager(String groupId,String userId,String creatorId,double balance)throws SQLException;

    String getCreatorId(String groupId)throws SQLException;

    AllMemberVO getAllMemberDetails(String groupId, String userId)throws SQLException;

    boolean joinGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance)throws SQLException;

    boolean createGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance) throws SQLException;

    ArrayList<String> getJoinedGroups(String userId) throws SQLException;

    ArrayList<String> getCreatedGroups(String userId) throws SQLException;

    ArrayList<String> getUnJoinGroups(String userId) throws SQLException;
}
