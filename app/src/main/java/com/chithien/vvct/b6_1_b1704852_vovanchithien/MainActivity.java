package com.chithien.vvct.b6_1_b1704852_vovanchithien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_type, btn_web;
    ConnectivityManager connectivityManager;
    TextView show,show2;
    EditText link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_type = findViewById(R.id.btn_type);
        btn_web = findViewById(R.id.btn_web);
        show = findViewById(R.id.show);
        show2 = findViewById(R.id.show2);
        link = findViewById(R.id.link);

        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = link.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null) {
                    show2.setVisibility(View.VISIBLE);
                    // connected to the internet
                    switch (info.getType()) {
                        case ConnectivityManager.TYPE_WIFI:
                            // connected to wifi
                            show.setText("Wifi" + "--");
                            show2.setText("Network OK");
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                            // connected to mobile data
                            show.setText("3G" + "--");
                            show2.setText("Network OK");
                            break;
                        default:
                            break;
                    }
                } else if (info == null){
                    show.setText("No default network is currently active");
                } else if (!info.isConnected()){
                    show.setText("Network not available");
                }
            }
        });
    }
}