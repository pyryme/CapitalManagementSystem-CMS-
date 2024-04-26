package com.xxx.cms.service;



import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
//import com.xxx.cms.model.Group;

import java.sql.SQLException;

public interface GroupService {



    Result createGroup(String groupName,String groupScale,String direction,String isPublic) throws SQLException;

    String  getGroupId(String groupName) throws SQLException;

    Result getUnJoinGroups2() throws SQLException;

    Result  getGroupDetails(String groupName) throws SQLException;










//    /**
//     * 获取用户已加入的群组信息
//     *
//     * @param username 用户名
//     * @return 返回结果封装类
//     * @throws SQLException mysql操作中可能遇到的异常
//     */
//    PageBean<Group> getJoinedGroups(String username, Integer currentCount, Integer countPerPage) throws SQLException;
//    // 其他群组相关的服务方法，如创建群组、加入群组、退出群组等

}
