package com.xxx.cms.service.impl;

import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
import com.xxx.cms.bean.vo.AllMemberVO;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.impl.AllMemberDaoImpl;
import com.xxx.cms.dao.impl.GroupDaoImpl;
//import com.xxx.cms.model.Group;
import com.xxx.cms.dao.impl.UserDaoImpl;
import com.xxx.cms.service.AllMemberService;
import com.xxx.cms.service.GroupService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//这Service层都用Result的数据类型作为函数的返回值，因为这个能够携带3个信息，很好

public class AllMemberServiceImpl implements AllMemberService {
    private final AllMemberDaoImpl allMemberDao = new AllMemberDaoImpl();
    private final UserDaoImpl userDao = new UserDaoImpl();

    //------------------------------transaction事务-----------------------
    @Override
    public Result getAllMemberDetails_Array(ArrayList<String> arrayList_allCreatedGroupName)throws SQLException{
        ArrayList<AllMemberVO> arrayList = new ArrayList<>();
        arrayList = allMemberDao.getAllMemberDetails_Array(arrayList_allCreatedGroupName);

        if (arrayList.isEmpty()) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("没有数据",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("成功获得数据",arrayList);
        }

    }





    @Override
    public Result getCreatedGroups_all(String userId) throws SQLException{
        ArrayList<AllMemberVO> arrayList = new ArrayList<>();
        arrayList = allMemberDao.getCreatedGroups_all(userId);

        if (arrayList.isEmpty()) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("没有数据",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("成功获得数据",arrayList);
        }

    }







    //-------------------------------group事务-------------------------------

    @Override
    public Result invite(String groupId,String groupName,String inviteUserId)throws SQLException{
        boolean resultCode ;

        if (!userDao.isUser_UserId(inviteUserId)){
//            System.out.println("用户不存在 +++++++++++++ AMSIMPL");
            return Result.fail("用户不存在，拉人失败！",null);
        }

        //去users表拿用户的userName,
        String inviteUserName = userDao.getUserName(inviteUserId);


        if (allMemberDao.inGroup(groupId,inviteUserId)){
            return Result.fail("用户已经在群里，拉人失败！",null);
        }


        //这里套用joinGroup的逻辑
        resultCode = allMemberDao.joinGroup(groupId,groupName,inviteUserId,inviteUserName,"0","0",0.0);

        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("拉人失败",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
//            System.out.println("拉人成功  +++++++++++++ AMSIMPL ");
            return Result.success("拉人成功AMSIMPL",null);
        }

    }



    @Override
    public Result quitGroupMember(String groupId,String userId)throws SQLException{
        boolean resultCode = allMemberDao.quitGroupMember(groupId,userId);

        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("退出失败",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("退出成功",null);
        }
    }




    @Override
    public Result quitGroupManager(String groupId,String userId,double balance)throws SQLException {
        //这里获取creator
        String creatorId = allMemberDao.getCreatorId(groupId);

        boolean resultCode = allMemberDao.quitGroupManager(groupId,userId,creatorId,balance);

        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("退出失败",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("退出成功",null);
        }
    }



    //这个拿的是单个的数据，不是数组
    @Override
    public Result getAllMemberDetails(String groupId,String userId)throws SQLException{
        AllMemberVO allMemberVO = new AllMemberVO();

        allMemberVO = allMemberDao.getAllMemberDetails(groupId,userId);

        if (allMemberVO.isNullOrEmpty()) {
            System.out.println("AMSVIMPL ***** result is empty");
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("查询失败",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("查询成功",allMemberVO);
        }
    }




    @Override
    public Result joinGroup(String groupId,String groupName,String userId,String userName)throws SQLException{
        boolean resultCode = allMemberDao.joinGroup(groupId,groupName,userId,userName,"0","0",0.0);

        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("加入失败",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("加入成功",null);
        }
    }

    @Override
    public Result createGroup(String groupId,String groupName,String userId,String userName,String isCreator,String isManager,double balance) throws SQLException{
        //signup单独处理，其他的查询再让dao层的函数返回值进行统一。这里把返回值变为bool，抄user的signup的代码
        boolean resultCode = allMemberDao.createGroup(groupId,groupName,userId,userName,"1","0",0.0);
        //4.3如果插入数据失败：说明用户名已被使用，返回结果封装类并注明错误信息
        if (!resultCode) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("群组名已经被使用，请重新创建",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("创建成功",null);
        }
    }


    @Override
    public Result getJoinedGroups(String userId) throws SQLException{
        //接受下一层的数据
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = allMemberDao.getJoinedGroups(userId);

        if (arrayList.isEmpty()) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("没有数据",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("成功获得数据",arrayList);
        }

    }

    @Override
    public Result getCreatedGroups(String userId) throws SQLException{
        //接受下一层的数据
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = allMemberDao.getCreatedGroups(userId);

        if (arrayList.isEmpty()) {
            //感觉用Result也可以，既可以携带信息true/false，也可以携带数据data
            return Result.fail("没有数据",null);
        }
        //4.4如果插入数据成功：返回结果封装类并注明成功信息
        else {
            return Result.success("成功获得数据",arrayList);
        }

    }


    //获取全部的没有自己的群组，和另一个函数配合达到getUnJoinGroups的目的
    @Override
    public Result getUnJoinGroups(String userId) throws SQLException{
        //接受下一层的数据
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = allMemberDao.getUnJoinGroups(userId);

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
