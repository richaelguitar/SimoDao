package com.richaelguitar.simodao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.richaelguitar.dao.sqlite.SQLiteFactory;
import com.richaelguitar.simodao.entity.Person;
import com.richaelguitar.simodao.entity.User;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String[] names = {"'张三'","'李四'","'王五'","'赵六'","'陈七'"};
    private Person[] personList = new Person[names.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<names.length;i++){
            Person person = new Person(names[i],20+i,1);
            personList[i]=person;
        }
    }

    public void addPerson(View view) {

        Log.d(TAG,"size="+personList.length);
        long[] ids = SQLiteFactory.getInstance(this).getDao(Person.class).save(personList);
        Log.d(TAG,"ids="+Arrays.toString(ids));
    }

    public void addUser(View view) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               long startTime = System.currentTimeMillis();
               for(int i=0;i<5000;i++){
                    SQLiteFactory.getInstance(MainActivity.this).getDao(User.class).save(new User("'richael"+i+"'","'123456'","'kad'"));
                   Log.d(TAG,"=执行第"+(i+1)+"次=");
               }
               long endTime = System.currentTimeMillis();
               Log.d(TAG,"=执行总时间="+((endTime-startTime)/1000f)+"s");
           }
       }).start();
    }
}
