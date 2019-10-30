package bcs.p_line;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bcs.p_line.R;

/**
 * Created by BrianCahSantai on 4/30/2015.
 */
public class controlerstatusbayar extends AsyncTask<Void, Void, String> {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    List<NameValuePair> nameValuePairs;
    String host;



    public void setParams(String url, String id_user, String n_menu,String ruko,String transaksi) {
        nameValuePairs = new ArrayList<NameValuePair>();
        //These two variables should be same as in the php side...
        nameValuePairs.add(new BasicNameValuePair("id_user",id_user));
        nameValuePairs.add(new BasicNameValuePair("n_menu", n_menu));
        nameValuePairs.add(new BasicNameValuePair("ruko", ruko));
        nameValuePairs.add(new BasicNameValuePair("id_transaksi",transaksi));
        host = url;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            // create http connection
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(host);

            // connect
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);


            // get response
            HttpEntity entity = response.getEntity();

            is = entity.getContent();

        } catch (ClientProtocolException e) {
            Log.e("client protocol", "Log_tag");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return null;
    }


}
