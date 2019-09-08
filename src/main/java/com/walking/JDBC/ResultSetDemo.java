package com.walking.JDBC;

//测试ResultSet结果集的基本用法

import java.sql.*;

public class ResultSetDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

            String sql ="SELECT id,NAME,age FROM student where id > ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,2); //把id大于2的记录都取出来

            rs =  ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //遵循：ResultSet ---> Statement ---> Conntion这样的关闭顺序！ 一定要将三个try...catch块，分开写！
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
}

//建立连接MYSQL数据库  Connection负责连接数据库并担任传送数据的任务。