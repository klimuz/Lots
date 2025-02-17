package com.klimuz.lots;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonYesNoPressed(View view) {
        Intent intent = new Intent(this, YesOrNoActivity.class);
        startActivity(intent);
    }

    public void buttonLotsPressed(View view) {
        Intent intent = new Intent(this, NamesActivity.class);
        startActivity(intent);
    }
}