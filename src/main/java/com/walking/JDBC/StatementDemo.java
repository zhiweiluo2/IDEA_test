package com.walking.JDBC;

//测试执行SQL语句，以及SQL注入问题

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDemo {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo3";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null; // Connection负责连接数据库并担任传送数据的任务
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动  （ 装载） //导入驱动
             Class.forName(JDBC_DRIVER);  //依据数据库的不同，管理JDBC驱动

             //建立连接MYSQL数据库  （连接对象内部其实包含了Socket对象，是一个远程连接。比较耗时！这是Connection对象管理的一个要点）
             //真正的开发中，为了提供效率，都会使用 连接池  来管理连接对象。Connection负责连接数据库并担任传送数据的任务。
             conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Statement 由Connection产生、负责执行SQL语句
            stmt = conn.createStatement();
//            String sql ="insert into websites (id,name,url,alexa,country) values('6','longlong','https://weibo.com/','909','CN')";
////            stmt.execute(sql);    //向websites表插入新成员。

            //测试SQL注入
            String id = "5 or  1=1";
            String sql ="delete from websites where id = 7";
            stmt.execute(sql);    //向websites表删除id 为 7 的成员。

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(stmt != null){   //以防空指针
                    stmt.close();
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
}

