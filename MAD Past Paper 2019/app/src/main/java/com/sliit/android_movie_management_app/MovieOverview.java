package com.sliit.android_movie_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieOverview extends AppCompatActivity {

    TextView movie_name, current_rating, commentView, changeRate;
    Button btn_submit;
    SeekBar seekBar;
    EditText inp_comment;
    String movieName, comment;
    ListView list_comment;
    List commentList = new ArrayList<>();
    List commentRating = new ArrayList<>();
    List MOVIE_NAME = new ArrayList<>();
    DBHandler dbHandler;
    int seekBarValue, totalRate, rateValue;
    float avarageRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        movie_name = findViewById(R.id.txt_movie_name);
        current_rating = findViewById(R.id.txt_currentRating);
        seekBar = findViewById(R.id.seek_rate);
        inp_comment = findViewById(R.id.inp_comment);
        list_comment = (ListView) findViewById(R.id.list_comments);
        commentView = (TextView) findViewById(R.id.txt_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        changeRate = (TextView) findViewById(R.id.txt_changeRate);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            movieName = (String) bundle.get("movieName");
            movie_name.setText(movieName);
        }

        // Showing Comments in a List
        // Getting Comments and relevant data from DB
        dbHandler = new DBHandler(getApplicationContext());
        SQLiteDatabase commentTable = dbHandler.getReadableDatabase();

        // Getting data from DB and add to List
        dbHandler.viewComments();
        commentList = dbHandler.getMOVIE_COMMENTS();
        commentRating = dbHandler.getMOVIE_RATING();
        MOVIE_NAME = dbHandler.getMOVIE_NAME();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,commentList);
        list_comment.setAdapter(adapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekBar.setMax(10);
                // seekBar.setProgress(5);
                seekBarValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                changeRate.setText(String.valueOf(seekBarValue));
            }
        });

        int i = commentRating.size();
        for (int x = 0; x < i; x++) {
            rateValue = (Integer) commentRating.get(0);
            totalRate += rateValue;
        }
        avarageRate = totalRate / i;
        current_rating.setText(String.valueOf(avarageRate));
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler = new DBHandler(getApplicationContext());

                comment = inp_comment.getText().toString().trim();

                long result = dbHandler.insertComments(movieName,seekBarValue,comment);
                if (result > 0) {
                    Toast.makeText(MovieOverview.this, "Comment Added!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MovieOverview.this, MovieOverview.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MovieOverview.this, "Comment Adding Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}