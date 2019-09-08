package com.walking.JDBC;

//测试PreparedStatement的基本用法

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

            String sql ="insert into student (id,name) values (?,?)";
            ps = conn.prepareStatement(sql);
//            ps.setString(1, "9");  //参数索引是从1开始计算，而不是0 。char 类型用双引号
//            ps.setString(2, "成龙");
//            ps.setString(3, "65");

            //可以使用setObject方法处理参数
            ps.setObject(1,"10");   //插入一行新成员 从首列开始加入参数
            ps.setObject(2,"美丽");

            System.out.println("插入一行记录");
//            ps.execute();  //返回是否有结果集
            int count = ps.executeUpdate(); //运行insert/update/delete操作，返回跟新的行数
            System.out.println(count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
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
}

//建立连接MYSQL数据库  Connection负责连接数据库并担任传送数据的任务。