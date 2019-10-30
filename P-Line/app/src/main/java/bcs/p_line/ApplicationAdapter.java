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

public class ApplicationAdapter extends ArrayAdapter<application>{
    private List<application> items;

    public ApplicationAdapter(Context context, List<application> items) {
        super(context, R.layout.list, items);
        this.items = items;
    }

    public List<application> getItems() {
        return items;
    }

    public void setItems(List<application> items) {
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
            v = li.inflate(R.layout.list, null);
        }

        application app = items.get(position);

        if(app != null) {
            ImageView icon = (ImageView)v.findViewById(R.id.image);
            TextView jenis1  = (TextView)v.findViewById(R.id.tjenis);
            TextView jenis2  = (TextView)v.findViewById(R.id.njenis);
            TextView ruko2  = (TextView)v.findViewById(R.id.nruko);
            TextView ruko1  = (TextView)v.findViewById(R.id.truko);
            TextView harga  = (TextView)v.findViewById(R.id.tharga);
            TextView status  = (TextView)v.findViewById(R.id.tstatus);

            if(jenis2!=null){
                jenis2.setText(app.getN_menu());
            }

            if(ruko2!=null){
                ruko2.setText(app.getRuko());
            }

            if(icon != null) {
                Resources res = getContext().getResources();
                String sIcon = app.getImage();
                icon.setImageDrawable(res.getDrawable(res.getIdentifier(sIcon, null, null)));
            }

            if(jenis1 != null) {
                jenis1.setText(app.getJenis());
            }

            if(harga != null) {
                harga.setText("Rp. "+app.getHarga());
            }

            if(app.getStok()>=0){
                status.setText("Tersedia");
            }else{
                status.setText("Habis");
            }

            if(ruko1 != null) {
                ruko1.setText("Ruko : ");
            }
        }

        return v;
    }


}