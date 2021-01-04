package com.dsi32g6.sqliteregistrelogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText email ,password ;
    Button login;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DataBaseHelper(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password_user);
        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext =email.getText().toString();
                String passwordtext =password.getText().toString();
                Boolean chkemailpassword =db.emailpassword(emailtext,passwordtext);
                if (chkemailpassword){
                    Toast.makeText(getApplicationContext(), "Successfully login ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "wrong email or email ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}