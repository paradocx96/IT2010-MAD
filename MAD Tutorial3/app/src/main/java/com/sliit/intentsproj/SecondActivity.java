package com.sliit.intentsproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText etNumber1, etNumber2;
    String number1, number2;
    int num1, num2;
    TextView lbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Defining the text objects
        etNumber1 = findViewById(R.id.numDisplay1);
        etNumber2 = findViewById(R.id.numDisplay2);
        lbl = findViewById(R.id.txt_view);

        Intent intent = getIntent();

        number1 = intent.getStringExtra("n1");
        number2 = intent.getStringExtra("n2");

        etNumber1.setText(number1);
        etNumber2.setText(number2);

        num1 = Integer.parseInt(number1);
        num2 = Integer.parseInt(number2);
    }

    public void add(View view) {
        lbl.setText(number1 + " + " + number2 + " = " + (num1+num2));
    }

    public void sub(View view) {
        lbl.setText(number1 + " - " + number2 + " = " + (num1-num2));
    }

    public void multiply(View view) {
        lbl.setText(number1 + " * " + number2 + " = " + (num1*num2));
    }

    public void dividing(View view) {
        lbl.setText(number1 + " / " + number2 + " = " + (num1/num2));
    }
}