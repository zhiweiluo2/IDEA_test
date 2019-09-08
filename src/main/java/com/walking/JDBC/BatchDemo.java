package com.walking.JDBC;

//测试 Batch 批量处理，如：批量添加用户

import java.sql.*;

public class BatchDemo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");
            conn.setAutoCommit(false); //1、设为手动提交
            Long start = System.currentTimeMillis();
             stmt = conn.createStatement();

             for(int i = 0; i<20000;i++){
               stmt.addBatch("insert into stu (name,pwd,regTime) values ('gao"+i+"','555555',now())");
             }
             stmt.executeBatch();
             conn.commit();   //提交事务
            Long end = System.currentTimeMillis();
            System.out.println("插入20000条数据，耗时（毫秒）:"+ (end-start)); //耗时（毫秒）:11388

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
                if(stmt != null){
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

