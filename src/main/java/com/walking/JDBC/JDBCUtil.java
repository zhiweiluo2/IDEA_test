package com.walking.JDBC;
/**
 * 定义工具类：不用每次都写重复的代码！以后再去写查询就方便多了
 */

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static Properties pros = null;  //可以帮助读取和处理资源文件中的信息

    static {    //加载JDBCUtil类的时候调用
        pros = new Properties();

        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConn(){
        try {
            //重要注意：此两行代码，在实际开发中是不会写在源码里面的，一般写在配置文件里面，比如可以写在 XML 便于以后修改
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName(pros.getProperty("mysqldriver"));  //依据数据库的不同，管理JDBC驱动
            return  DriverManager.getConnection(pros.getProperty("mysqlURL"),
                    pros.getProperty("mysqlUser"),pros.getProperty("mysqlPwd"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Connection getOracleConn(){
        try {
            //重要注意：此两行代码，在实际开发中是不会写在源码里面的，一般写在配置文件里面，比如可以写在 XML 便于以后修改
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName(pros.getProperty("oracledriver"));  //依据数据库的不同，管理JDBC驱动
            return  DriverManager.getConnection(pros.getProperty("oracleURL"),
                    pros.getProperty("oracleUser"),pros.getProperty("oraclePwd"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void close(ResultSet rs, PreparedStatement ps,Connection conn){
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
    public static void close( PreparedStatement ps,Connection conn){ //为了方便以后使用，可以提供重载的
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
    public static void close(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
