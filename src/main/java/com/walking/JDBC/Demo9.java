package com.walking.JDBC;

//测试CLOB 文本大对象的使用
//包含：将字符串、文件内容插入到数据库中的CLOB字段、将CLOB字段值取出来的操作。

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class Demo9 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reader r = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

//            ps = conn.prepareStatement("insert into t_user (username,myInfo) values (?,?)");
//            ps.setString(1,"威哥");           //以流的方式
//            ps.setClob(2,new FileReader(new File("D:\\a.txt"))); //将文本文件内容直接输入数据库中
//            将程序中的字符串输入到数据库的CLOB字段中
//            ps.setClob(2,new BufferedReader(new InputStreamReader(new ByteArrayInputStream("aaabbb".getBytes()))));
//            ps.executeUpdate();
             ps = conn.prepareStatement("select * from t_user where id = ?");
             ps.setObject(1,2);
             rs =  ps.executeQuery();
             while(rs.next()){
                 Clob c  = rs.getClob("myInfo");
                 r =  c.getCharacterStream();
                 int temp = 0;
                 while((temp = r.read()) != -1 ){
                     System.out.print((char) temp);  //(char)转成人看得懂的字符
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
                if(r != null){
                    r.close();   //IO流
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

