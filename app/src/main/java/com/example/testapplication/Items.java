package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.testapplication.Helper.Manager.DbManager;
import com.example.testapplication.Helper.UserHelper.AppItem;
import com.example.testapplication.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class Items extends AppCompatActivity {

    Bundle extra;
    ListView lv_items;

    List<AppItem> items = new ArrayList<>();
    ItemAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        lv_items = findViewById(R.id.lv_items);
        extra = this.getIntent().getExtras();
        String cat =  extra.get("CAT").toString();
        int x = Integer.parseInt(cat);

        Context context = this;

        DbManager dbManager = new DbManager(this);
        dbManager.open();


        switch (x){
            case 1 : {
                items =  dbManager.getListItems1();
                adapter = new ItemAdapter(getApplicationContext(),items);
                lv_items.setAdapter(adapter);
                break;
            }
            case 2 : {
                items =  dbManager.getListItems2();
                adapter = new ItemAdapter(getApplicationContext(),items);
                lv_items.setAdapter(adapter);
                break;
            }
            case 3 : {
                items =  dbManager.getListItems3();
                adapter = new ItemAdapter(getApplicationContext(),items);
                lv_items.setAdapter(adapter);
                break;
            }

        }
    }

    private void display_cat_1(){

    }

    public void panier(View view) {
        Intent intent = new Intent(this,Panier.class);
        startActivity(intent);
    }

    public void to1(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",1);
        startActivity(intent);
    }
    public void to2(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",2);
        startActivity(intent);
    }
    public void to3(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",3);
        startActivity(intent);
    }
}
