package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testapplication.Helper.Manager.DbManager;
import com.example.testapplication.Helper.UserHelper.PanierItem;
import com.example.testapplication.adapter.PanierAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Choisisseur extends AppCompatActivity {

    Bundle extras;
    String Magasin;

    List<PanierItem> panierItems = new ArrayList<>();

    ListView lv_choisisseur;
    TextView tv_total;
    Button btn_next ;
    Button btn_prev;

    String next = "";
    String prev = "";

    Context context;

    ImageView imageView;

    PanierAdapter panierAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisisseur);

        imageView = findViewById(R.id.magasin_logo);
        context = this;

        extras = this.getIntent().getExtras();
        Magasin = extras.getString("Magasin");

        lv_choisisseur = (ListView) findViewById(R.id.lv_choisisseur);
        tv_total = findViewById(R.id.txt_total);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);


        String choice = "";

        int id = context.getResources().getIdentifier(Magasin.toLowerCase(), "drawable", context.getPackageName());
        imageView.setImageResource(id);


        switch (Magasin) {
            case "M": {
                choice = "PRICE_M";
                next = "C";
                prev = "G";
                break;
            }
            case "C": {
                choice = "PRICE_C";
                next = "G";
                prev = "M";
                break;
            }
            case "G": {
                choice = "PRICE_G";
                next = "M";
                prev = "C";
                break;
            }
        }
        DbManager dbManager = new DbManager(this);
        dbManager.open();


        int total = 0;
        for (Map.Entry entry :
                Globals.getPanier().entrySet()) {
            String name = entry.getKey().toString();

            String amount = entry.getValue().toString();

            if (Integer.valueOf(amount) != 0) {
                String MagasinPrice = dbManager.getPriceByNamePerMagasin(name,choice);
                System.err.println("FROM CHOISISSEUR :"+MagasinPrice);
               // String priceToPay = Globals.getWALLET().get(name);
                int UnitaryPrice = Integer.valueOf(MagasinPrice);

                UnitaryPrice = UnitaryPrice * Integer.valueOf(amount);

                total += UnitaryPrice;

                PanierItem panierItem = new PanierItem(name, Integer.valueOf(amount), UnitaryPrice);// Integer.valueOf(priceToPay));

                panierItems.add(panierItem);
            }
        }
        tv_total.setText("Total : "+String.valueOf(total));

        panierAdapter = new PanierAdapter(this, panierItems);
        lv_choisisseur.setAdapter(panierAdapter);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Choisisseur.class);
                intent.putExtra("Magasin", next);
                startActivity(intent);
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Choisisseur.class);
                intent.putExtra("Magasin", prev);
                startActivity(intent);
            }
        });

    }

    public void toHome(View view) {
        Intent intent = new Intent(this,Categories.class);
        startActivity(intent);
    }

    public void commander(View view) {
        Globals.setPanierItems(panierItems);
        Intent intent = new Intent(this,Facture.class);
        intent.putExtra("Magasin",Magasin);
        startActivity(intent);
    }
}
