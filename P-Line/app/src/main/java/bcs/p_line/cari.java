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

public class cari extends AsyncTask<String, String, String> {
    private final FetchDataListener listener;
    private String msg;
    static InputStream is;
    List<NameValuePair> nameValuePairs;
    public static int stok;

    public cari(FetchDataListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        if(params == null) return null;
        // get url from params
        String n_menu = params[0];
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("n_menu",n_menu));

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.56.1/cari.php");
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
                listener.onFetchFailure(msg);
            }
            return;
        }

        try {

            // convert json string to json array
            JSONArray aJson = new JSONArray(sJson);
            List<application> apps = new ArrayList<application>();
            for(int i=0; i<aJson.length(); i++) {
                JSONObject json = aJson.getJSONObject(i);
                application app = new application();
                app.setRuko(json.getString("id_ruko"));
                app.setImage(json.getString("image"));
                app.setN_menu(json.getString("n_menu"));
                app.setStok(Integer.parseInt(json.getString("stok")));
                app.setHarga(Integer.parseInt(json.getString("harga")));
                app.setJenis(json.getString("jenis"));
                // add the app to apps list
                apps.add(app);

            }
            //notify the activity that fetch data has been complete
            if(listener != null) {
                listener.onFetchComplete(apps);
            }
        } catch (JSONException e) {

            msg = "Invalid response";
            if(listener != null) listener.onFetchFailure(msg);
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
