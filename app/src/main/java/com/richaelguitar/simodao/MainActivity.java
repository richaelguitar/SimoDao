package com.richaelguitar.simodao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.richaelguitar.dao.sqlite.SQLiteFactory;

import java.util.ArrayList;
import java.util.List;

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
        SQLiteFactory.getInstance(this).getDao(Person.class).save(personList);
    }
}
