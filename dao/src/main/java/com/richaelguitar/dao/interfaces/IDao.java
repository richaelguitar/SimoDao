package com.richaelguitar.dao.interfaces;


import android.database.Cursor;


import com.richaelguitar.dao.sqlite.Condition;

import java.util.List;

/**
 * 数据接口
 * @author xww
 * @since  2018-10-26
 */
public interface IDao<T> {

    /**
     * 保存对象
     * @param entity
     * @return
     */
    long[] save(T ...entity);

    /**
     * 保存或修改对象
     * @param entity
     * @return
     */
    long[] saveOrUpdate(T ...entity);

    /**
     * 删除对象
     * @param entity
     * @return
     */
    boolean delete(T ...entity);

    /**
     * 根据条件查询对象列表
     * @param condition
     * @return
     */
    List<T>  query(Condition condition);

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    T queryById(String id);

    /**
     * 根据条件查询单个对象
     * @param condition
     * @return
     */
    T querySingle(Condition condition);

    /**
     * 执行原生的sql查询，需要自己处理事务
     * @param condition
     * @return
     */
    Cursor querySQL(Condition condition);

    /**
     * 执行原生sql，需要自己处理事务
     * @param sqls
     * @return
     */
    boolean executeSQL(String ...sqls);

}
