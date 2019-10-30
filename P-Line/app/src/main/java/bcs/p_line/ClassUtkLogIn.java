package bcs.p_line;


        import android.content.Intent;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.support.v7.app.ActionBarActivity;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;


public class ClassUtkLogIn extends ActionBarActivity implements OnClickListener {

    EditText isiNamaUser, isiPassword;
    ImageButton tombolLogin;
    bcs.p_line.Listener listener;
    Utils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utils = new Utils(this);
        // utk kotak input yg perlu di di isi pengguna
        isiNamaUser = (EditText) findViewById(R.id.ID);
        isiPassword = (EditText) findViewById(R.id.password);
        // utk tombol
        tombolLogin = (ImageButton) findViewById(R.id.blogin);

        // agar tombol bisa di klik
        tombolLogin.setOnClickListener(this);
        listener = new bcs.p_line.Listener() {
            public void onUser(String message){

                if (message.equals("pembeli")){
                    Intent pembeli = new Intent(ClassUtkLogIn.this, homepembeli.class);
                    startActivity(pembeli);

                } else if(message.equals("penjual")){
                    Intent penjual = new Intent(ClassUtkLogIn.this, homepenjual.class);
                    startActivity(penjual);
                }
            }

            @Override
            public void onSuccessful(String message) {
                Utils.showToast(message, Toast.LENGTH_LONG);
                utils.hidePB();
                //Write the Login here to navigate to the Next page after Login
            }

            @Override
            public void onError(String message) {
                Utils.showToast(message, Toast.LENGTH_LONG);
                utils.hidePB();
                //Write the Logic here to show the error message to the user
            }
        };

    }


    @Override
    public void onClick(View v) {
                utils.showPB("Signing in...");
                SignInService signInService = new SignInService(this, listener);
                signInService.setParams(isiNamaUser.getText().toString().trim(), isiPassword.getText().toString().trim());
                signInService.doSignIn();

    }


}

