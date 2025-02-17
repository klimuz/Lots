package com.klimuz.lots;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NamesActivity extends AppCompatActivity {

    private EditText editTextNames;
    private String persons = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        editTextNames = findViewById(R.id.editTextNames);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            persons = extras.getString("persons");
        }
        editTextNames.setText(persons);
    }

    public void buttonAcceptNamesPressed(View view) {
        String inputText = editTextNames.getText().toString();
        inputText = inputText.replaceAll("\\.$", "");
        if (inputText.contains(".")) {
            Intent intent = new Intent(this, LotsActivity.class);
            intent.putExtra("namesString", inputText);
            startActivity(intent);
        } else {
            Toast.makeText(this, "enter at least 2 names", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonBackNamesPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}