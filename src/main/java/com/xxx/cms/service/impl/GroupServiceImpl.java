//package com.xxx.cms.service.impl;
//
//
//import com.xxx.cms.bean.PageBean;
//import com.xxx.cms.bean.Result;
//import com.xxx.cms.dao.impl.GroupDaoImpl;
//import com.xxx.cms.model.Group;
//import com.xxx.cms.service.GroupService;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupServiceImpl implements GroupService {
//
//    private final GroupDaoImpl groupDao = new GroupDaoImpl();
//
//    @Override
//    public PageBean<Group> getJoinedGroups(String username, Integer currentCount, Integer countPerPage) throws SQLException {
//        //先这么写后两个参数为了后面的分页功能
//
//        //#后续补充，这里没有用上后面两个参数，之后再来用
//        // 调用dao层方法获取用户已加入的群组信息
//        ArrayList<Group>  joinedGroups = groupDao.getJoinedGroups(username);
//        Integer totalCount = 1;//这里先乱写，后续拿来写分页功能
//        return new PageBean<>(totalCount, joinedGroups);
//
////
//
////        // 判断返回结果是否为空
////        if (joinedGroups == null || joinedGroups.isEmpty()) {
////            return Result.fail("用户未加入任何群组！", null);
////        } else {
////            return Result.success("获取已加入的群组信息成功！", joinedGroups);
////        }
////    }
//
//    // 其他群组相关的服务方法，如创建群组、加入群组、退出群组等
//}
