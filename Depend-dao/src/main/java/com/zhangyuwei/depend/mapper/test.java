package com.zhangyuwei.depend.mapper;

import com.zhangyuwei.depend.entities.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class test {
    //@Autowired
    //IGoodDao dao;

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        //IGoodDao dao=ctx.getBean(IGoodDao.class);
        IGoodDao dao=ctx.getBean(IGoodDao.class);
        List<Good> entity=dao.selectAllGood();
        System.out.println(entity);
    }
}
