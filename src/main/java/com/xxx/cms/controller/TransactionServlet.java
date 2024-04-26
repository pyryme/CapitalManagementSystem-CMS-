package com.xxx.cms.controller;


//import com.xxx.cms.model.Group;
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
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");






        //这里先拿groupName的数组(用userId iscreator得到Name，没有写得到ArrayList<AllMemberVO>的函数.....所以就索性写一个能够获得这个的函数)
        //这个函数的前3个信息的数组拿得到，这个信息的name可以去拿member的信息
        result_allCreatedGroup = allMemberService.getCreatedGroups_all(userId);//返回值是ArrayList<AllMemberVO>，是这个用户创造的自己的数据
        //**这里拿前3个数据作为数组**//



        //这里直接拿groupName，给后面的用
        result_allCreatedGroupName = allMemberService.getCreatedGroups(userId);//这个data的数据是ArrayList<String>,含义是name
        ArrayList<String> arrayList_allCreatedGroupName = (ArrayList<String>) result_allCreatedGroupName.getData();

        //用arrayList_allCreatedGroupName获取后面的ArrayList<AllMemberVO>的信息
        result_allMemberDetail = allMemberService.getAllMemberDetails_Array();//返回值是数组Arraylist<AllMemberVo>，是这个群组所有成员的（之前的命名限定没有写好，所以这里写的很难受，命名有问题了，不易懂）

        //**然后在这里完成封装,(拿 输入的群组名字 对应的数据的后部分的)**
        //**然后进行解析，传到前端中**



        //例子：下面仿照下面注释的代码进行解析然后上传到html页面上
//        //这里用result来接受返回值
//        result = allMemberService.getJoinedGroups(userId);
//
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

}