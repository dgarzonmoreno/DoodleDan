package com.example.doodledan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InGameActivity extends AppCompatActivity implements SurfaceHolder.Callback, SensorEventListener {

    float doodleXPos = 100, doodleYPos = 1, doodleRadius =100;
    Bitmap doodle = null;
    Bitmap platform = null;
    boolean bitmapsLoaded = false;
    SurfaceHolder holder = null;
    long timestamp = 0;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        SurfaceView my_surface = findViewById(R.id.my_surface);
        my_surface.getHolder().addCallback(this);


        SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        SurfaceView my_surface = findViewById(R.id.my_surface);
        float acc_x = sensorEvent.values[0];

        if(doodleYPos > getScreenHeight()) {
//            Intent t = new Intent(this, GameOverActivity.class);
//            startActivity(t);
            System.out.println("Game over");
        } else if(doodleYPos < getScreenHeight() && doodleYPos > 0){
            doodleYPos += 4;
        }

        if(doodleXPos < getScreenWidth() && doodleXPos > 0)
            doodleXPos -= acc_x * 17;
        else if(doodleXPos > getScreenWidth()){
            doodleXPos = 1;
        } else if(doodleXPos < 0){
            doodleXPos = getScreenWidth() - 1;
        }

        if(timestamp + 60 < sensorEvent.timestamp / 1000000)
        {
            timestamp = sensorEvent.timestamp / 1000000;
            redraw();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        holder = surfaceHolder;
        redraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        holder = surfaceHolder;
        redraw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void loadBitmapsAndPaints(Canvas c) {
        doodle = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dan),
                350, 350, false);
        platform = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.platform), 300, 200, false);

        bitmapsLoaded=true;
    }

    void redraw(){
        if(holder==null)
            return;

        Canvas c = holder.lockCanvas();

        if(!bitmapsLoaded)
            loadBitmapsAndPaints(c);

        draw(c);
        holder.unlockCanvasAndPost(c);
    }

    private void draw(Canvas c) {
//        final int NUM_OF_PLATFORMS = 10;
        c.drawColor(Color.GRAY);
        c.drawBitmap(doodle, doodleXPos - doodleRadius,doodleYPos + doodleRadius,null);

//        Random r = new Random();
//        for(int i = 0; i < 5; i++){
//            int x = r.nextInt(getScreenWidth());
//            int y = r.nextInt(getScreenHeight());
//            c.drawBitmap(platform, x, y, null);
//        }

        c.drawBitmap(platform, 200, 1200, null);

//        Platform platTwo = new Platform();
//        c.drawBitmap(platform, platTwo.getxPos(), platTwo.getyPos(), null);
//        platforms.add(platTwo);
//
//        Platform platThree = new Platform();
//        c.drawBitmap(platform, platThree.getxPos(), platThree.getyPos(), null);
//        platforms.add(platThree);

        //TODO: add score TextView
    }
}
