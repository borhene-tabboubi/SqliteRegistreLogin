package com.example.testapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.Globals;
import com.example.testapplication.Helper.UserHelper.AppItem;
import com.example.testapplication.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<AppItem> items;

    public ItemAdapter(Context context, List<AppItem> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.article, null);
        TextView tvName = (TextView) v.findViewById(R.id.tv_itemname);

        TextView tvprice = (TextView) v.findViewById(R.id.tv_itemprice);
        ImageView img_article = (ImageView) v.findViewById(R.id.img_article);

        tvName.setText(items.get(position).getName());
        tvprice.setText("à partir de "+items.get(position).getMin_price());
        int id = context.getResources().getIdentifier( items.get(position).getPhoto(), "drawable", context.getPackageName());
        System.err.println("PHOTO ID : "+ id);
        img_article.setImageResource(id);

        Globals.addItem(items.get(position).getName(), items.get(position).getMin_price());

        Button btn_up = (Button) v.findViewById(R.id.btn_up);
        Button btn_down = (Button) v.findViewById(R.id.btn_down);

        final TextView amount = (TextView) v.findViewById(R.id.amount);
        amount.setText(Integer.toString(Globals.getAmount(items.get(position).getName())));


        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.upOne(items.get(position).getName());
                Globals.intoPanier(items.get(position));
                amount.setText(Integer.toString(Globals.getAmount(items.get(position).getName())));
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.downOne(items.get(position).getName());
                amount.setText(Integer.toString(Globals.getAmount(items.get(position).getName())));
            }
        });

        return v;
    }
}
