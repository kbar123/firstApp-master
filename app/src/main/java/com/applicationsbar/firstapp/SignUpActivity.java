package com.applicationsbar.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.applicationsbar.firstapp.MainActivity.EXTRA_MESSAGE;

public class SignUpActivity extends AppCompatActivity {

    SignUpActivity appContext = this;
    public static TCPClient tcpClient ;
    Intent intent;
    public static String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void saveUserToDatabase(View view) {
        String password = ((EditText) findViewById(R.id.s_password)).getText().toString();
        String username = ((EditText) findViewById(R.id.s_username)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.s_first)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.s_last)).getText().toString();
        String signUpMessage="";
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            ((TextView) findViewById(R.id.signUpMessage)).setText("Please fill in all the fields");
            return;
        }
        try {
            signUpMessage="signUp"+"&password="+URLEncoder.encode(password, "UTF-8")+"&username="+URLEncoder.encode(username, "UTF-8")+"&firstName="+URLEncoder.encode(firstName, "UTF-8")+"&lastName="+URLEncoder.encode(lastName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        tcpClient = new TCPClient(new TCPClient.AsyncResponse()
        {

            @Override
            public void processFinish(int messageType, byte[] message) {

                try {
                    TCPClient.clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (messageType==1) {
                    String response = new String(message);
                    System.out.println(response);

                    if (response.contains("Successfully")) {
                        intent = new Intent(appContext, LogInActivity.class);
                        startActivity(intent);
                    }

                    else {

                        System.out.println(response);
                    }
                }
                else {
                    ((TextView) findViewById(R.id.signUpMessage)).setText("Sign Up Failed");
                    System.out.println("Sign Up failed");
                }



            }
        },  signUpMessage);
        tcpClient.execute();

    }
}
