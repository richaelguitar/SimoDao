package com.richaelguitar.dao.utils;

import android.text.TextUtils;
import android.util.Log;

import com.richaelguitar.dao.annotation.Column;
import com.richaelguitar.dao.annotation.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * 反射工具类
 * @author xww
 * @since  2018-10-26
 */
public class DBReflectUtils {


    private static final String TAG = "feildName";

    private static Map<String,Field>  cacheFieldMap = new HashMap<>();




    public static String generateCreateTable(Class<?> clazz){

        //获取注解
        Table table = clazz.getAnnotation(Table.class);
        if(table!=null){
            //获取表名
            String tableName = table.name();
            if(TextUtils.isEmpty(tableName)){
                tableName = clazz.getName();
            }
            StringBuffer createTableSQL = new StringBuffer("CREATE TABLE IF NOT EXISTS  ");
            createTableSQL.append(tableName).append(" ( ");
            //获取表字段
            Field[] fields = clazz.getDeclaredFields();
            Log.d(TAG,"length"+fields.length);
            //遍历字段
            for (int index=0;index<fields.length;index++) {
                Field field = fields[index];
                Log.d(TAG,field.getName());
                //获取列的注解
                Column column = field.getAnnotation(Column.class);
                if(column!=null){
                    //获取列的注解值
                    String columnName = column.name();
                    if(TextUtils.isEmpty(columnName)){
                        columnName = field.getName();
                    }

                    //设置是否是主键
                    boolean isPrimaryKey= column.isPrimaryKey();
                    boolean isAutoIncrement = column.isAutoIncrement();
                    if(isPrimaryKey){
                        createTableSQL.append(columnName)
                                .append(" INTEGER  ").append(" PRIMARY KEY ");
                        if(isAutoIncrement){
                            createTableSQL .append(" AUTOINCREMENT ");
                        }
                    }else{
                        //获取字段的类型
                        Class<?> type = field.getType();
                        if(type == String.class){
                            createTableSQL.append(columnName).append(" TEXT ");
                        }else if(type == Integer.class||type == int.class){
                            createTableSQL.append(columnName).append(" INTEGER ");
                        }else if(type == Double.class||type == double.class){
                            createTableSQL.append(columnName).append(" DOUBLE ");
                        }else if(type == byte[].class){
                            createTableSQL.append(columnName).append(" BLOB ");
                        }else{
                            continue;
                        }
                    }
                    //添加约束
                    if(column.isUnique()){
                        createTableSQL.append(" UNIQUE ");
                    }
                    //每句的最后加上逗号
                    if(index!=fields.length-1){
                        createTableSQL.append(" ,");
                    }
                }
            }
            //结尾括号
            createTableSQL.append(" );");
            return  createTableSQL.toString();
        }
        return null;
    }

    /**
     * 生成插入sql
     * @param entity
     * @return
     */
    public static  String generateInsertSQL(Object entity) {
        //获取注解
        Class<?> clazz = entity.getClass();
        Table table = clazz.getAnnotation(Table.class);
        if(table!=null) {
            //获取表名
            String tableName = table.name();
            if (TextUtils.isEmpty(tableName)) {
                tableName = clazz.getName();
            }
            StringBuffer sql = new StringBuffer("INSERT INTO ");
            sql.append(tableName).append(" ( ");
            //获取字段
            Field[] fields = clazz.getDeclaredFields();

//            if(cacheFieldMap.size()>0){
//
//            }else{
                for (int index=0;index<fields.length;index++) {
                    Field field = fields[index];
                    //获取列的注解
                    Column column = field.getAnnotation(Column.class);
                    if(column!=null) {
                        //获取列的注解值
                        String columnName = column.name();
                        if (TextUtils.isEmpty(columnName)) {
                            columnName = field.getName();
                        }
                        sql.append(columnName);
                        if(index!=fields.length-1){
                            sql.append(",");
                        }
                        //缓存
                        cacheFieldMap.put(columnName,field);
                    }
                }
//            }

            sql.append(" ) VALUES ( ");

            //设置字段值
            for (int index=0;index<fields.length;index++) {
                Field field = fields[index];
                field.setAccessible(true);
                //获取列的值
                try {
                    String value = field.get(entity).toString();
                    Log.d(TAG,value);
                    sql.append(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(index!=fields.length-1){
                    sql.append(",");
                }
            }
            //结尾
            sql.append(" );");

            return sql.toString();
        }
        return null;
    }
}
