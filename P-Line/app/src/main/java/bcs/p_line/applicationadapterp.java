package bcs.p_line;


import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class applicationadapterp extends ArrayAdapter<transaksi>{
    private List<transaksi> items;
    private List<pesanan> item;

    public applicationadapterp(Context context, List<transaksi> items) {
        super(context, R.layout.daftarpesanan, items);
        this.items = items;
        this.item = item;
    }


    public List<transaksi> getItems() {
        return items;
    }

    public void setItems(List<transaksi> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.daftarpesanan, null);
        }

        transaksi app = items.get(position);

        if(app != null) {
            TextView jenis2  = (TextView)v.findViewById(R.id.meja);
            TextView jenis1  = (TextView)v.findViewById(R.id.menu);
            if(jenis1!=null){
                jenis1.setText(app.getN_menu());
            }

            if(jenis2!=null){
                jenis2.setText(String.valueOf(app.getMeja()));
            }


        }

        return v;
    }


}