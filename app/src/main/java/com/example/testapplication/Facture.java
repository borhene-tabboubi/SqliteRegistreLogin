package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.testapplication.Helper.UserHelper.PanierItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Facture extends AppCompatActivity {

    TextView tv_facture;
    TextView tv_titre;
    List<PanierItem> panierItems;

    Bundle extra;
    String magasin;

    String ma_titre;

    ScrollView layout;

    double total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facture);
        tv_facture = findViewById(R.id.tv_facture);
        tv_titre = findViewById(R.id.tv_titre);
        tv_titre.setTextSize(18);
        tv_facture.setTextSize(20f);
        panierItems = Globals.getPanierItems();

        layout =  findViewById(R.id.layout_facture);


        extra = this.getIntent().getExtras();
        magasin = extra.getString("Magasin");

        switch (magasin) {
            case "M": {
                ma_titre = "Magasin Générale";
                break;
            }
            case "C": {
                ma_titre = "Carrefour";
                break;
            }
            case "G": {
                ma_titre = "Géant";
                break;
            }
        }

        tv_titre.append(" "+ma_titre+" ");

        for(PanierItem item : panierItems){
            total+= item.getPriceToPay();
            tv_facture.append("\n "+item.getItemname()+"\n\n Quantité : "+item.getAmount()+"\u00A0\u00A0\u00A0\u00A0 Net à payer : "+item.getPriceToPay()+"\n\n");
        }

        System.err.println("TOTAL : "+total);

        double difference = total;
        if(total<=19000){
            total = total * 1.1;
        }else if (total<= 39000){
            total = total* 1.09;
        }
        else if (total<= 59000){
            total = total* 1.08;
        }
        else if (total<= 79000){
            total = total* 1.07;
        }
        else if (total<= 99000){
            total = total* 1.06;
        }else {
            total = total* 1.05;
        }

        System.err.println("TOTAL : "+total);

        difference = total- difference;
        difference += 4000;

        total+=4000;

        tv_facture.append("Frais de service : "+new DecimalFormat("##.##").format(difference)+"\n\n");
        tv_facture.append("Total à payer : "+new DecimalFormat("##.##").format(total));

        //takeScreenshot();
    }

    public Bitmap getScreenBitmap() {
        //View v= findViewById(android.R.id.content).getRootView();
        View v = layout;
        System.err.println("width : "+v.getWidth());
        System.err.println("height : "+v.getHeight());

        v.setDrawingCacheEnabled(true);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }


    public void takeScreenshot(){
        Bitmap bitmap = getScreenBitmap(); // Get the bitmap
       // View v= findViewById(android.R.id.content).getRootView();
        //Bitmap bitmap = loadBitmapFromView(v);
       // Bitmap bitmap = getBitmapFromView(layout, layout.getChildAt(0).getHeight(), layout.getChildAt(0).getWidth());

        saveTheBitmap(bitmap);               // Save it to the external storage device.
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    public void saveTheBitmap(Bitmap bitmap){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir

        File folder = new File(getApplicationContext().getFilesDir() , "9offaExpres");
        if(!folder.exists())  folder.mkdir();
        if(folder.exists()) {
            System.err.println("EXIST");
        }else System.err.println("DOESNT EXIST");

        File directory = cw.getDir(folder.getName(), Context.MODE_PRIVATE);
        System.err.println("folder get name "+folder.getName());

        File mypath=new File(directory,currentDateandTime+".png");

        try{
            FileOutputStream fos = null;
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

        }catch (Exception e){

        }


/*
        folder.mkdir();
        if(folder.exists()){
            System.err.println("EXIST");
        }
        else {
            System.err.println();
        }

        try (FileOutputStream out = new FileOutputStream(folder.getName()+"/"+ currentDateandTime)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private Bitmap loadBitmapFromView(View yourView) {
        Bitmap snapshot = null;
        Drawable drawable = null;
        yourView.setDrawingCacheEnabled(true);
        yourView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH); //Quality of the snpashot
        try {
            yourView.buildDrawingCache(); // UPDATE HERE
            snapshot = Bitmap.createBitmap(yourView.getDrawingCache());
        } finally {
            yourView.setDrawingCacheEnabled(false);
        }
        return snapshot;
    }
}
