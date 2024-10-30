package com.example.mipt_3;

import static android.text.method.TextKeyListener.clear;
import java.lang.Math;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity{

    StringBuilder currentInput;
    double operand1, operand2;
    String operator;
    TextView tv_result;
    MaterialButton buttonDel,buttonC,buttonSymbol,buttonEquals;
    MaterialButton button7,button8,button9,button4,button5,button6,button3,button2,button1,button0;
    MaterialButton buttonDivide,buttonSqrt,buttonMultiplication,buttonPlus,buttonMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_result=findViewById(R.id.tv_results);
        currentInput = new StringBuilder();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setNumberListeners();
        setOperatorListeners();
    }

    void setNumberListeners() {
        int[] numberButtons = {
                R.id.button_zero, R.id.button_one, R.id.button_two,
                R.id.button_three, R.id.button_four, R.id.button_five,
                R.id.button_six, R.id.button_seven, R.id.button_eight,
                R.id.button_nine
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            currentInput.append(b.getText());
            tv_result.setText(currentInput.toString());
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }




    }
    void setOperatorListeners() {
        findViewById(R.id.button_clear).setOnClickListener(v -> clear());
        findViewById(R.id.button_backspace).setOnClickListener(v -> useBackspace());
        findViewById(R.id.button_symbol ).setOnClickListener(v -> changeSymbol());
        findViewById(R.id.button_plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.button_multiplication).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.button_divide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.button_sqrt).setOnClickListener(v -> calculateSqrt());
        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
    }
    void setOperator(String op) {
        if (currentInput.length() > 0) {
            operand1 = Double.parseDouble(currentInput.toString());
            operator = op;
            currentInput.setLength(0);
            tv_result.setText(op);
        }
    }
    void calculateSqrt(){
        if (currentInput.length() > 0) {
            operand1 = Double.parseDouble(currentInput.toString());
            double result;
            result = Math.sqrt(operand1);
            tv_result.setText(String.valueOf(result));
            currentInput.setLength(0);
            operator = null;
        }
    }
    void useBackspace(){
    if (currentInput.length()>0){
        currentInput.deleteCharAt(currentInput.length() - 1);
        tv_result.setText(currentInput.length() > 0 ? currentInput.toString() : "0");
    }
    }
    void changeSymbol(){
        if (currentInput.length() > 0) {
            if (currentInput.charAt(0) == '-') {
                currentInput.deleteCharAt(0);
            } else {
                currentInput.insert(0, '-');
            }
            tv_result.setText(currentInput.toString());
        }
    }
    void calculateResult() {
        if (currentInput.length() > 0 && operator != null) {
            operand2 = Double.parseDouble(currentInput.toString());
            double result;

            switch (operator) {
                case "+": result = operand1 + operand2; break;
                case "-": result = operand1 - operand2; break;
                case "*": result = operand1 * operand2; break;
                case "/":
                    if (operand2 == 0) {
                        tv_result.setText("Error");
                        return;
                    }
                    result = operand1 / operand2;
                    break;
                default: return;
            }

            tv_result.setText(String.valueOf(result));
            currentInput.setLength(0);
            operator = null;
        }
    }
    void clear() {
        operand1 = operand2 = 0;
        operator = null;
        currentInput.setLength(0);
        tv_result.setText("0");
    }
}