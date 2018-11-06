package com.zhangyuwei.depend.mapper;

import com.zhangyuwei.depend.entities.Good;
import com.zhangyuwei.depend.entities.GoodPage;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public interface IGoodDao {
    /*查询所有商品*/
    List<Good> selectAllGood();
    /*添加商品*/
    int insertGood(Good entity);
    /*修改商品*/
    int updateGood(Good entity);
    /*删除商品*/
    int deleteGood(int gid);
    /*查询商品个数*/
    int selectGoodCount();
    /*查询商品个数带有条件*/
    int selectGoodCountMoreIf(Map<String,Object> map);
    /*根据编号进行查询*/
    List<Good> selectGoodById(int gid);
    /*分页与多查询之间的组合*/
    List<Good> selectGoodMoreIfPage(Map<String,Object> map);
    /*分页与多查询之间的组合*/
    List<Good> selectGoodMoreIfPage2(List<GoodPage> listpage);
    /*分页*/
    List<Good> selectGoodPage(int param1,int param2);
    /*多条件查询*/
    List<Good> selectGoodMoreIf(Map<String,Object> map);
    /*批量删除*/
    int deleteGoodMore(List<Integer> entity);
}
