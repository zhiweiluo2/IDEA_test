package com.walking.JDBC;

//测试BLOB 二进制大对象的使用

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class Demo10 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

//            ps = conn.prepareStatement("insert into t_user (username,headImg) values (?,?)");
//            ps.setString(1,"威哥");           //以流的方式
//            ps.setBlob(2,new FileInputStream("D:\\1.jpg"));
//            ps.execute();

             ps = conn.prepareStatement("select * from t_user where id = ?");
             ps.setObject(1,3);
             rs =  ps.executeQuery();
             while(rs.next()){
                 Blob b  = rs.getBlob("headImg");
                 is =  b.getBinaryStream();
                 os = new FileOutputStream("D:\\b.jpg");
                 int temp = 0;
                 while((temp = is.read()) != -1 ){
                    os.write(temp);
                 }
             }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(os != null){
                    os.close();   //IO流
                }
            } catch (Exception e) {    //IO流 所以Exception
                e.printStackTrace();
            }
            try {
                if(is != null){
                    is.close();   //IO流
                }
            } catch (Exception e) {    //IO流 所以Exception
                e.printStackTrace();
            }
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

