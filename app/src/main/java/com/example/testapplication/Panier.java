package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testapplication.Helper.UserHelper.PanierItem;
import com.example.testapplication.adapter.PanierAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Panier extends AppCompatActivity {

    ListView lv_panier;
    PanierAdapter panierAdapter;
    TextView tv_panier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        List<PanierItem> panierItems = new ArrayList<>();

        lv_panier = findViewById(R.id.lv_panier);

        for (Map.Entry entry :
                Globals.getPanier().entrySet()) {
            String amount = entry.getValue().toString();
            //System.err.println("amount : "+amount);

            if (Integer.valueOf(amount) != 0) {
                String priceToPay = Globals.getWALLET().get(entry.getKey().toString());
                int UnitaryPrice = Integer.valueOf(priceToPay);
                UnitaryPrice = UnitaryPrice * Integer.valueOf(amount);
                //System.err.println("priceopay : "+priceToPay);
                String name = entry.getKey().toString();
                //System.err.println("name : "+name);
                PanierItem panierItem = new PanierItem(name, Integer.valueOf(amount),UnitaryPrice);// Integer.valueOf(priceToPay));

                //System.err.println("panieritem : "+panierItem);
                panierItems.add(panierItem);
            }
        }

        System.err.println("FROM PANIER : " + panierItems);

        panierAdapter = new PanierAdapter(this, panierItems);
        lv_panier.setAdapter(panierAdapter);
    }

    public void validerPanie(View view) {
        Intent intent = new Intent(this,Choisisseur.class);
        intent.putExtra("Magasin","M");
        startActivity(intent);

    }
}


