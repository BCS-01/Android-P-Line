package bcs.p_line;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;

public class controlerlihattransaksi extends AsyncTask<String, String, String> {
    private final gettransaksi listener;
    private String msg;
    static InputStream is;
    List<NameValuePair> nameValuePairs;
    public static int stok;

    public controlerlihattransaksi(gettransaksi listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        if(params == null) return null;
        // get url from params
        String url = params[0];
        String id_ruko = params[1];
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id_ruko",id_ruko));

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            return streamToString(is);
        }
        catch(IOException e){
            msg = "No Network Connection";
        }

        return null;
    }

    @Override
    protected void onPostExecute(String sJson) {
        if(sJson == null) {
            if(listener != null) {
                listener.onFetchFailuretrans(msg);
            }
            return;
        }

        try {

            // convert json string to json array
            JSONArray aJson = new JSONArray(sJson);
            List<transaksi> apps = new ArrayList<transaksi>();
            for(int i=0; i<aJson.length(); i++) {
                JSONObject json = aJson.getJSONObject(i);
                transaksi app = new transaksi();
                app.setId_user(Integer.parseInt(json.getString("id_user")));
                app.setId_transaksi(Integer.parseInt(json.getString("id_transaksi")));
                app.setN_menu(json.getString("n_menu"));
                // add the app to apps list
                apps.add(app);

            }
            //notify the activity that fetch data has been complete
            if(listener != null) {
                listener.onFetchCompletetrans(apps);
            }
        } catch (JSONException e) {

            msg = "Invalid response";
            if(listener != null) listener.onFetchFailuretrans(msg);
        }
    }

    /**
     * This function will convert response stream into json string
     * @param is respons string
     * @return json string
     * @throws IOException
     */
    public String streamToString(final InputStream is) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                throw e;
            }
        }
        return sb.toString();

    }

}
