package com.xxx.cms.controller;


import com.xxx.cms.bean.PageBean;
import com.xxx.cms.bean.TimeBasedIdGenerator;
import com.xxx.cms.bean.vo.AllMemberVO;
import com.xxx.cms.bean.vo.GroupVO;
import com.xxx.cms.constant.ResultConst;
//import com.xxx.cms.model.Group;
import com.xxx.cms.service.AllMemberService;
import com.xxx.cms.service.impl.AllMemberServiceImpl;
import com.xxx.cms.service.impl.GroupServiceImpl;
import com.xxx.cms.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import com.xxx.cms.bean.Result;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;

@WebServlet("/Group/*")
public class GroupServlet extends BaseServlet {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final GroupServiceImpl groupService = new GroupServiceImpl();
    private final AllMemberServiceImpl allMemberService = new AllMemberServiceImpl();



    public void invite(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException {
        Result result;
        boolean haveThisUser;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取html传来的信息groupName
        String groupId = req.getParameter("groupId");
        String groupName = req.getParameter("groupName");
        String inviteUserId = req.getParameter("inviteUserId");

        //这些判断放到service中，因为service才能存放信息，学习login。先看看有没有这个人。（这里把这个函数写大了，输入userid然后返回全部的信息，但是时间不太够，所以用比较简单的单个返回值先写）
//        haveThisUser = userService.haveThisUser（）

        result = allMemberService.invite(groupId,groupName,inviteUserId);//这个result的data返回值是null

        //以下都是套路部分
        //获取响应变量的字节输出流
        PrintWriter writer = resp.getWriter();
        //获取返回结果封装类中的信息
        String message = result.getMessage();
        //如果成功：
        if (result.getCode().equals(ResultConst.SUCCESS)) {
            writer.write(message);
        }
        //如果失败：
        else if (result.getCode().equals(ResultConst.FAIL)) {
            writer.write(message);
        }

    }



    public void quitGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取html传来的信息,name可以不拿，
        String groupId = req.getParameter("groupId");
        String isManager = req.getParameter("isManager");
        String balanceStr = req.getParameter("balance");
        // 将balance字符串转换为double
        double balance = Double.parseDouble(balanceStr);

        //获取当前用户id
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
//        System.out.println("GSL  ********* session userId    "+userId);

        if(isManager.equals("1")) {//执行时manager的操作
//            System.out.println("GSL *********went to if");
            result = allMemberService.quitGroupManager(groupId, userId,balance);//这个result的data返回值是null，因为不是查询获取数据的操作
        }else {
//            System.out.println("GSL *********went to else");
            result = allMemberService.quitGroupMember(groupId,userId);
        }

        //获取响应变量的字节输出流
        PrintWriter writer = resp.getWriter();
        //获取返回结果封装类中的信息
        String message = result.getMessage();
        //如果成功：
        if (result.getCode().equals(ResultConst.SUCCESS)) {
            writer.write(message);
        }
        //如果失败：
        else if (result.getCode().equals(ResultConst.FAIL)) {
            writer.write(message);
        }

    }





    //这个的service可以重载，根据输入参数的数量不同 走不同的逻辑。但是返回值是一样的，含义是一样的
    public void getAllMemberDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        System.out.println("GSL into F_GAMD");
        //拿来接受下一层的数据和信息体的
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取html传来的信息,name可以不拿，
        String groupId = req.getParameter("groupId");
        //获取当前用户id
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        //这里的service可以重载
        result = allMemberService.getAllMemberDetails(groupId,userId);//这个返回值是AllMemberVO


