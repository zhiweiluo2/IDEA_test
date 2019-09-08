package com.walking.testORM.testorm;
/**
 * 定义工具类：不用每次都写重复的代码！以后再去写查询就方便多了
 */

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil2 { //工具类 特点：就是它所有的方法都是静态的，方便去调用。

    private static String url;  //只有是静态的变量，才可以被静态代码块所访问，才能被静态方法所访问
    private static String user;
    private static String password;
    private static String driver;
    /**
     * 文件的读取，只需要读取一次即可拿到这些值，使用静态代码块
     */
    static{
        //读取资源文件，获取值。
        try {
            //1、创建 Properties集合类。
            Properties pro  = new Properties();

            //获取src路径下的文件的方式---》ClassLoader类加载器
            ClassLoader classLoader = JDBCUtil2.class.getClassLoader();
            URL res =  classLoader.getResource("db.properties");
            String path = res.getPath();
            System.out.println(path);
            pro.load(new FileReader(path));
            // 3、获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取连接,返回值 肯定是 连接对象  Connection
     * @return
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,user,password);

    }

    /**
     * 释放资源
     */
    public static void close( PreparedStatement ps,Connection conn){ //执行 增删改 SQL语句时 ，就要释放Statement对象、Connection对象
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps,Connection conn){  //执行 查询语句时，需要释放三个对象
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
