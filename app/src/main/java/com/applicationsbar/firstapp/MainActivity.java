package com.applicationsbar.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    MainActivity appContext=this;
    public static TCPClient tcpClient;
    Intent intent;
    public static String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void loginPage(View view) {

        intent = new Intent(appContext, LogInActivity.class);

        startActivity(intent);
    }

    public void signUpPage(View view)
    {
        intent = new Intent(appContext, SignUpActivity.class);
        startActivity(intent);
    }
}