        //下面的是仿照这种形式获取返回值未GroupVO得数据，解析转换传送到前端
        if (result != null && result.getData() instanceof AllMemberVO) {
//            System.out.println("GSL ***into if");
            AllMemberVO allMemberVO = (AllMemberVO) result.getData();
//            System.out.println("GSL F-GAMD***** into if, ismanager  :"+allMemberVO.getIsManager());

            // 将GroupVO对象转换为JSON格式
            String jsonAllMemberVO = JSON.toJSONString(allMemberVO);

            // 发送JSON数据到前端
            resp.getWriter().write(jsonAllMemberVO);
        } else {
//            System.out.println("GSL F-GAMD***into else");
            // 如果未找到数据或数据格式不正确，返回错误信息
            resp.getWriter().write("{\"error\": \"Data not found or incorrect format\"}");
        }


    }






    //用groupservlet处理group的事务，用all的service和dao拿数据
    public void joinGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        //拿来接受下一层的数据和信息体的
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取html传来的信息
        String groupName = req.getParameter("groupName");
        String groupId = req.getParameter("groupId");
        //获取当前用户id name
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        result = allMemberService.joinGroup(groupId,groupName,userId,userName);

        //下面这个通用，就是返回msg的
        //获取响应变量的字节输出流
        PrintWriter writer = resp.getWriter();
        //获取返回结果封装类中的信息
        String message = result.getMessage();
        //如果成功：
        if (result.getCode().equals(ResultConst.SUCCESS)) {
            writer.write(message);
        }
        //如果失败：
        else if (result.getCode().equals(ResultConst.FAIL)) {
            writer.write(message);
        }

    }






    public void getGroupDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{

        //拿来接受下一层的数据和信息体的
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String groupName = req.getParameter("groupName");

        result= groupService.getGroupDetails(groupName);//这个返回值是Result,把GroupVO放到result是service的事情
//        System.out.println("GSL result  :"+result.getCode());
//        System.out.println("GSL result  :"+result.getData());
//        System.out.println("GSL  ****before if");

        //下面的是仿照这种形式获取返回值未GroupVO得数据，解析转换传送到前端
        if (result != null && result.getData() instanceof GroupVO) {
//            System.out.println("GSL ***into if");
            GroupVO groupVO = (GroupVO) result.getData();
//            System.out.println("GSL ****groupVO.group_name  :"+groupVO.getGroupName());

            // 将GroupVO对象转换为JSON格式
            String jsonGroupVO = JSON.toJSONString(groupVO);

            // 发送JSON数据到前端
            resp.getWriter().write(jsonGroupVO);
        } else {
//            System.out.println("GSL ***into else");
            // 如果未找到数据或数据格式不正确，返回错误信息
            resp.getWriter().write("{\"error\": \"Data not found or incorrect format\"}");
        }

//        System.out.println("GSL ****after if");

//        // 检查result中的data是否为ArrayList<String>
//        if (result != null && result.getData() instanceof ArrayList<?>) {
//            ArrayList<String> groups = (ArrayList<String>) result.getData();
//            System.out.println("-----groups"+groups);
//
//            // 将ArrayList<String>转换为JSON格式
//            String jsonGroups = JSON.toJSONString(groups);
//
//            // 设置响应内容类型
//            resp.setContentType("application/json");
//            resp.setCharacterEncoding("UTF-8");
//
//            // 将JSON数据发送到前端
//            resp.getWriter().write(jsonGroups);
//        }


    }





    //用groupservlet处理group的事务，用all的service和dao拿数据
    public void getJoinedGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        //拿来接受下一层的数据和信息体的
        Result result;
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        //这里用result来接受返回值
        result = allMemberService.getJoinedGroups(userId);

        // 检查result中的data是否为ArrayList<String>
        if (result != null && result.getData() instanceof ArrayList<?>) {
            ArrayList<String> groups = (ArrayList<String>) result.getData();
            System.out.println("-----groups"+groups);

            // 将ArrayList<String>转换为JSON格式
            String jsonGroups = JSON.toJSONString(groups);

            // 设置响应内容类型
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // 将JSON数据发送到前端
            resp.getWriter().write(jsonGroups);
        }

        //如果成功了
        if(result.getCode().equals(ResultConst.SUCCESS)){
//            System.out.println("GSL ");
        }
        // 如果失败了
        if(result.getCode().equals(ResultConst.FAIL)){
        }

