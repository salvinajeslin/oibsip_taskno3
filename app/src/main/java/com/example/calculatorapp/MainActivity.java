package com.example.calculatorapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        setNumberListeners();
        setOperatorListeners();
    }
    private void setNumberListeners() {
        int[] numberIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentInput += button.getText().toString();
                display.setText(currentInput);
            }
        };
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }
    private void setOperatorListeners() {
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                operator = "";
                firstOperand = 0;
                display.setText("0");
            }
        });
        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
            }
        };
        findViewById(R.id.button_add).setOnClickListener(operatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(operatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(operatorListener);
        findViewById(R.id.button_divide).setOnClickListener(operatorListener);
    }
    private void calculateResult() {
        double secondOperand = Double.parseDouble(currentInput);
        double result = 0;
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "×":
                result = firstOperand * secondOperand;
                break;
            case "÷":
                result = firstOperand / secondOperand;
                break;
        }
        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
    }
}
