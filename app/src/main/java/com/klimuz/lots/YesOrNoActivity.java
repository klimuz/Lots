package com.klimuz.lots;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class YesOrNoActivity extends AppCompatActivity {

    TextView textViewDecision;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_or_no);

        textViewDecision = findViewById(R.id.textViewDecision);
        mPlayer= MediaPlayer.create(this, R.raw.coin);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
                int randomVal = new Random().nextInt(2);
                String decisionText;
                if (randomVal == 0) {
                    decisionText = getString(R.string.no_text);
                    textViewDecision.setTextColor(Color.RED);
                } else {
                    decisionText = getString(R.string.yes_text);
                    textViewDecision.setTextColor(Color.GREEN);
                }
                textViewDecision.setText(decisionText);
            }
        });
    }
    private void stopPlay(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }

    public void buttonDecidePressed(View view) {
        textViewDecision.setText(R.string.wait_text);
        mPlayer.start();
    }

    public void buttonCancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void buttonQuitPressed(View view) {
        finishAffinity();
    }
}