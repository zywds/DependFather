package com.zhangyuwei.depend.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis会话工具类
 * */
public class MyBatisUtil {
    /**会话工厂*/
    private static SqlSessionFactory factory;

    static {
        try {
            /*获得配置文件的文件流*/
            InputStream inputStream=Resources.getResourceAsStream("mybatisConf.xml");
            //初始化工厂
            factory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得会话对象
     * 指定是否自动提交
     * */
    public static SqlSession openSqlSession(boolean isAutoCommit){
        return getFactory().openSession(isAutoCommit);
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }
    public static void setFactory(SqlSessionFactory factory) {
        MyBatisUtil.factory = factory;
    }

    /**
     * 关闭会话
     * */
    public static void closeSession(SqlSession session){
        if(session!=null){
            session.close();
        }
    }
}
