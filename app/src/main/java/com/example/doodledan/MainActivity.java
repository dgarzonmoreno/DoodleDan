package com.example.doodledan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newGame = findViewById(R.id.new_game_btn);
        Button exitApp = findViewById(R.id.exit_btn);

        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToInGame = new Intent(MainActivity.this, InGameActivity.class);
                startActivity(goToInGame);
            }
        });

        exitApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}
