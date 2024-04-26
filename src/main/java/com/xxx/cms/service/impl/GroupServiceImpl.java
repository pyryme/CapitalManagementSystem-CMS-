package com.xxx.cms.service.impl;


import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.GroupDao;
import com.xxx.cms.dao.impl.GroupDaoImpl;
//import com.xxx.cms.model.Group;
import com.xxx.cms.service.GroupService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    private final GroupDaoImpl groupDao = new GroupDaoImpl();





    @Override
    public Result  getGroupDetails(String groupName) throws SQLException{
        //接受下一层的数据
        GroupVO groupVO = new GroupVO();
        groupVO = groupDao.getGroupDetails(groupName);
        System.out.println("GSVIMPL ****groupVO.name  :"+groupVO.getGroupName());
        if(groupVO.isNullOrEmpty()){
            return Result.fail("获取失败",null);
        }else{
            return Result.success("获取成功",groupVO);
        }
    }









    //由于要返回所有数据，所以就用这个东西作为返回值,但是这个创建的时候就可以不用返回了 但还是写一下，用VO里面的null初始化来进行初始化
    @Override
    public Result createGroup(String groupName,String groupScale,String direction,String isPublic) throws SQLException{
//        //这里用new进行定义,让后让值为null就可以了。
//        GroupVO groupVO=new GroupVO();
//        groupVO.reset();


        //signup单独处理，其他的查询再让dao层的函数返回值进行统一。这里把返回值变为bool，抄user的signup的代码
        boolean resultCode = groupDao.createGroup(groupName, groupScale,direction,isPublic);
        //4.3如果插入数据失败：说明名已被使用，返回结果封装类并注明错误信息
        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("群组名已经被使用，请重新创建",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("创建成功",null);
        }
    }

    //这个特殊写的,获取id
    @Override
    public String  getGroupId(String groupName) throws SQLException{
        String  GroupId = groupDao.getGroupId(groupName);
        return GroupId;
    }





    //获取全部的公开的群组，和另一个函数配合达到getUnJoinGroups的目的
    @Override
    public Result getUnJoinGroups2() throws SQLException{
        //接受下一层的数据
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = groupDao.getUnJoinGroups2();

        if (arrayList.isEmpty()) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("没有数据",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("成功获得数据",arrayList);
        }

    }




}

//    @Override
//    public PageBean<Group> getJoinedGroups(String username, Integer currentCount, Integer countPerPage) throws SQLException {
//        //先这么写后两个参数为了后面的分页功能
//
//        //#后续补充，这里没有用上后面两个参数，之后再来用
//        // 调用dao层方法获取用户已加入的群组信息
//        ArrayList<Group> joinedGroups = groupDao.getJoinedGroups(username);
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
//        // 其他群组相关的服务方法，如创建群组、加入群组、退出群组等
//    }
