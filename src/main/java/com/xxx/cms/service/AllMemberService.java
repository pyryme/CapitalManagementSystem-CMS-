package com.xxx.cms.service;


import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
//import com.xxx.cms.model.Group;

import java.sql.SQLException;
import java.util.ArrayList;


public interface AllMemberService {
    Result getAllMemberDetails_Array(ArrayList<String> arrayList_allCreatedGroupName)throws SQLException;

    Result getCreatedGroups_all(String userId) throws SQLException;

    Result invite(String groupId,String groupName,String inviteUserId)throws SQLException;

    Result quitGroupMember(String groupId,String userId)throws SQLException;

    Result quitGroupManager(String groupId,String userId,double balance)throws SQLException;

    Result getAllMemberDetails(String groupId,String userId)throws SQLException;

    Result joinGroup(String groupId,String groupName,String userId,String userName)throws SQLException;

    Result createGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance) throws SQLException;

    Result getJoinedGroups(String userId) throws SQLException;

    Result getCreatedGroups(String userId) throws SQLException;

    Result getUnJoinGroups(String userId) throws SQLException;

}
