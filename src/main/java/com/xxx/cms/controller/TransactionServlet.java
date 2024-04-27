package com.xxx.cms.controller;


//import com.xxx.cms.model.Group;
import com.alibaba.fastjson.JSON;
import com.xxx.cms.bean.vo.AllMemberVO;
import com.xxx.cms.bean.vo.GroupTransactionVO;
import com.xxx.cms.bean.vo.GroupTransactionVO2;
import com.xxx.cms.service.impl.AllMemberServiceImpl;
import com.xxx.cms.service.impl.GroupServiceImpl;
import com.xxx.cms.service.impl.TransactionServiceImpl;
import com.xxx.cms.service.impl.UserServiceImpl;
        import com.xxx.cms.bean.Result;

        import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
        import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Transaction/*")
public class TransactionServlet extends BaseServlet {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final GroupServiceImpl groupService = new GroupServiceImpl();
    private final AllMemberServiceImpl allMemberService = new AllMemberServiceImpl();
    private final TransactionServiceImpl transactionService= new TransactionServiceImpl();


    //这个函数根据数据的来源，不用调用自己的数据库表，所以在这一层进行数据的封装
    public void getCreatedGroups_toTCHTML(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException {
        Result result_allCreatedGroupName;
        Result result_allMemberDetail;
        Result result_allCreatedGroup;
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");




        //////////////////////*****************************下面的可读性极差，估计只有写的时候能比较清楚的了解，之后就很难懂了，维护性不好；
        /////////////////////下面的代码一定要写好注释，命名有问题，有歧义，包括数据类型和功能的歧义
        /////////////////////理解的核心：记住数据结构：的结构。整体就是GroupTransactionVO,里面member部分就是GroupTransactionVO2的数据结构
//        createdGroups: [
//          {
//            groupId: 1,
//            groupName: "Group 1",
//            myBalance: 100,
//            members: [
//              { userId: 1, userName: "User 1", isManager: true, balance: 50 },
//              { userId: 2, userName: "User 2", isManager: false, balance: 30 },
//              // 其他成员...
//            ]
//          },
//          {
//            groupId: 2,
//            groupName: "Group 2",
//            myBalance: 150,
//            members: [
//              { userId: 1, userName: "User 1", isManager: false, balance: 70 },
//              { userId: 3, userName: "User 3", isManager: false, balance: 80 },
//              // 其他成员...
//            ]
//          },

        //这里先拿groupName的数组(用userId iscreator得到Name，没有写得到ArrayList<AllMemberVO>的函数.....所以就索性写一个能够获得这个的函数)
        //这个函数的前3个信息的数组拿得到，这个信息的name可以去拿member的信息***********************关键
        result_allCreatedGroup = allMemberService.getCreatedGroups_all(userId);//返回值是ArrayList<AllMemberVO>，是这个用户创造的自己的数据
        ArrayList<AllMemberVO> allCreatedGroup= (ArrayList<AllMemberVO>)result_allCreatedGroup.getData();
        //**这里拿前3个数据作为数组**//这里先不拿，等下面拿到信息了一起拿再来封装



        //这里直接拿groupName，给后面的用
        result_allCreatedGroupName = allMemberService.getCreatedGroups(userId);//这个data的数据是ArrayList<String>,含义是name
        ArrayList<String> arrayList_allCreatedGroupName = (ArrayList<String>) result_allCreatedGroupName.getData();

        //调试
        if(arrayList_allCreatedGroupName==null){
            System.out.println("TSL      arrayList_allCreatedGroupName  is null!!!!!!!!!!!!!!!!");
        }else {
            System.out.println("TSL     arrayList_allCreatedGroupName:   "+arrayList_allCreatedGroupName);
        }


        //用arrayList_allCreatedGroupName获取后面的ArrayList<AllMemberVO>的信息***********关键
        result_allMemberDetail = allMemberService.getAllMemberDetails_Array(arrayList_allCreatedGroupName);//返回值是数组Arraylist<AllMemberVo>，是这个群组所有成员的（之前的命名限定没有写好，所以这里写的很难受，命名有问题了，不易懂）
        ArrayList<AllMemberVO> allMemberDetail = (ArrayList<AllMemberVO>)result_allMemberDetail.getData();
        //调试
        if(allMemberDetail==null){
            System.out.println("TSL  allMemberDetail   is null");
        }else {
            System.out.println("TSL  allMemberDetail   :"+allMemberDetail.get(1).getMemberName());
        }



        ArrayList<GroupTransactionVO> groupTransactionVOs = new ArrayList<>();

        for (int i = 0; i < arrayList_allCreatedGroupName.size(); i++) {
            AllMemberVO allMemberVO = allCreatedGroup.get(i);

            GroupTransactionVO groupTransactionVO1 = new GroupTransactionVO();
            groupTransactionVO1.setGroupId(allMemberVO.getGroupId());
            groupTransactionVO1.setGroupName(allMemberVO.getGroupName());
            groupTransactionVO1.setMyBalance(allMemberVO.getBalance());

            ArrayList<GroupTransactionVO2> One_groupTransactionVO2S = new ArrayList<>(); // Move this line inside the outer loop

            for (int j = 0; j < allMemberDetail.size(); j++) {
                AllMemberVO One_allMemberDetial = allMemberDetail.get(j);
                if (One_allMemberDetial.getGroupName().equals(arrayList_allCreatedGroupName.get(i))) {
                    GroupTransactionVO2 groupTransactionVO2 = new GroupTransactionVO2();

                    groupTransactionVO2.setUserId(One_allMemberDetial.getMemberId());
                    groupTransactionVO2.setUserName(One_allMemberDetial.getMemberName());
                    groupTransactionVO2.setIsManager(One_allMemberDetial.getIsManager());
                    groupTransactionVO2.setBalance(One_allMemberDetial.getBalance());

                    One_groupTransactionVO2S.add(groupTransactionVO2);
                }
            }

            groupTransactionVO1.setArrayList_members(One_groupTransactionVO2S);
            groupTransactionVOs.add(groupTransactionVO1);
        }

        ///////**************不可删除****************
        //下面部分自己写的，但是逻辑有点问题，上面GPT写的，完美运行.就是把 One_groupTransactionVO2S.add(groupTransactionVO2);这一行往上调。
