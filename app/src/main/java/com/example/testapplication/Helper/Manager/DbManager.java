package com.example.testapplication.Helper.Manager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.testapplication.Helper.DataBaseHelper;
import com.example.testapplication.Helper.UserHelper.AppItem;

import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private DataBaseHelper dataBaseHelper;

    private Context context;

    private SQLiteDatabase database;

    public DbManager(Context c) {
        context = c;
    }

    public Boolean authenticate(String username, String password) {
        return dataBaseHelper.authenticate(username,password);
    }

    public DbManager open() throws SQLException {
        dataBaseHelper = new DataBaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public List<AppItem> getListItems1(){
        return dataBaseHelper.getListItems1(database);
    }

    public void close() {
        dataBaseHelper.close();
    }

    public List<AppItem> getListItems2() {
        return dataBaseHelper.getListItems2(database);

    }
    public List<AppItem> getListItems3() {
        return dataBaseHelper.getListItems3(database);

    }

    public String getPriceByNamePerMagasin(String name, String choice) {

        System.err.println(name);
        System.err.println(choice);
        return dataBaseHelper.getPriceByNamePerMagasin(database,name,choice);
    }
}
