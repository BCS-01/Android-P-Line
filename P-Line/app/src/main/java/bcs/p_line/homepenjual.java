package bcs.p_line;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class homepenjual extends ActionBarActivity implements Listener, AdapterView.OnItemClickListener,gettransaksi,lihatmeja,View.OnClickListener {
    String url="http://192.168.56.1/gettransaksi.php";
    String meja="http://192.168.56.1/getmeja.php";
    String meja1="http://192.168.56.1/getmeja1.php";
    int q;
    bcs.p_line.Listener listener;
    Utils utils;
    List<transaksi> data2;
    List<pesanan> data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepenjual);
        ambilmeja();
        ambiltransaksi();
        ListView myList=(ListView)findViewById(R.id.lv1);
        myList.setOnItemClickListener(this);
    }

    public void ambilmeja(){
        controlerlihatmeja1 b = new controlerlihatmeja1(this);
        SignInService a = new SignInService(this,listener);
        b.execute(meja1,a.user);
    }

    public void ambiltransaksi(){
        controlerlihattransaksi a= new controlerlihattransaksi(this);
        SignInService sig = new SignInService(this,listener);
        a.execute(url,sig.user);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        q=i;
        SignInService a =new SignInService(this,listener);
        controlerlihatmeja b = new controlerlihatmeja(this);
        b.execute(meja,a.user, String.valueOf(data1.get(i).getMeja()));
    }

    @Override
    public void onFetchCompletetrans(List<transaksi> data) {
        applicationadapterp adapter = new applicationadapterp(this,data);
        ListView myList=(ListView)findViewById(R.id.lv1);
        myList.setAdapter(adapter);

    }

    @Override
    public void onFetchFailuretrans(String msg) {

    }

    @Override
    public void potong1(List<pesanan> data) {
        Toast.makeText(homepenjual.this,"gagal", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void potong2(List<pesanan> data) {
        data1=data;
    }

    @Override
    public void onFetchFailure1(String msg) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccessful(String message) {
        Toast.makeText(homepenjual.this,message.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onUser(String message) {

    }
}
