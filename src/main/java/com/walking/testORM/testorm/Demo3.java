package com.walking.testORM.testorm;

import java.sql.*;
import java.util.Scanner;

/**
 * 需求：
 *      1、通过键盘录入用户名和密码
 *      2、判断用户是否登陆成功
 */
public class Demo3 {
    public static void main(String[] args) {
            //1、键盘录入 ，接收用户名和密码
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户名");
            String username = sc.nextLine();
            System.out.println("请输入密码");
            String password = sc.nextLine();
            //2、调用方法
            boolean flag = new Demo3().login(username,password);
            //3、判断结果，输出不同语句
            if(flag){
                //登录成功
                System.out.println("/登录成功");
            }else {
                System.out.println("用户名或密码错误");
            }

    }
    /**
     * 登录方法
     */
    public boolean login(String username,String password){
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs  = null;
        try {
            //连接数据库地址是否登录成功
            //1、获取连接
            conn = JDBCUtil2.getConnection();
            //2、定义sql
            String sql = "select * from student where username = ? and password = ?";
            //3、获取执行sql的对象
            ps =  conn.prepareStatement(sql);
            //给？赋值
            ps.setObject(1,username);
            ps.setObject(2,password);
            //4、执行查询,不需要传递sql
            rs  =  ps.executeQuery();
            //5、判断
            return rs.next();  //如果有下一行，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil2.close(rs,ps,conn);
        }

        return false;
    }

}
