package com.sliit.intentsproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Toast toast;
    EditText etNumber1, etNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definig the text objects
        etNumber1 = findViewById(R.id.num1);
        etNumber2 = findViewById(R.id.num2);

        OnclickButtonListener();
    }

    public void OnclickButtonListener() {
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("n1", etNumber1.getText().toString());
                        intent.putExtra("n2", etNumber2.getText().toString());

                        /*Context context = getApplicationContext();
                        CharSequence text = "You just clicked the OK button";
                        int duration = Toast.LENGTH_SHORT;
                        toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);*/

                        //Creating the LayoutInflater instance
                        LayoutInflater li = getLayoutInflater();

                        //Getting the View object as defined in the customtoast.xml file
                        View layout = li.inflate(R.layout.customtoast, (ViewGroup)findViewById(R.id.custom_toast_layout));

                        //Creating the Toast object
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                        //setting the view of custom toast layout
                        toast.setView(layout);
                        toast.show();

                        startActivity(intent);
                    }
                });
    }
}