//        //展示result的msg方面
//        PrintWriter writer = resp.getWriter();
//        writer.write(result.getMessage());


    }


    //屎山代码，这个和上面的逻辑一摸一样，除了把最后的iscreator从0改为1，其实可以优化代码量的，但是这里先这样写，为了业务性
    public void getCreatedGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        //拿来接受下一层的数据和信息体的
        Result result;
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        //这里用result来接受返回值
        result = allMemberService.getCreatedGroups(userId);

        // 检查result中的data是否为ArrayList<String>
        if (result != null && result.getData() instanceof ArrayList<?>) {
            ArrayList<String> groups = (ArrayList<String>) result.getData();
            System.out.println("-----groups"+groups);

            // 将ArrayList<String>转换为JSON格式
            String jsonGroups = JSON.toJSONString(groups);

            // 设置响应内容类型
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // 将JSON数据发送到前端
            resp.getWriter().write(jsonGroups);
        }

        //如果成功了
        if(result.getCode().equals(ResultConst.SUCCESS)){
//            System.out.println("GSL ");
        }
        // 如果失败了
        if(result.getCode().equals(ResultConst.FAIL)){
        }

//        //展示result的msg方面
//        PrintWriter writer = resp.getWriter();
//        writer.write(result.getMessage());


    }



    //这个和上面的逻辑大致一样，把最后的ispublic改为1 并且没有自己id的，然后把这些数据中重复的去掉。因为在两个表查数据，而且做到一半才想到所以逻辑有点复杂。
    //      就是获取两个result，把这两个result的data拿出来，进行取数组重叠部分的操作，然后将这个赋值到一个新的result的data中去，然后送到前端
    public void getUnJoinGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException{
        //拿来接受下一层的数据和信息体的
        Result result_noMy;
        Result result_isPublic;
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        //这里用result来接受返回值
        result_noMy = allMemberService.getUnJoinGroups(userId);
        result_isPublic=groupService.getUnJoinGroups2();
        ArrayList<String> groups_noMy = (ArrayList<String>) result_noMy.getData();
        ArrayList<String> groups_isPublic = (ArrayList<String>) result_isPublic.getData();

        //这里取两个数组的重叠部分的操作
        // 使用HashSet存储list1中的元素
        HashSet<String> set = new HashSet<>(groups_noMy);
        // 创建一个新的ArrayList来存储重叠部分
        ArrayList<String> overlappingList = new ArrayList<>();
        // 遍历list2，如果set包含list2中的元素，则将其添加到overlappingList中
        for (String item : groups_isPublic) {
            if (set.contains(item)) {
                overlappingList.add(item);
            }
        }


        //进行和前两个函数同样的操作
        // 将ArrayList<String>转换为JSON格式
        String jsonGroups = JSON.toJSONString(overlappingList);

        // 设置响应内容类型
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 将JSON数据发送到前端
        resp.getWriter().write(jsonGroups);



//        // 检查result中的data是否为ArrayList<String>
//        if (result != null && result.getData() instanceof ArrayList<?>) {
//            ArrayList<String> groups = (ArrayList<String>) result.getData();
//            System.out.println("-----groups"+groups);
//
//            // 将ArrayList<String>转换为JSON格式
//            String jsonGroups = JSON.toJSONString(groups);
//
//            // 设置响应内容类型
//            resp.setContentType("application/json");
//            resp.setCharacterEncoding("UTF-8");
//
//            // 将JSON数据发送到前端
//            resp.getWriter().write(jsonGroups);
//        }
//


    }







