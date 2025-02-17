package com.klimuz.lots;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LotsActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private TextView textViewLotsDecision;
    private EditText editTextNumberOfLots;
    private String persons = "";
    private ArrayList<String> arrayList;
    private String numberOfLotsString;
    private int numberOfLots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lots);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            persons = extras.getString("namesString");
        }

        textViewLotsDecision = findViewById(R.id.textViewLotsDecision);
        editTextNumberOfLots = findViewById(R.id.editTextNumberOfLots);

        String[] personsArray = persons.split(" ");
        arrayList = new ArrayList<>(Arrays.asList(personsArray));

        mPlayer = MediaPlayer.create(this, R.raw.coin);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
                choosePersons();
            }
        });
    }

    private void stopPlay() {
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        } catch (Throwable t) {
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

    private void choosePersons() {
        StringBuilder stringBuilder = new StringBuilder();
        while (numberOfLots > 0) {
            int randomVal = new Random().nextInt(arrayList.size());
            String decisionText = arrayList.get(randomVal);
            stringBuilder.append(decisionText).append("\n");
            arrayList.remove(randomVal);
            numberOfLots--;
        }
        String decisionText = stringBuilder.toString();
        textViewLotsDecision.setText(decisionText);
    }

    public void buttonDecideLotsPressed(View view) {
        numberOfLotsString = editTextNumberOfLots.getText().toString();
        if (!numberOfLotsString.isEmpty()) {
            numberOfLots = Integer.parseInt(numberOfLotsString);
            if (numberOfLots < arrayList.size()) {
                mPlayer.start();
            } else {
                Toast.makeText(this, "number of lots must be less than number of people!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "fill lots number!", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonBackLotsPressed(View view) {
        Intent intent = new Intent(this, NamesActivity.class);
        intent.putExtra("persons", persons);
        startActivity(intent);
    }

    public void buttonQuitPressed(View view) {
        finishAffinity();
    }
}