package com.example.testapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.Helper.UserHelper.PanierItem;
import com.example.testapplication.R;

import java.util.List;

public class PanierAdapter extends BaseAdapter {


    Context context;
    List<PanierItem> panierItems;

    public PanierAdapter(Context context, List<PanierItem> panierItems){
        this.context = context;
        this.panierItems = panierItems;
        System.err.println(this.panierItems);
    }

    @Override
    public int getCount() {
        return panierItems.size();
    }

    @Override
    public Object getItem(int position) {
        return panierItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.panierelement, null);

        TextView tv_name = (TextView) v.findViewById(R.id.tv_panier_iemname);
        TextView tv_amount =(TextView) v.findViewById(R.id.tv_panier_itemamount);
        TextView tv_price =(TextView) v.findViewById(R.id.tv_panier_pricetopay);
        /*
        ImageView img_article = (ImageView) v.findViewById(R.id.img_panier);

        int id = context.getResources().getIdentifier( panierItems.get(position).getPhoto(), "drawable", context.getPackageName());
        img_article.setImageResource(id);
        */
        tv_name.setText(panierItems.get(position).getItemname());
        tv_amount.setText(panierItems.get(position).getAmount().toString());
        tv_price.setText(panierItems.get(position).getPriceToPay().toString());



        return v;
    }
}
