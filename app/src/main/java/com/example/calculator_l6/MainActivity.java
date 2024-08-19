package com.example.calculator_l6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstValue = Double.NaN;
    private double secondValue;
    private char currentOperation;
    private Button hiddenButton;
    private boolean isHiddenButtonVisible = false;
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        hiddenButton = findViewById(R.id.hiddenButton);
        hiddenButton.setVisibility(View.GONE);

        assignButtonListeners();
        hiddenButton = findViewById(R.id.hiddenButton);
        display = findViewById(R.id.display);

        hiddenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", display.getText().toString());
            startActivity(intent);
        });

        Button buttonEqual = findViewById(R.id.button_equals);
        buttonEqual.setOnClickListener(v -> {
            String result = calculateResult();
            display.setText(result);
            hiddenButton.setVisibility(View.VISIBLE);
            isResultDisplayed = true;
        });


        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(v -> {
            display.setText("0");
            if (isResultDisplayed) {
                hiddenButton.setVisibility(View.GONE);
                isResultDisplayed = false;
            }
        });
    }

    private void assignButtonListeners() {
        findViewById(R.id.button_0).setOnClickListener(v -> appendNumber("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> appendNumber("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> appendNumber("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> appendNumber("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> appendNumber("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> appendNumber("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> appendNumber("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> appendNumber("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> appendNumber("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> appendNumber("9"));

        findViewById(R.id.button_add).setOnClickListener(v -> performOperation('+'));
        findViewById(R.id.button_subtract).setOnClickListener(v -> performOperation('-'));
        findViewById(R.id.button_multiply).setOnClickListener(v -> performOperation('*'));
        findViewById(R.id.button_divide).setOnClickListener(v -> performOperation('/'));

        findViewById(R.id.button_equals).setOnClickListener(v -> {
            calculateResult();
            hiddenButton.setVisibility(View.VISIBLE);
            isHiddenButtonVisible = true;
        });

        hiddenButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", display.getText().toString());
            startActivity(intent);
        });


        View.OnClickListener generalButtonClickListener = v -> {
            if (isHiddenButtonVisible) {
                hiddenButton.setVisibility(View.GONE);
                isHiddenButtonVisible = false;
            }
        };

        findViewById(R.id.button_clear).setOnClickListener(generalButtonClickListener);
        findViewById(R.id.button_clear_entry).setOnClickListener(generalButtonClickListener);

    }

    private void appendNumber(String number) {
        display.append(number);
    }

    private void performOperation(char operation) {
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(display.getText().toString());
            calculateResult();
            currentOperation = operation;
        } else {
            firstValue = Double.parseDouble(display.getText().toString());
            currentOperation = operation;
        }
        display.setText("");
    }

    private String calculateResult() {
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(display.getText().toString());
            switch (currentOperation) {
                case '+':
                    firstValue += secondValue;
                    break;
                case '-':
                    firstValue -= secondValue;
                    break;
                case '*':
                    firstValue *= secondValue;
                    break;
                case '/':
                    firstValue /= secondValue;
                    break;
            }
            display.setText(String.valueOf(firstValue));
            firstValue = Double.NaN;
            currentOperation = ' ';
        }
        return null;
    }
}
