package com.richaelguitar.dao.interfaces;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.richaelguitar.dao.annotation.Table;
import com.richaelguitar.dao.entity.DaoEntity;
import com.richaelguitar.dao.sqlite.Condition;
import com.richaelguitar.dao.utils.DBReflectUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 数据接口具体操作类
 * @author xww
 * @since  2018-10-26
 */
public  class BaseDao<T extends DaoEntity> implements IDao<T> {

    private static final String TAG = BaseDao.class.getSimpleName();
    private SQLiteDatabase database;
    private Class<T> entityClass;
    private  String tableName;

    public void init(SQLiteDatabase database,Class<T> entityClass) {
        this.database =database;
        this.entityClass = entityClass;
        this.tableName = entityClass.getAnnotation(Table.class).name();
        //判断数据库是否可用
        if(database.isOpen()){
            //创建数据表
            String createTableSQL = DBReflectUtils.generateCreateTable(entityClass);
            Log.d(TAG,"sql="+createTableSQL);
            database.execSQL(createTableSQL);
            //缓存
        }

    }

    @Override
    public long[] save(T... entitys) {

//        if(database!=null&&database.isOpen()){
//            database.beginTransaction();
//            for(T entity:entitys){
//                String insertSQL =  DBReflectUtils.generateInsertSQL(entity);
//                Log.d(TAG,insertSQL);
//                database.execSQL(insertSQL);
//            }
//            database.setTransactionSuccessful();
//            database.endTransaction();
//        }
        if(database!=null&&database.isOpen()){
            for(T entity:entitys){
                ContentValues values = new ContentValues();
                Map<String,String> valuesMap = DBReflectUtils.getValuesMap(entity);
                Iterator<String> iterator = valuesMap.keySet().iterator();
                while (iterator.hasNext()){
                    String key = iterator.next();
                    values.put(key,valuesMap.get(key));
                }
               database.insert(tableName,null,values);
            }
        }
        return new long[0];
    }

    @Override
    public long[] saveOrUpdate(T... entity) {
        return new long[0];
    }

    @Override
    public boolean delete(T... entity) {
        return false;
    }

    @Override
    public List<T> query(Condition condition) {
        return null;
    }

    @Override
    public T queryById(String id) {
        return null;
    }

    @Override
    public T querySingle(Condition condition) {
        return null;
    }

    @Override
    public Cursor querySQL(Condition condition) {
        return null;
    }

    @Override
    public boolean executeSQL(String... sqls) {
        return false;
    }
}
