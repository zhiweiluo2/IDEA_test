package com.walking.testORM.testorm;
/**
 * 定义工具类：不用每次都写重复的代码！以后再去写查询就方便多了
 */


import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil { //工具类 特点：就是它所有的方法都是静态的，方便去调用。

//    static Properties pros = null;  //可以帮助读取和处理资源文件中的信息

//    static {    //加载JDBCUtil类的时候调用
//        pros = new Properties();
//        try {
//            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
//            pros.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
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
            ClassLoader classLoader = JDBCUtil.class.getClassLoader();
            URL res =  classLoader.getResource("db.properties");
            String path = res.getPath();
            System.out.println(path);
            //2、加载文件
//            pro.load(new FileReader("D:\\ideajSpace\\TestJDBC\\src\\db.properties")); //绝对路径
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
//        return null;  //先写这句代替先，不会报错。
        return DriverManager.getConnection(url,user,password);

//        try {
//            //重要注意：此两行代码，在实际开发中是不会写在源码里面的，一般写在配置文件里面，比如可以写在 XML 便于以后修改
//            //加载驱动类    注册 JDBC 驱动  （ 装载）
//            Class.forName(pros.getProperty("JDBC_DRIVER"));  //依据数据库的不同，管理JDBC驱动
//            return  DriverManager.getConnection(pros.getProperty("DB_URL"),
//                    pros.getProperty("USER"),pros.getProperty("PASS"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
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


//    public static void close(Connection conn){
//        try {
//            if(conn != null){
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



}
