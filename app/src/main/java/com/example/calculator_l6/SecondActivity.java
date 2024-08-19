package com.example.calculator_l6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String result = getIntent().getStringExtra("result");
        TextView resultView = findViewById(R.id.resultView);
        resultView.setText(result);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(v -> {
            finishAffinity();
        });


        ImageButton heartButton = findViewById(R.id.heartButton);
        heartButton.setOnClickListener(v -> {
            boolean isSelected = heartButton.isSelected();
            heartButton.setSelected(!isSelected);
            heartButton.setImageResource(isSelected ? R.drawable.ic_heart_outline : R.drawable.ic_heart_filled);
        });


        Spinner dropdown = findViewById(R.id.dropdown);
        SeekBar rangeSeekBar = findViewById(R.id.rangeSeekBar);

    }
}
