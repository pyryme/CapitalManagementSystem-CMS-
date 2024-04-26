package com.xxx.cms.controller;

import com.alibaba.fastjson.JSON;
import com.xxx.cms.bean.Result;
import com.xxx.cms.bean.vo.UserAvatarVO;
import com.xxx.cms.bean.vo.UserLoginVO;
import com.xxx.cms.bean.vo.UserSignupVO;
import com.xxx.cms.bean.vo.UserUpdatePhoneUsernameVO;
import com.xxx.cms.constant.ResultConst;
import com.xxx.cms.service.impl.UserServiceImpl;

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
import java.util.Collection;
import java.util.UUID;

//@WebServlet("/User/*")
//@WebServlet("/User")
//public class UserServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("get...");
//
//        //1. 接收请求参数
//        String username = request.getParameter("username");
//        System.out.println(username);
//
//        //2. 响应数据
//        response.getWriter().write("hello Axios~");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("post...");
//        this.doGet(request, response);
//    }
//
//    private final UserServiceImpl userService = new UserServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("get");
////        service(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("post");
////        service(req, resp);
//        String pathInfo = req.getPathInfo();
//        if ("/login".equals(pathInfo)) {
//            try {
//                login(req, resp);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } else if ("/register".equals(pathInfo)) {
////            register(req, resp);
//        } else {
//            // 处理其他未知请求
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) {
//        //1.设置字符编码,防止中文汉字报错
//        try {
//            req.setCharacterEncoding("UTF-8");
//            resp.setContentType("text/html;charset=utf-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//
//        //2.获取访问路径的参数（方法名）
//        //获取uri
//        String uri = req.getRequestURI();
//        //通过截取字符串获取参数
//        String[] strs = uri.split("/");
//        String methodName = strs[strs.length - 1];
//
//        //3.通过反射执行方法
//        //获取class字节码对象
//        Class<? extends UserServlet> cls = this.getClass();
//
//        try {
//            //获取方法对象
//            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            //执行方法
//            method.invoke(this, req, resp);//？？？？？？？？？？？？？？？？是不是在根据请求的情况下转到User服务器的login函数去
//        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//
//
//
//
//    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
//        System.out.println("open login servlet success");
//        //创建返回结果封装类对象
//        Result result;
//
//        //获取JSON对象，并转化为用户视图封装类对象
//        BufferedReader reader = req.getReader();
//        String jsonStr = reader.readLine();
//        UserLoginVO userLoginVO = JSON.parseObject(jsonStr, UserLoginVO.class);
//
//        //调用service层的登录方法，判断数据库中是否存在对应的用户，并获取返回结果封装类对象
//        result = userService.login(userLoginVO.getUsername(), userLoginVO.getPassword());
//
//        //如果登录成功：
//        if (result.getCode().equals(ResultConst.SUCCESS)) {
//            //将用户id存储至session中
//            Integer userId = (Integer) result.getData();
//            HttpSession userIdSession = req.getSession();
//            userIdSession.setAttribute("userId", userId);
//        }
//
//        //返回结果封装类中的信息给前端页面
//        PrintWriter writer = resp.getWriter();
//        writer.write(result.getMessage());
//        System.out.println("servletsuccess");
//    }
// @WebServlet("/User")

@MultipartConfig
@WebServlet("/User/*")
public class UserServlet extends BaseServlet {
    private final UserServiceImpl userService = new UserServiceImpl();

    /**
     * 1.用户的登录
     *
     * @param req  请求体对象
     * @param resp 响应体对象
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        System.out.println("into login");

        //创建返回结果封装类对象
        Result result;

        System.out.println("USL define result");
        //就是说axios的信息体类型是json，这里就是把json变成string
        //获取JSON对象，并转化为用户视图封装类对象
        //#1数据类型解释：读取HTTP请求体的内容到reader里面，配合下面的
        BufferedReader reader = req.getReader();
        System.out.println("USL define and init reader");

        //#1数据类型解释：将BufferedReader类型转换为string类型
        String jsonStr = reader.readLine();
        System.out.println("USL define and init jsonStr");

        //#1数据类型解释：将string类型转换为UserLoginVO（VO可以理解为自己定义的数据类型）类型的
        //        这里的UserLoginVO是string类型。**关键**而且从json键值对的键和这个VO里面的成员变量名字一样，所以可以这样直接就赋值上去了吗？？
        UserLoginVO userLoginVO = JSON.parseObject(jsonStr, UserLoginVO.class);
        System.out.println("USL define and init userLoginVO");

        //#1数据类型解释：这里将VO的两个string类型的成员变量的值 作为参数传入到了userService.login里面（虽然调用的是userService 但是后面传到了Impl中）
        //调用service层的登录方法，判断数据库中是否存在对应的用户，并获取返回结果封装类对象-----------关键行
        result = userService.login(userLoginVO.getUsername(), userLoginVO.getPassword());
        System.out.println("USL init result");

        //如果登录成功：
        if (result.getCode().equals(ResultConst.SUCCESS)) {
            //将用户的name存入session中（但是原理和下面的不同,这里直接用，下面的id是用这个name去数据库拿的）
            String userName = userLoginVO.getUsername();
            HttpSession userNameSession = req.getSession();
            userNameSession.setAttribute("userName",userName);

            //将用户id存储至session中
            String  userId = (String) result.getData();
            HttpSession userIdSession = req.getSession();
            userIdSession.setAttribute("userId", userId);

        }

        //返回结果封装类中的信息给前端页面
        PrintWriter writer = resp.getWriter();
        writer.write(result.getMessage());
    }


    /**
     * 2.用户的注册
     *
     * @param req  请求体对象
     * @param resp 响应体对象
     */
    public void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //创建返回结果封装类对象
        Result result;

        //设置字符编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取JSON对象，并转化为用户视图封装类对象
        BufferedReader reader = req.getReader();
        String jsonStr = reader.readLine();
        UserSignupVO userSignupVO = JSON.parseObject(jsonStr, UserSignupVO.class);

        //将数据交给service层进行注册操作/////////////----------------关键行
        try {
            result = userService.signup(userSignupVO.getUsername(), userSignupVO.getPassword(), userSignupVO.getPassword2());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //获取响应变量的字节输出流
        PrintWriter writer = resp.getWriter();
        //获取返回结果封装类中的信息
        String message = result.getMessage();
        //如果贴吧创建成功：
        if (result.getCode().equals(ResultConst.SUCCESS)) {
            writer.write(message);
        }
        //如果贴吧创建失败：
        else if (result.getCode().equals(ResultConst.FAIL)) {
            writer.write(message);
        }
    }

    /**
     * 3.获取用户名
     *
     * @param req  请求体对象
     * @param resp 响应体对象
     */
    //这个没有用，当作区别例子：这个是通过获取id核心的，我的是获取username为核心的
    public void getUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //1.获取当前用户id
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        //2.调用service层方法获取用户名
        String userName = userService.getUserName(userId);

        //3.将数据返回给前端
        PrintWriter writer = resp.getWriter();
        writer.write(userName);
    }


    public void getUserAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Result result;

        //获取用户名
        String username = req.getParameter("username");

        //2.调用service层方法获取avatar
        String avatar = userService.getUserAvatar(username);

        //3.将数据返回给前端..?????????/看看这个逻辑是什么东西，没用试试自己写的.?????????????这个原理不太清楚
        PrintWriter writer = resp.getWriter();
        writer.write(avatar);
    }


