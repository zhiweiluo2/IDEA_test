package com.walking.JDBC;

/**
 * 测试 使用 JDBCUtil 工具类 来 简化 JDBC开发
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo11 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getMysqlConn();

            ps =  conn.prepareStatement("insert into t_user (username) values (?)");
            ps.setString(1,"威哥");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,ps,conn);
        }
    }
}

