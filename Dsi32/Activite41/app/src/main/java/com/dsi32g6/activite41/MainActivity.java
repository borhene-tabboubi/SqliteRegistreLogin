package com.dsi32g6.activite41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText nom,mdp;
    private Button button;
    private CheckBox checkBox;
    private SharedPreferences mp;
    private  SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom=(EditText)findViewById(R.id.nom_user);
        mdp=(EditText)findViewById(R.id.password_user);
        button=findViewById(R.id.btn_login);
        checkBox=findViewById(R.id.checkbox1);


        mp= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mp.edit();

        checkShared();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();

                    String name=nom.getText().toString();
                    mEditor.putString(getString(R.string.nom),name);
                    mEditor.commit();

                    String password=mdp.getText().toString();
                    mEditor.putString(getString(R.string.mot_de_passe), password);
                    mEditor.commit();

                }else{
                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();
                    mEditor.putString(getString(R.string.nom),"");
                    mEditor.commit();
                    mEditor.putString(getString(R.string.mot_de_passe),"");
                    mEditor.commit();

                }
            }
        });


    }
    private  void  checkShared(){
        String checkbox1 =mp.getString(getString(R.string.checkbox),"False");
        String name =mp.getString(getString(R.string.nom),"");
        String mdps =mp.getString(getString(R.string.mot_de_passe),"");
        nom.setText(name);
        mdp.setText(mdps);
        if(checkbox1.equals("True")){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

    }
}