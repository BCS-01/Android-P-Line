package bcs.p_line;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class homepembeli extends ActionBarActivity implements FetchDataListener, View.OnClickListener,AdapterView.OnItemClickListener {
    private ProgressDialog dialog;
    private String kode="http://192.168.56.1/makanan.php";
    Constants cons;
    ImageButton makanan,minuman,snack,home,keranjang,cari;
    EditText search;
    ListView listv;
    List<application> ambil;
    Listener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepembeli);
        home = (ImageButton) findViewById(R.id.home);
        search = (EditText) findViewById(R.id.search);
        makanan = (ImageButton) findViewById(R.id.makanan);
        keranjang = (ImageButton) findViewById(R.id.keranjang);
        minuman = (ImageButton) findViewById(R.id.minuman);
        snack = (ImageButton) findViewById(R.id.snack);
        cari = (ImageButton) findViewById(R.id.cari);
        listv = (ListView) findViewById(R.id.lv);
        home.setOnClickListener(this);
        keranjang.setOnClickListener(this);
        makanan.setOnClickListener(this);
        minuman.setOnClickListener(this);
        snack.setOnClickListener(this);
        cari.setOnClickListener(this);
        search.setOnClickListener(this);
        initView();
        ListView myList=(ListView)findViewById(R.id.lv);
        myList.setOnItemClickListener(this);

    }



    private void initView() {
        dialog = ProgressDialog.show(this, "", "Loading...");
        String url = kode;
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
    }

    private void cari(){
        String a = search.getText().toString();
        cari c = new cari(this);
        c.execute(a);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.home:
                Toast.makeText(this,"belum ada favorit", Toast.LENGTH_LONG).show();
                break;

            case R.id.minuman:
                kode=cons.minuman;
                initView();
                break;

            case R.id.snack:
                kode=cons.snack;
                initView();
                break;

            case R.id.makanan:
                kode=cons.makanan;
                initView();
                break;

            case R.id.cari:
                cari();
                break;

            case R.id.keranjang:
                Intent keranjang = new Intent(homepembeli.this, waitinglist3.class);
                startActivity(keranjang);
        }
    }

    @Override
    public void onFetchComplete(List<application> data) {
        if(dialog != null)  dialog.dismiss();
        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
        ListView myList=(ListView)findViewById(R.id.lv);
        myList.setAdapter(adapter);
        ambil = data;
    }


    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
        if (ambil.get(myItemInt).getStok()>=0){
            String nama = ambil.get(myItemInt).getN_menu();
            String ruko = ambil.get(myItemInt).getRuko();
            String harga = String.valueOf(ambil.get(myItemInt).getHarga());
            SignInService sig = new SignInService(this,listener);
            String a = sig.user;
        Toast.makeText(homepembeli.this," You clicked on",Toast.LENGTH_SHORT).show();

        pesan mJSONParser = new pesan();
        mJSONParser.setParams(a.toString(),nama,ruko,harga);
        mJSONParser.execute();
        }

    }
}