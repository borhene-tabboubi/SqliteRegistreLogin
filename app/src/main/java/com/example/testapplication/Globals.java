package com.example.testapplication;

import com.example.testapplication.Helper.UserHelper.AppItem;
import com.example.testapplication.Helper.UserHelper.PanierItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Globals {

    private static List<PanierItem> panierItems = new ArrayList<>();

    public static List<PanierItem> getPanierItems() {
        return panierItems;
    }

    public static void setPanierItems(List<PanierItem> panierItems) {
        Globals.panierItems = panierItems;
    }

    private static List<AppItem> ITEMS = new ArrayList<>();
    private static Map<String,Integer> PANIER = new LinkedHashMap<>();//< item : amount >
    private static Map<String,String> WALLET = new LinkedHashMap<>();//<item : total price to pay>


    public static int getAmount(String itemname){
        return PANIER.get(itemname);
    }

    public static void addItem(String itemname,String price){
        if(!PANIER.containsKey(itemname)){
            PANIER.put(itemname,0);
            WALLET.put(itemname,price);
        }
    }

    public static void upOne(String itemname){
        Integer amount = PANIER.get(itemname);
        amount++;
        PANIER.put(itemname,amount);

        /*Integer price_to_pay = Integer.valueOf(WALLET.get(itemname));
        System.err.println("price to pay  : "+price_to_pay);
        int unitary_price = (PANIER.get(itemname) > 2 ? Integer.valueOf(WALLET.get(itemname)) : Integer.valueOf(WALLET.get(itemname))/PANIER.get(itemname) );
        System.err.println("unitary_price  : "+unitary_price);

        price_to_pay = (PANIER.get(itemname) <= 1 ? price_to_pay : price_to_pay + unitary_price);
        System.err.println("price_to_pay  : "+price_to_pay);

        WALLET.put(itemname,price_to_pay.toString());*/
    }

    public static void downOne(String itemname){
        Integer amount = PANIER.get(itemname);
        if (amount > 0) {
            amount--;
            PANIER.put(itemname,amount);

            /*Integer price_to_pay = Integer.valueOf(WALLET.get(itemname));
            price_to_pay -= price_to_pay;
            WALLET.put(itemname,price_to_pay.toString());*/
        }


    }

    public static void intoPanier(AppItem item){
        if(!ITEMS.contains(item))ITEMS.add(item);
    }


    public static Map<String,Integer> getPanier(){
        return PANIER;
    }

    public static Map<String,String> getWALLET(){
        return WALLET;
    }
}
