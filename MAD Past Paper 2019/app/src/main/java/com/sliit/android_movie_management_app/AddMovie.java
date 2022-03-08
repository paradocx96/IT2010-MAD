package com.sliit.android_movie_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    Button btn_add;
    EditText M_Name, M_Year;
    String mName;
    int mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        btn_add = findViewById(R.id.btn_add);
        M_Name = findViewById(R.id.inp_movieName);
        M_Year =findViewById(R.id.inp_movieYear);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                mName = M_Name.getText().toString().trim();
                mYear = Integer.parseInt(M_Year.getText().toString().trim());

                boolean result = dbHandler.addMovie(mName,mYear);
                if (result == true) {
                    Toast.makeText(AddMovie.this, "Movie Successfully Added!", Toast.LENGTH_SHORT).show();
                    clear();
                } else {
                    Toast.makeText(AddMovie.this, "Movie Adding Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clear() {
        M_Name.setText(null);
        M_Year.setText(null);
    }
}