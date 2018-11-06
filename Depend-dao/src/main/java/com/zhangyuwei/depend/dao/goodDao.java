package com.zhangyuwei.depend.dao;

import com.zhangyuwei.depend.entities.Good;
import com.zhangyuwei.depend.entities.GoodPage;
import com.zhangyuwei.depend.mapper.IGoodDao;
import com.zhangyuwei.depend.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.tools.mail.MailMessage;

import java.util.List;
import java.util.Map;

public class goodDao implements IGoodDao {
    /*查询所有的商品*/
    public List<Good> selectAllGood() {
        //打开一个会话
        SqlSession session=MyBatisUtil.openSqlSession(true);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        List<Good> entity=ia.selectAllGood();
        return entity;
    }
    /*添加商品*/
    public int insertGood(Good entity) {
        //打开一个会话
        SqlSession session=MyBatisUtil.openSqlSession(false);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        int row=ia.insertGood(entity);
        session.commit();session.close();
        return row;
    }
    /*修改商品*/
    public int updateGood(Good entity) {
        //打开一个会话
        SqlSession session=MyBatisUtil.openSqlSession(false);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        int row=ia.updateGood(entity);
        session.commit();session.close();
        return row;
    }
    /*删除商品*/
    public int deleteGood(int gid) {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(false);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        int row=ia.deleteGood(gid);
        session.commit();session.close();
        return row;
    }
    /*查询商品数量*/
    public int selectGoodCount() {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(true);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        int count=ia.selectGoodCount();
        return count;
    }

    public int selectGoodCountMoreIf(Map<String,Object> map) {
        return 0;
    }

    /*根据编号进行查询*/
    public List<Good> selectGoodById(int gid) {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(true);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        List<Good> entity=ia.selectGoodById(gid);
        return entity;
    }

    public List<Good> selectGoodMoreIfPage(Map<String, Object> map) {
        return null;
    }

    public List<Good> selectGoodMoreIfPage2(List<GoodPage> listpage) {
        return null;
    }

    /*分页*/
    public List<Good> selectGoodPage(int arg0, int arg1) {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(true);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        List<Good> entity=ia.selectGoodPage((arg0-1)*arg1,arg1);
        return entity;
    }
    /*多条件查询*/
    public List<Good> selectGoodMoreIf(Map<String, Object> map) {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(true);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        List<Good> entity=ia.selectGoodMoreIf(map);
        return entity;
    }
    /*批量删除*/
    public int deleteGoodMore(List<Integer> entity) {
        //打开会话
        SqlSession session=MyBatisUtil.openSqlSession(false);
        IGoodDao ia=session.getMapper(IGoodDao.class);
        int row=ia.deleteGoodMore(entity);
        session.commit();session.close();
        return row;
    }
}
