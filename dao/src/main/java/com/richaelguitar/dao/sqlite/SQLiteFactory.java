package com.richaelguitar.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.richaelguitar.dao.BuildConfig;
import com.richaelguitar.dao.entity.DaoEntity;
import com.richaelguitar.dao.interfaces.BaseDao;

import java.io.File;

/**
 * 数据库工厂
 * @author xww
 * @since  2018-10-26
 */
public class SQLiteFactory {


    private SQLiteDatabase database;
    private String databasePath;

    private static  SQLiteFactory ourInstance;

    public static SQLiteFactory getInstance(Context context) {
       if(ourInstance==null){
           synchronized (SQLiteFactory.class){
               if(ourInstance == null){
                   ourInstance = new SQLiteFactory(context);
               }
           }
       }
        return ourInstance;
    }

    private SQLiteFactory(Context context) {
        File file = new File("/data/data/"+context.getPackageName()+"/database/");
        if(!file.exists()){
            file.mkdir();
        }
        this.databasePath = file.getPath()+"/"+BuildConfig.DatabaseName+".db";
        database = SQLiteDatabase.openOrCreateDatabase(databasePath,null);
    }

    public <T extends DaoEntity >  BaseDao<T>  getDao(Class<T> clazz){
        BaseDao baseDao = null;
        try {
            baseDao = BaseDao.class.newInstance();
            baseDao.init(database,clazz);
        } catch (IllegalAccessException |InstantiationException e) {
            e.printStackTrace();
        }
        return baseDao;
    }
}
