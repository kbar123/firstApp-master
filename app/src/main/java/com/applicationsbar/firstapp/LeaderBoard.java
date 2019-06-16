package com.applicationsbar.firstapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

public class LeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        this.setFinishOnTouchOutside(false);
        String message = getIntent().getStringExtra("message");
        String [] arr = message.split("&");
        TextView t1 = findViewById(R.id.pl1);
        TextView t2 = findViewById(R.id.pl2);
        TextView t3 = findViewById(R.id.pl3);
        TextView t4 = findViewById(R.id.pl4);
        TextView t5 = findViewById(R.id.pl5);
        TextView t6 = findViewById(R.id.pl6);
        TextView t7 = findViewById(R.id.pl7);
        TextView t8 = findViewById(R.id.pl8);
        TextView t9 = findViewById(R.id.pl9);
        TextView t10 = findViewById(R.id.pl10);
        t1.setText(arr[0]);
        TextView [] arrView = {t1,t2,t3,t4,t5,t6,t7,t8,t9,t10};
        for (int i = 0; i<arr.length; i++)
        {
            arrView[i].setText(arr[i]);
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}

