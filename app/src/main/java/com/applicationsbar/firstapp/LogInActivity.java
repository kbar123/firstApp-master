package com.applicationsbar.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.applicationsbar.firstapp.MainActivity.EXTRA_MESSAGE;

public class LogInActivity extends AppCompatActivity {

    LogInActivity appContext=this;
    public static TCPClient tcpClient;
    Intent intent;
    public static String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void checkLogin(View view) {
        ((TextView) findViewById(R.id.loginMessage)).setText("");
        String username = ((EditText) findViewById(R.id.l_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.l_password)).getText().toString();
        String chatroom = ((EditText) findViewById(R.id.room_number)).getText().toString();
        String loginMessage="";

        if (username.isEmpty() || password.isEmpty()){
            ((TextView) findViewById(R.id.loginMessage)).setText("Please enter username and password");
            return;

        }
        try {
            loginMessage="login"+"&password="+URLEncoder.encode(password, "UTF-8")+"&username="+URLEncoder.encode(username, "UTF-8")+"&chatroom="+URLEncoder.encode(chatroom, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        tcpClient = new TCPClient(new TCPClient.AsyncResponse()
        {

            @Override
            public void processFinish(int messageType, byte[] message) {
                if (messageType==1) {
                    String response = new String(message);
                    System.out.println(response);

                    if (response.contains("Welcome")) {
                        name = response.split("'")[1];
                        intent = new Intent(appContext, DisplayMessageActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, response);
                        startActivity(intent);
                    }

                    else {

                        System.out.println(response);
                    }
                }
                else {
                    ((TextView) findViewById(R.id.loginMessage)).setText("Login Failed");
                    System.out.println("Login failed");
                }

            }
        },  loginMessage);
        tcpClient.execute();

    }
}
