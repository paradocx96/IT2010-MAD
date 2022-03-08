package com.sliit.madlab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ok = findViewById(R.id.btn_ok);
        EditText readerName = findViewById(R.id.editTextName);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ReadingCollectionsActivity.class);
                String reader = readerName.getText().toString().trim();
                intent.putExtra("readerName",reader);
                startActivity(intent);
            }
        });
    }
}