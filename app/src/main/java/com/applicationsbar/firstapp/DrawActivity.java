package com.applicationsbar.firstapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;


public class DrawActivity extends AppCompatActivity {

    private PaintView paintView;
    public int color = R.color.gold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        this.setFinishOnTouchOutside(false);
        paintView = (PaintView) findViewById(R.id.paintView);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int w=paintView.getWidth();
        int h=paintView.getHeight();
        paintView.init( paintView.getWidth(), paintView.getHeight());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void sendDrawing(View view) {

        try {

            ByteArrayOutputStream os= new ByteArrayOutputStream();
            paintView.mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            TCPClient.ba=os.toByteArray();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changeToWhite(View view)
    {
        paintView.changeColor(R.color.white);
    }

    public void changeToRed(View view)
    {
        paintView.changeColor(R.color.red);
    }
    public void changeToYellow(View view)
    {
        paintView.changeColor(R.color.yellow);
    }
    public void changeToGreen(View view)
    {
        paintView.changeColor(R.color.green);
    }
    public void changeToBlue(View view)
    {
        paintView.changeColor(R.color.blue);
    }

    public int getColor()
    {
        return this.color;
    }

}
