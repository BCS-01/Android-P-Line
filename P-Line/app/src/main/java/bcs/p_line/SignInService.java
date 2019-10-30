package bcs.p_line;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;

public class SignInService {

    List<NameValuePair> nameValuePairs;
    private static final String TAG = "SignInService";
    bcs.p_line.Listener listener;
    String serverResponse=null;
    Context context;
    Utils utils;
    Logger logger;
    Constants constants;
    public static String user;

    public SignInService(Context context, bcs.p_line.Listener listener) {
        this.context = context;
        this.listener = listener;
        utils = new bcs.p_line.Utils(context);
    }

    public void setParams(String username, String pwd) {
        nameValuePairs = new ArrayList<NameValuePair>();
        //These two variables should be same as in the php side...
        nameValuePairs.add(new BasicNameValuePair("id_user", username));
        nameValuePairs.add(new BasicNameValuePair("password", pwd));
        user=username;


    }

    public void doSignIn() {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    logger.logInfo(TAG, constants.SIGN_IN);
                    msg = utils.passDataToServer(constants.SIGN_IN, nameValuePairs);
                } catch (Exception ex) {
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                logger.logInfo(TAG, "Login Response From Server : " + msg);
                createMessageToHandler(msg);
            }

            public void createMessageToHandler(String msg) {
                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString(constants.SERVER_RESPONSE, msg);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }

            // Define the Handler that receives messages from the thread and
            // update the progress
            @SuppressLint("HandlerLeak")
            private final Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    serverResponse = msg.getData().getString(constants.SERVER_RESPONSE);
                    if ((null != serverResponse) && !serverResponse.equalsIgnoreCase("")) {

                        try {

                            //You will receive server response here
                            // Parse the data here and store data using preferences
                            //
                            listener.onSuccessful("welcome");
                            listener.onUser(serverResponse);
                        } catch (Exception e) {
                            //e.printStackTrace();
                            listener.onError(constants.ERROR_DATA_FROM_SERVER);
                        }
                    } else {
                        listener.onError(constants.NO_DATE_FROM_SERVER);
                    }
                }

            };
        }.execute(null, null, null);
    }


}