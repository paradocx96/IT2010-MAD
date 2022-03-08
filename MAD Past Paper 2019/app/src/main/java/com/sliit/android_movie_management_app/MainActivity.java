package com.sliit.android_movie_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_login, btn_register;
    EditText inp_username, inp_password;
    List USERNAME, PASSWORD, TYPE;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        inp_username = findViewById(R.id.inp_username);
        inp_password = findViewById(R.id.inp_password);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.loginUser();
                USERNAME = dbHandler.getUSERNAME();
                PASSWORD = dbHandler.getPASSWORD();
                TYPE = dbHandler.getUSER_TYPE();
                username = inp_username.getText().toString().trim();
                password = inp_password.getText().toString().trim();

                if (USERNAME.indexOf(username) >= 0) {
                    if (PASSWORD.get(USERNAME.indexOf(username)).equals(password)) {
                        if (TYPE.get(USERNAME.indexOf(username)).equals("admin")) {
                            Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, AddMovie.class);
                            startActivity(intent);
                            clear();
                        } else {
                            Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MovieList.class);
                            startActivity(intent);
                            clear();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password incorrect Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username & Password Incorrect Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                username = inp_username.getText().toString().trim();
                password = inp_password.getText().toString().trim();

                long result = dbHandler.registerUser(username,password);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Registration Successfully Done!", Toast.LENGTH_SHORT).show();
                    clear();
                } else {
                    Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clear() {
        inp_username.setText(null);
        inp_password.setText(null);
    }
}