package com.example.doodledan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Button mainMenuButton = findViewById(R.id.main_menu_btn);
        Button retryButton = findViewById(R.id.retry_btn);

        retryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent retryGame = new Intent(GameOverActivity.this, InGameActivity.class);
                startActivity(retryGame);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToMainMenu = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(goToMainMenu);
            }
        });
    }
}