////////////////////////////////////////////////////////////////////////临时标记

    public void getUserPhone(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        Result result;

        //获取用户名
        String username = req.getParameter("username");

        //2.调用service层方法获取用户名
        String userPhone = userService.getUserPhone(username);

        //3.将数据返回给前端
        PrintWriter writer = resp.getWriter();
        writer.write(userPhone);
    }


    /**
     * 4.修改头像图片
     *
     * @param req  请求体对象
     * @param resp 响应体对象
     */
    public void changeSaveImage (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("USL into F_ChangeSaveImage");

        //创建返回结果封装类对象
        Result result;

        //获取用户名
        String username = req.getParameter("username");


        //测试req是否包含了图片
        System.out.println("Request Content-Type: " + req.getContentType());
        System.out.println("Request Content-Length: " + req.getContentLength());

        // 获取文件部分
            Part filePart = req.getPart("file");
            System.out.println("req :"+req);
            System.out.println("req.getPart()   :"+req.getPart("file"));
            System.out.println("USL Imgfile:  "+filePart);

        //这个是测试的
        Collection<Part> parts = req.getParts();
        for (Part part : parts) {
            System.out.println("Part Name: " + part.getName());
            System.out.println("Content Type: " + part.getContentType());
            System.out.println("Size: " + part.getSize());
        }



        if (filePart != null && filePart.getSize() > 0) {
            System.out.println("USL into if");


            //（易错bug点，这个学习版的ideal只能支持最高tomcat7，tomcat7不能直接用Part.getSubmittedFileName()获取文件名）
            // 获取文件名另一种方法
            String cd = filePart.getHeader("Content-Disposition");
                //截取不同类型的文件需要自行判断
            String fileOriginName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
            System.out.println("USL  define and init fileOriginName  :"+fileOriginName);

            // 生成唯一文件名
            // String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
            String fileName = UUID.randomUUID().toString() + "_" + fileOriginName;
            System.out.println("USL define and init fileName  :"+fileName);

            // 保存文件到服务器(这里为了测试先写绝对路径，之后再改为可适应性更加强的相对路径.  已经改了)--------关键多行1 开始
            //获得相对路径和这个文件的动态系统路径，增强代码的迁移应用能力
            //这里隐藏了bug，可以看到输出的路径是C:\GithubResource\CMS\src\main\webapp\img/b58ee6_laji.jpg，这个斜杠有点问题，但是项目又能动
            String relativePath = "/img/";
            String absolutePath = getServletContext().getRealPath(relativePath)+"/";
            System.out.println("USL absolutePath  :"+absolutePath);
            String filePath = Paths.get(absolutePath, fileName).toString();//这个可以把斜杠搞正，一个纠正斜杠方向的工具

            System.out.println("USL define and init filePath  :"+filePath);

                try (InputStream fileContent = filePart.getInputStream();
                     OutputStream outputStream = new FileOutputStream(filePath)) {
                    System.out.println("USL into try1(in F_change...)");

                    int read;
                    final byte[] bytes = new byte[1024];
                    while ((read = fileContent.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    System.out.println("USL  save image success");
                }
            System.out.println("USL try1 success,before  assign resp value");

                // 返回文件名
            resp.getWriter().write(fileName);
            System.out.println("USL try1 success,after  assign resp value");


            //公式化，先这样接受数据------------------------关键行2
            UserAvatarVO userAvatarVO = new UserAvatarVO();
            userAvatarVO.setUseravatar(filePath);

            try {
                result = userService.changeSaveImage(username,userAvatarVO.getUseravatar());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            System.out.println("USL userAvatarVO.avatar  :"+userAvatarVO.getUseravatar());

            }
        else {
                System.out.println("USL No file uploaded.");
                resp.getWriter().write("No file uploaded.");
            }
        //-----------------------------------------------------------关键多行1 结束





        }

    /**
     * 5.修改用户的名称和手机号
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void  updatePhoneUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //创建返回结果封装类对象
        Result result;

        String Oldusername = req.getParameter("Oldusername");
        String Newusername = req.getParameter("Newusername");
        String phoneNumber = req.getParameter("phoneNumber");

        //公式化，这样接受数据
        UserUpdatePhoneUsernameVO userUpdatePhoneUsernameVO = new UserUpdatePhoneUsernameVO();
        userUpdatePhoneUsernameVO.setUserUpdatePhone(phoneNumber);
        userUpdatePhoneUsernameVO.setUserUpdateNewUsername(Newusername);
        userUpdatePhoneUsernameVO.setUserUpdateOldUsername(Oldusername);

        //将数据交给service层进行注册操作/////////////----------------关键行
        try {
            result = userService.updatePhoneUsername(userUpdatePhoneUsernameVO.getUserUpdateOldUsername(),userUpdatePhoneUsernameVO.getUserUpdateNewUsername(), userUpdatePhoneUsernameVO.getUserUpdatePhone());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    }









