//    /**
//     * 获取用户已加入的群组信息
//     *
//     * @param req  请求体对象
//     * @param resp 响应体对象
//     */
//    public void getJoinedGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
//        // 创建返回结果封装类对象
//        Result result;
//
//        // 获取用户名
//        String username = req.getParameter("username");
//
//        try {
//            // 调用service层方法获取已加入的群组信息 ///////////////////////////////////////////标记，这里报错
//            PageBean<Group> pageBean= groupService.getJoinedGroups(username,1,1);
////            result = new Result(ResultConst.SUCCESS, "获取已加入的群组信息成功", joinedGroups);
//// 将数据传递给前端页面进行展示
//        PrintWriter writer = resp.getWriter();
//        String jsonString = JSON.toJSONString(pageBean);
//        writer.write(jsonString);
//
//        } catch (SQLException e) {
//            result = new Result(ResultConst.FAIL, "获取已加入的群组信息失败", null);
//            throw new RuntimeException(e);
//        }
//
//        // 将数据返回给前端
//
//
////        resp.getWriter().write(JSON.toJSONString(result));
//    }
    //**************2次修改，把数据类型设置为Result，既可以表示false/true，表示信息，还可以承担数据data,然后用下面的代码拿信息
    // 然后状态 和 信息 都拿到最外层表达，在里面表达也可以
    // String jsonString = "{\"name\": \"John\", \"age\": 30}";
    //
    //        try {
    //            // 将JSON字符串解析为JSONObject
    //            JSONObject jsonObject = JSON.parseObject(jsonString);
    //
    //            // 从JSONObject中获取数据并设置到Person对象
    //            Person person = new Person();
    //            person.setName(jsonObject.getString("name"));
    //            person.setAge(jsonObject.getIntValue("age"));
    // ... 其他方法，如添加、修改、删除群组等



    //**重要思想：其实allmember和groups表是同一个数据的存储表，都是主要服务group的。虽然可以把后面的DAO直接写在group相关的service和
    //dao上，但是考虑到是去all表拿信息 和 service和 dao相对比较整体，所以这里先入groupServlet 再转到all的service dao。
    //这里的遵循核心：  去哪个表写就用哪个service dao 写，是什么事务就写在哪个Servlet.
    //                   这样就会造成alls 这个交叉作用的表的service和dao服务于不同交叉的事务
    //另一种分类方法核心：  虽然去all表拿数据，但是不写这个交叉表对应的service和dao，写在各自的事物下面的service,dao
    //产生这个问题的原因： 表存放了不同事务的数据，（可能是表没有设计合理，也有可能是无法避免的 在表的简洁性+操作一次性+但是事务交叉性 和 表的复杂性+操作多次+事务分离干净  之间选择 ）

    public void createGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //拿来接受下一层的数据和信息体的
        Result result_groups;
        Result result_allMembers;

        //1.获取当前用户id name
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        System.out.println("GSL session userId  :"+userId);
        System.out.println("GSL session userName  :"+userName);


        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        GroupVO groupVO=new GroupVO();



        //那个VO作为service的参数没有卵用，还麻烦有限制。
        //id放最后dao处理吧
        String groupName = req.getParameter("groupName");
        String groupScale = req.getParameter("groupScale");
        String direction = req.getParameter("direction");
        String isPublic = req.getParameter("isPublic");



        System.out.println("GSL ---groupName  :"+groupName);
        System.out.println("GSL ---direction  :"+direction);
        //1.这里是输入的部分，之后也有用，但是传送的参数不同，所以sevice要重载！！！！
        // 2.返回的要是数据库的所有值（非数组），所以返回值用result来承接，然后用上面的方法那里面的数据data-----关键
//////////////////////////////////////////////////////////////////////////////////////////////////////////////临时
        try {
            //先在groups表进行登记
            result_groups = groupService.createGroup(groupName,groupScale,direction,isPublic);
            //再在all表进行登记
            String groupId = groupService.getGroupId(groupName);
            result_allMembers = allMemberService.createGroup(groupId,groupName,userId,userName,"1","0",0.0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //获取响应变量的字节输出流
        PrintWriter writer = resp.getWriter();
        //获取返回结果封装类中的信息
        String message = result_groups.getMessage();
        //表示创建成功或者失败的结果：
        writer.write(message);




    }



}
