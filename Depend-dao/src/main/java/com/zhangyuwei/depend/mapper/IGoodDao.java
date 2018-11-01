package com.zhangyuwei.depend.mapper;

import com.zhangyuwei.depend.entities.Good;

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
    /*根据编号进行查询*/
    List<Good> selectGoodById(int gid);
    /*分页*/
    List<Good> selectGoodPage(int param1,int param2);
    /*多条件查询*/
    List<Good> selectGoodMoreIf(Map<String,Object> map);
    /*批量删除*/
    int deleteGoodMore(List<Integer> entity);
}
