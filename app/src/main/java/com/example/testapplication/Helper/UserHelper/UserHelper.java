package com.example.testapplication.Helper.UserHelper;

public class UserHelper {

    private static int id_user = -1;
    private static String nom = "";


    public static void setUserID(int id){
        id_user = id;
    }
    public static void setUsername(String username){
        nom = username;
    }
}
