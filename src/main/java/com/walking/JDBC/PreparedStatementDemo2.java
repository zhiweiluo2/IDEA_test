package com.walking.JDBC;

//测试PreparedStatement的基本用法

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo2 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");
            conn.setAutoCommit(false);//1、JDBC中默认是true，自动提交事务

            ps1 = conn.prepareStatement("insert into student (name,pwd) values (?,?)");
            ps1.setObject(1,"美丽");   //插入一行新成员 从首列开始加入参数
            ps1.setObject(2,"123456");
            ps1.execute();
            System.out.println("插入一个用户，美丽");

            Thread.sleep(4000);

            ps2 = conn.prepareStatement("insert into student (name,pwd) values (?,?)");
            ps2.setObject(1,"龙哥");   //插入一行新成员 从首列开始加入参数
            ps2.setObject(2,"123456");
            ps2.execute();
            System.out.println("插入一个用户，龙哥");
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                conn.rollback();  //回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            try {
                if(ps1 != null){
                    ps1.close();
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

//建立连接MYSQL数据库  Connection负责连接数据库并担任传送数据的任务。