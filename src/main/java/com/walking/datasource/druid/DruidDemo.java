package com.walking.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
//        1、导入jar 包 到 src/main/resources中
//        2、定义配置文件
//        3、加载配置文件
        Properties pro = new Properties();
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
//        4、获取连接池对象
        //创建连接池，使用配置文件中的参数
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        for (int i = 0; i < 9; i++) {
            //从连接池中取出连接
            Connection conn = ds.getConnection();
            System.out.println(conn);
        }
        //最大连接数设置为10，此时获取十一个connection 第十一个等待三秒会报错！！！！！



    }

}
