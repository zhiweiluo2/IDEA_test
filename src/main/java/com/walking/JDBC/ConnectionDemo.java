package com.walking.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDemo {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo3";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null; // Connection负责连接数据库并担任传送数据的任务
        Statement stmt = null;  //由Connection产生、负责执行SQL语句
        try {
            // 注册 JDBC 驱动  （ 装载）
             Class.forName(JDBC_DRIVER);  //依据数据库的不同，管理JDBC驱动
            /**
             * 连接MYSQL数据库   (连接就建好了)
             * 建立连接（连接对象内部其实包含了Socket对象，是一个远程连接。比较耗时！这是Connection对象管理的一个要点）
             * 真正的开发中，为了提供效率，都会使用 连接池  来管理连接对象
             */
            Long start = System.currentTimeMillis();
                 conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Long end = System.currentTimeMillis();
                System.out.println(conn);
                System.out.println("建立连接，耗时:"+ (end-start)+"ms毫秒");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 输出：
 *    com.mysql.jdbc.JDBC4Connection@7a30d1e6
 *    建立连接，耗时:815ms毫秒
 */
