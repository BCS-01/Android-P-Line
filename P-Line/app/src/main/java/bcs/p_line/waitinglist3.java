package bcs.p_line;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.text.Editable;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.List;
public class waitinglist3 extends ActionBarActivity implements waintlist, Fetchlistener, View.OnClickListener,AdapterView.OnItemClickListener {

    ListView list;
    ProgressDialog dialog;
    String kode= "http://192.168.56.1/ambiltransaksi.php";
    String apa = "http://192.168.56.1/saldo.php";
    String opo = "http://192.168.56.1/hargayangdibeli.php";
    String iki = "http://192.168.56.1/updatesaldo.php";
    String statusbayar = "http://192.168.56.1/updatestatusbayar.php";
    String pesanan1 = "http://192.168.56.1/pesanan.php";
    String lihatstok = "http://192.168.56.1/lihatstok.php";
    String updatestok = "http://192.168.56.1/updatestok.php";
    Listener listener;
    getsaldo app;
    int q;
    int stokawal,stoksisa;
    int saldo;
    ImageButton home1;
    EditText nomeja;
    List<namapesanan> ambil;
    List<setpotong> data;
    Editable a;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitinglist);
        home1 = (ImageButton) findViewById(R.id.home1);
        nomeja=(EditText) findViewById(R.id.nomeja);
        a=nomeja.getText();
        list = (ListView) findViewById(R.id.listView);
        initView();
        ListView myList=(ListView)findViewById(R.id.listView);
        myList.setOnItemClickListener(this);
        home1.setOnClickListener(this);
        nomeja.setOnClickListener(this);
    }

    private void initView() {
        dialog = ProgressDialog.show(this, "", "Loading...");
        ceksaldo();
        sambildata();

    }
    public void ceksaldo(){
        SignInService sis = new SignInService(this,listener);
        getsaldo tas = new getsaldo(this);

        tas.execute(apa,sis.user.toString());
    }

    public void sambildata(){
        SignInService sis = new SignInService(this,listener);
        ambildata task = new ambildata(this);
        task.execute(kode,sis.user.toString());
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.home1 :
                Intent home1 = new Intent(waitinglist3.this, homepembeli.class);
                startActivity(home1);
        }
    }

    public void kirimpenjual(){
        String trans = ambil.get(q).getTransaksi();
        String ruko = ambil.get(q).getRuko();
        String nomejas = nomeja.getText().toString().trim();
        controlerkirimpenjual pesanan = new controlerkirimpenjual();
        pesanan.setParams(pesanan1,trans,ruko,nomejas);
        pesanan.doInBackground();
    }

    public void kurangstok(){
        controlerlihatstok lihat = new controlerlihatstok(this);
        lihat.execute(lihatstok,ambil.get(q).getRuko().toString(),ambil.get(q).getMakanan().toString());
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        q=i;
        int harga = Integer.parseInt(ambil.get(i).getHarga());
            if (saldo < harga) {
                Toast.makeText(waitinglist3.this, "saldo tidak cukup", Toast.LENGTH_SHORT).show();

            } else if (a.toString().isEmpty()) {
                Toast.makeText(waitinglist3.this, "no meja belum diisi", Toast.LENGTH_SHORT).show();
            } else {
                String sisasaldo = String.valueOf(saldo - harga);
                controlerupdatesaldo update = new controlerupdatesaldo();
                SignInService sig = new SignInService(this, listener);
                String a = sig.user;
                update.setParams(iki, a, sisasaldo);
                update.doInBackground();
                controlerstatusbayar statuss = new controlerstatusbayar();
                statuss.setParams(statusbayar, a, ambil.get(i).getMakanan(), ambil.get(i).getRuko(), ambil.get(i).getTransaksi());
                statuss.doInBackground();
                kirimpenjual();
                kurangstok();
                ceksaldo();
                sambildata();

        }
    }

    @Override
    public void onFetchComplete(List<namapesanan> data) {
            if(dialog != null)  dialog.dismiss();
            waitinglist1 adapter = new waitinglist1(this, data);
            ListView myList=(ListView)findViewById(R.id.listView);
            myList.setAdapter(adapter);
            ambil = data;

    }

    @Override
    public void onFetchComplete1(List<setsaldo> data) {
        TextView tex  = (TextView)findViewById(R.id.textView2);
        tex.setText(app.saldo);
        saldo= Integer.parseInt(app.saldo);

    }

    @Override
    public void potong(List<setpotong> data) {
            int a = Integer.parseInt(data.get(0).getPotong().toString());
            stokawal = a - 1;
            controlerupdatestok update = new controlerupdatestok();
            update.setParams(updatestok, ambil.get(q).getRuko(), ambil.get(q).getMakanan(), String.valueOf(stokawal));
            update.doInBackground();
    }


    @Override
    public void onFetchFailure(String msg) {

        Toast.makeText(waitinglist3.this, msg, Toast.LENGTH_SHORT).show();
    }
}

