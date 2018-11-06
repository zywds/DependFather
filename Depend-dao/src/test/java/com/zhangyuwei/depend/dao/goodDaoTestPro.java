package com.zhangyuwei.depend.dao;

import com.zhangyuwei.depend.entities.Good;
import com.zhangyuwei.depend.entities.GoodPage;
import com.zhangyuwei.depend.mapper.IGoodDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class goodDaoTestPro {
    @Autowired
    IGoodDao dao;
    @Test
    public void selectAllGood(){
        System.out.println(dao.selectAllGood());
    }
    @Test
    public void deleteGood(){
        int gid=1;
        int row=dao.deleteGood(gid);
        Assert.assertEquals(1,row);
    }
    @Test
    public void selectGoodMoreIfPage(){
        int page=1;
        int limit=10;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("gname","益达尊享护齿装草本40粒+冰柠40粒+西");
        map.put("gprice",25.9);
        map.put("page",page);
        map.put("limit",limit);
        List<Good> entity=dao.selectGoodMoreIfPage(map);
        System.out.println(entity);
    }
    @Test
    public void selectGoodMoreIfPage2(){
        List<GoodPage> pageList=new ArrayList<GoodPage>();
        GoodPage gp=new GoodPage();
        gp.setPage(1);
        gp.setLimit(10);
        pageList.add(gp);
        List<Good> entity=dao.selectGoodMoreIfPage2(pageList);
        System.out.println(entity);
    }
    @Test
    public void selectGoodCountMoreIf(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("gname","");
        map.put("gprice","");
        int count=dao.selectGoodCountMoreIf(map);
        System.out.println(count);
    }
}
