package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void toCategorie_1(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",1);
        startActivity(intent);
    }

    public void toCategorie_2(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",2);
        startActivity(intent);
    }

    public void toCategorie_3(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra("CAT",3);
        startActivity(intent);
    }

    public void toCategorie_4(View view) {
    }
}
