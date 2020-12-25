package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.Helper.DataBaseHelper;
import com.example.testapplication.Helper.Manager.DbManager;

public class MainActivity extends AppCompatActivity {

    DbManager dbManager;
    SQLiteDatabase db;

    EditText email;
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DbManager(this);
        dbManager.open();

        email = findViewById(R.id.edt_txt_login_id);
        pwd = findViewById(R.id.edt_txt_password);

           }

    private Boolean verify(String username, String password) {
        if (username != null && password != null) {
            if (!username.equals("") && !password.equals("")) {
                return true;
            }
        }
        return false;
    }

    private Boolean authenticate(String username, String password) {
        return dbManager.authenticate(username,password);
    }


    public void login(View view) {
        String username = email.getText().toString();
        String password = pwd.getText().toString();

        if (!verify(password, password)) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!authenticate(username, password)) {
            Toast.makeText(this, "Username or password wrong!", Toast.LENGTH_LONG).show();
            return;
        }


        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

}

