package bcs.p_line;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class Utils {

	static Context context;
	public static final String TAG = "Utils";
	public static final String PREF_FILE_NAME = "pref";

	HttpPost httppost;
	HttpGet httpget;
	HttpClient httpclient;

	ProgressDialog pb;

	public Utils(Context context) {
		Utils.context = context;
	}

	public static void showToast(final String txt, final int length) {
		((Activity) context).runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(context, txt, length).show();
			}
		});
	}

	public void showPB(final String message) {
		((FragmentActivity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				pb = new ProgressDialog(context);
				pb.setCancelable(false);
				pb.setMessage(message);
				pb.show();
			}
		});
	}

	public void hidePB() {
		((FragmentActivity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (pb != null && pb.isShowing())
					pb.dismiss();
			}
		});
	}

	public String passDataToServer(String url, List<NameValuePair> nameValuePairs) throws ClientProtocolException, IOException {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is
		// established.
		// The default value is zero, that means the timeout is not used.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 3000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpclient = new DefaultHttpClient(httpParameters);
		httppost = new HttpPost(url);
		if (nameValuePairs != null && nameValuePairs.size() > 0)
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		return httpclient.execute(httppost, responseHandler);
	}

	
}
