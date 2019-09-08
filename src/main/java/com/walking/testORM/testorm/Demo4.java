package com.walking.testORM.testorm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo4 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
//            1、获取连接
            conn =  JDBCUtil2.getConnection();
//            2、定义sql
//            2.1 xiaohong - 500
                String sql1 = "Update accout set balance  = balance - ? where id = ?";
//            2.2 huahua + 500
                String sql2 = "Update accout set balance  = balance + ? where id = ?";
//            3、获取执行sql对象
               pstmt1 =  conn.prepareStatement(sql1);
               pstmt2 =  conn.prepareStatement(sql2);
//               4、设置参数
                pstmt1.setDouble(1,500);
                pstmt1.setInt(2,1);

                pstmt2.setDouble(1,500);
                pstmt2.setInt(2,2);
//                5、执行sql
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil2.close(pstmt1,conn);
            JDBCUtil2.close(pstmt2,null);
        }
    }
}