//        //按道理来说name的顺序应该是一一对应的
//  1      ArrayList<GroupTransactionVO> groupTransactionVOs = new ArrayList<>();//这个是最终要传到html的
//
//        ArrayList<GroupTransactionVO2> One_groupTransactionVO2S = new ArrayList<>();
//  1      for(int i=0;i<arrayList_allCreatedGroupName.size();i++){//外层的是改变不同的群组
//  1          AllMemberVO allMemberVO =  allCreatedGroup.get(i);
//
//  1          GroupTransactionVO groupTransactionVO1 = new GroupTransactionVO();
//  1         groupTransactionVO1.setGroupId(allMemberVO.getGroupId());
//  1          groupTransactionVO1.setGroupName(allMemberVO.getGroupName());
//  1          groupTransactionVO1.setMyBalance(allMemberVO.getBalance());
//
//            ///////////////startstartstartstart
//            GroupTransactionVO2 groupTransactionVO2 =new GroupTransactionVO2();
//
//
//            for(int j=0;j<allMemberDetail.size();j++){//这里想不到好的方法，只能全遍历，然后里面用if筛选
//                AllMemberVO One_allMemberDetial= allMemberDetail.get(j);
//                if(allMemberDetail.get(j).getGroupName().equals(arrayList_allCreatedGroupName.get(i))){
//                   groupTransactionVO2 = new GroupTransactionVO2();
//
//                    groupTransactionVO2.setUserId(One_allMemberDetial.getMemberId());
//                    groupTransactionVO2.setUserName(One_allMemberDetial.getMemberName());
//                    groupTransactionVO2.setIsManager(One_allMemberDetial.getIsManager());
//                    groupTransactionVO2.setBalance(One_allMemberDetial.getBalance());
//                    System.out.println("if: i j  "+i+""+j);
//                }
//                System.out.println(" i j  "+i+""+j);
//                System.out.println(" allMemberDetail.get(j).getMemberName()    arrayList_allCreatedGroupName.get(i)  "+allMemberDetail.get(j).getGroupName()+"  "+arrayList_allCreatedGroupName.get(i));
//
//
//            }
//            One_groupTransactionVO2S.add(groupTransactionVO2);
//            //////////////endendendendendend
//            groupTransactionVO1.setArrayList_members(One_groupTransactionVO2S);
//            groupTransactionVOs.add(groupTransactionVO1);
//        }

        System.out.println("TSL   ****   "+groupTransactionVOs.get(0).getArrayList_members().get(0).getUserId());

        //---------------------------------上面可能有错，下面的解析等吃完饭再来
        //**然后在这里完成封装,(拿 输入的群组名字 对应的数据的后部分的)**
        //**然后进行解析，传到前端中**
        String jsonGroups = JSON.toJSONString(groupTransactionVOs);


        // 设置响应内容类型
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 将JSON数据发送到前端
        resp.getWriter().write(jsonGroups);






    }

}