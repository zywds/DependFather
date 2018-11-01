package com.zhangyuwei.depend.dao;

import com.zhangyuwei.depend.entities.Good;
import com.zhangyuwei.depend.mapper.IGoodDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.nio.cs.ext.Big5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class goodDaoTest {
    IGoodDao ia=null;
    @Before
    public void setUp() throws Exception {
        ia=new goodDao();
    }

    @After
    public void tearDown() throws Exception {
    }
    /*查询所有的商品*/
    @Test
    public void selectAllGood() {
        List<Good> entity=ia.selectAllGood();
        System.out.println(entity);
    }
    /*添加商品*/
    @Test
    public void insertGood(){
        Good good=new Good();
        good.setGname("333");
        String s="45.56";Double d=Double.parseDouble(s);BigDecimal b=new BigDecimal(d);
        good.setGprice(b);good.setGpicture("4.jpg");
        int row=ia.insertGood(good);
        if(row>0){
            System.out.println("添加成功!");
        }
    }
    /*修改商品*/
    @Test
    public void updateGood(){
        Good good=new Good();
        good.setGid(10);
        good.setGname("334");
        String s="45.56";Double d=Double.parseDouble(s);BigDecimal b=new BigDecimal(d);
        good.setGprice(b);good.setGpicture("4.jpg");
        int row=ia.updateGood(good);
        if(row>0){
            System.out.println("修改成功!");
        }
    }
    /*删除商品*/
    @Test
    public void deleteGood(){
        int gid=10;
        int row=ia.deleteGood(gid);
        if(row>0){
            System.out.println("删除成功!");
        }
    }
    /*查询商品数量*/
    @Test
    public void selectGoodCount(){
        int count=ia.selectGoodCount();
        System.out.println("商品的数量是:"+count);
    }
    /*根据编号进行查询*/
    @Test
    public void selectGoodById(){
        int gid=4;
        List<Good> entity=ia.selectGoodById(gid);
        System.out.println(entity);
    }
    /*分页*/
    @Test
    public void selectGoodPage(){
        int page=2;
        int limit=5;
        List<Good> entity=ia.selectGoodPage(page,limit);
        System.out.println(entity);
    }
    /*多条件查询*/
    @Test
    public void selectGoodMoreIf(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("gname","益达");
        //map.put("gid",8);
        List<Good> entity=ia.selectGoodMoreIf(map);
        System.out.println(entity);
    }
    /*批量删除*/
    @Test
    public void deleteGoodMore(){
        List<Integer> entity=new ArrayList<Integer>();
        entity.add(11);entity.add(12);
        int row=ia.deleteGoodMore(entity);
        System.out.println(row);
    }
}