package zenlife.nox.nox;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    TextView notificationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        checkNetWork();

        notificationTxt = (TextView) findViewById(R.id.nofiticationTxt);
        notificationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationTxt.setText("Loading");
                checkNetWork();
            }
        });

    }

    private void checkNetWork(){
        if(isOnline()){
            //chuyen man hinh
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveToHomeActivity();
                }
            }, 200);

        } else {
            //No internet accessing
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayNoConnectionInternet();
                }
            }, 200);
        }
    }
    private void displayNoConnectionInternet() {
        notificationTxt.setText("No internet connecition \nTap to try again!");
    }

    private void moveToHomeActivity() {
        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
