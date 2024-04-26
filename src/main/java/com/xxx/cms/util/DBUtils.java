package com.xxx.cms.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * @author fangyufan
 */
public class DBUtils {
    /**
     *  0.私有化构造方法
     */
    private DBUtils() {

    }


    /**
     * 定义数据库用户名
     */
    private static final String USERNAME;
    /**
     * 定义数据库密码
     */
    private static final String PASSWORD;
    /**
     * 定义数据库访问路径
     */
    private static final String URL;

    static {
        try {
            System.out.println("DBUTILS into try1_static");
            // 加载配置文件
            Properties properties = new Properties();
            properties.load(Files.newInputStream(Paths.get("C:\\GithubResource\\CMS\\src\\main\\resources\\jdbc.properties")));
            // 获取配置文件信息
            String driver = properties.getProperty("driver");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            URL = properties.getProperty("url");
            // 加载驱动
            Class.forName(driver);
            System.out.println("DBUTILS finish try1 success");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("DBUTILS try1 fail");
            throw new RuntimeException(e);
        }
    }

    /**
     * 2.获取数据库连接
     *
     * @return conn 返回数据库的连接
     */
    public static Connection getConn() throws SQLException {
        //返回数据库连接
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 3.释放资源
     *
     * @param conn  数据库的连接对象
     * @param pstmt PreparedStatement对象
     * @param rs    结果集对象
     */
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        //释放conn对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //释放pstmt对象
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //释放rs对象
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 4.执行增删改操作
     *
     * @param sql  需要执行的sql语句
     * @param params 需要设置在sql语句中的参数值
     * @return ret 被执行操作的数据条目数
     */
    public static int executeUpdate(String sql, Object... params) {
        //定义数据库相关操作变量
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            System.out.println("DBUTILS into try1(in Function_executeUpdate)");
            //获取数据库连接
            conn = getConn();
            System.out.println("DBUTILS define conn(in Function_executeUpdate):"+conn);
            //获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            System.out.println("DBUTILS define pstmt(in Function_executeUpdate):"+pstmt);
            //设置参数，就是把pstmt中sql信息的?替换成params中的部分
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }


            // 检查连接是否正常
            System.out.println("Connection is valid: " + conn.isValid(5));  // 5 秒超时
            // 打印准备好的 SQL 语句
            System.out.println("Prepared SQL: " + pstmt.toString());


            //执行sql并返回结果-------------------------关键行
            System.out.println("DBUTILS try1(in Function_executeUpdate) success");
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DBUTILS try1(in Function_executeUpdate) fail");
            e.printStackTrace();  // 打印异常堆栈跟踪
            throw new RuntimeException(e);

        } finally {
            closeAll(conn, pstmt, null);
        }
    }


    /**
     * 5.数据库查询操作
     *
     * @param sql  需要执行的sql语句
     * @param params 需要设置在sql语句中的参数值
     * @return rs 查询得到的结果集
     */
    public static ResultSet executeQuery(String sql, Object... params) {
        System.out.println("DBUTILS into Function_executeQuery");

        //定义数据库相关操作变量
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        System.out.println("DBUTILS init conn pstmt re success");

        try {
            //获取数据库连接
            conn = getConn();
            //获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            System.out.println("DBUTILS define conn pstmt:"+conn+"//////"+pstmt);

            //设置参数
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }


            // 检查连接是否正常
            System.out.println("Connection is valid: " + conn.isValid(5));  // 5 秒超时
            // 打印准备好的 SQL 语句
            System.out.println("Prepared SQL: " + pstmt.toString());


            //-----------------------------------------------出错点，
            //执行sql
            rs = pstmt.executeQuery();
            System.out.println("DBUTILS try2(in Function_executeQuery) success,define rs:"+rs);
            //返回结果
            return rs;
        } catch (SQLException e) {
            System.out.println("DBUTILS try2(in Function_executeQuery) fail");
            throw new RuntimeException(e);
        }
    }
}
