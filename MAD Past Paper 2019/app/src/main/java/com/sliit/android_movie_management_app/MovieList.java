package com.sliit.android_movie_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieList extends AppCompatActivity {

    ListView movie_list;
    TextView txt_name;
    List movieName = new ArrayList<>();
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movie_list = (ListView) findViewById(R.id.list_movie);
        txt_name = (TextView) findViewById(R.id.txt_name);

        dbHandler = new DBHandler(getApplicationContext());
        SQLiteDatabase movieTable = dbHandler.getReadableDatabase();

        dbHandler.viewMovies();
        movieName = dbHandler.getMOVIE_NAME();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,movieName);
        movie_list.setAdapter(adapter);

        movie_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MovieOverview.class);
                intent.putExtra("movieName", value);
                startActivity(intent);
            }
        });
    }
}