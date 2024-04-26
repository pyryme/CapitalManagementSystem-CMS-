package com.xxx.cms.service.impl;


import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.Result;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.dao.GroupDao;
import com.xxx.cms.dao.impl.AllMemberDaoImpl;
import com.xxx.cms.dao.impl.GroupDaoImpl;
//import com.xxx.cms.model.Group;
import com.xxx.cms.dao.impl.UserDaoImpl;
import com.xxx.cms.service.GroupService;
import com.xxx.cms.service.TransactionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TransactionServiceImpl implements TransactionService {

    private final AllMemberDaoImpl allMemberDao = new AllMemberDaoImpl();
    private final UserDaoImpl userDao = new UserDaoImpl();
//
//    @Override
//    public Result getCreatedGroups_toTCHTML(String groupId) throws SQLException {
//
//        Result result_allGroupName;
//        //先拿groupName的数组
//        result_allGroupName = allMemberDao.getCreatedGroups(userId);//这个data的数据是ArrayList<String>,含义是name
//        ArrayList<String> array_allGroupName = (ArrayList<String>) result_allGroupName.getData();
//
//
//    }
}