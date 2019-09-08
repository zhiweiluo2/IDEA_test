package com.walking.testORM.testorm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo1 {
            public static void main(String[] args) throws SQLException {
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement ps = null;

        try {
            String sql ="insert into student (id,name) values (?,?)";
            ps = conn.prepareStatement(sql);
            //可以使用setObject方法处理参数
            ps.setObject(1,"11");   //插入一行新成员 从首列开始加入参数
            ps.setObject(2,"xiao丽");
            System.out.println("插入一行记录");
            int count = ps.executeUpdate(); //运行insert/update/delete操作，返回跟新的行数
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(ps,conn);
        }
    }
}
