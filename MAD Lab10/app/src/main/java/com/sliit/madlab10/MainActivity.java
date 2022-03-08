package com.sliit.madlab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input_value = (EditText) findViewById(R.id.input_value);
        RadioButton celsius = (RadioButton) findViewById(R.id.radioButton_celsius);
        RadioButton fahrenheit = (RadioButton) findViewById(R.id.radioButton_fahrenheit);
        Button btn_convert = (Button) findViewById(R.id.btn_convert);
        TextView textView = (TextView) findViewById(R.id.textView);

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_value.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Please enter a Number!",Toast.LENGTH_SHORT).show();
                    return;
                }

                float inputValue = Float.parseFloat(input_value.getText().toString());

                if (celsius.isChecked()) {
                    textView.setText("Result : " + String.valueOf(convertToCelsius(inputValue)) + " 'C");
                    celsius.setChecked(true);
                    fahrenheit.setChecked(false);
                }
                else {
                    textView.setText("Result : " + String.valueOf(convertToFahrenheit(inputValue)) + " 'F");
                    fahrenheit.setChecked(true);
                    celsius.setChecked(false);
                }
            }
        });
    }

    public static float convertToCelsius (float value) {
        return ((value - 32) * 5 / 9);
    }

    public static float convertToFahrenheit (float value) {
        return ((value * 9) / 5) + 32;
    }
}