package com.walking.JDBC;

//测试时间处理（java.sql.Date ,Time，Timestamp )

import java.sql.*;
import java.util.Random;

public class JavaSqlDateDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        try {
            //加载驱动类    注册 JDBC 驱动  （ 装载）
            Class.forName("com.mysql.jdbc.Driver");  //依据数据库的不同，管理JDBC驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo3","root","root");

            for(int i = 0 ;i < 1000; i++ ) {

                ps1 = conn.prepareStatement("insert into student (name,pwd,regTime,lastloginTime) values (?,?,?,?)");
                ps1.setObject(1,"美丽");   //插入一行新成员 从首列开始加入参数
                ps1.setObject(2,"123456");

                int rand  = 100000000 + new Random().nextInt(100000000);

                Date date =  new Date(System.currentTimeMillis()-rand);
                Timestamp stamp = new Timestamp(System.currentTimeMillis()-rand); //如果需要插入指定日期，可以使用C alendar DateFormat

                ps1.setDate(3,date);
                ps1.setTimestamp(4,stamp);
                ps1.execute();
            }
            System.out.println("插入一个用户，美丽");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

