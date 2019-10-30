package bcs.p_line;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class waitinglist1 extends ArrayAdapter<namapesanan> {
    private List<namapesanan> items;

    public waitinglist1(Context context, List<namapesanan> items) {
        super(context, R.layout.waiting, items);
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
            v = li.inflate(R.layout.waiting, null);
        }

        namapesanan app = items.get(position);

        if(app != null) {

            TextView nama  = (TextView)v.findViewById(R.id.textView3);
            TextView namaR  = (TextView)v.findViewById(R.id.textView4);
            TextView makanan  = (TextView)v.findViewById(R.id.textView5);
            TextView harga  = (TextView)v.findViewById(R.id.textView6);

            if(nama != null) {
                nama.setText("Ruko : ");
            }

            if(namaR != null) {
                namaR.setText(app.getRuko());
            }

            if(makanan != null) {
                makanan.setText(app.getMakanan());
            }

            if(harga != null) {
                harga.setText(app.getHarga());
            }

        }

        return v;
    }

}
