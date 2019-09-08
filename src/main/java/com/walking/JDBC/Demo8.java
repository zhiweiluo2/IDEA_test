package com.walking.JDBC;

//测试时间处理（java.sql.Date ,Time，Timestamp ) 取出指定时间段

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Demo8 {
    /**
     * 将字符串代表的日期为long 数字 （格式：yyyy-mm-dd hh:mm:ss）
     * @param dateStr
     * @return
     */
    public static long str2Date(String dateStr){
        DateFormat format = new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
        try {
            return format.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

//            ps = conn.prepareStatement("SELECT * FROM student where lastloginTime > ? and lastloginTime < ?");
//            java.sql.Date start = new java.sql.Date(str2Date("2019-08-19 20:00:00"));
//            java.sql.Date end = new java.sql.Date(str2Date("2019-08-20 04:06:15"));
//            ps.setObject(1,start);
//            ps.setObject(2,end);

            ps = conn.prepareStatement("SELECT * FROM student where lastloginTime > ? and lastloginTime < ?");
            Timestamp start = new Timestamp(str2Date("2019-08-19 18:38:35"));
            Timestamp end = new Timestamp(str2Date("2019-08-20 07:40:09"));
            ps.setObject(1,start);
            ps.setObject(2,end);
            rs =  ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name")+"---"+ rs.getTimestamp("lastloginTime"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
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

