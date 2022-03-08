package com.sliit.madlab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReadingCollectionsActivity extends AppCompatActivity {
    
    Button btn_book, btn_paperArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_collections);

        btn_book = findViewById(R.id.btn_book);
        btn_paperArticle = findViewById(R.id.btn_paper);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String readerName = (String) bundle.get("readerName");
            Toast.makeText(ReadingCollectionsActivity.this,"Welcome "+ readerName +"! Please enter what you read",Toast.LENGTH_SHORT).show();
        }

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadingCollectionsActivity.this,AddReadingActivity.class);
                int value = 1;
                intent.putExtra("number",value);
                startActivity(intent);
            }
        });

        btn_paperArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadingCollectionsActivity.this,AddReadingActivity.class);
                int value = 2;
                intent.putExtra("number",value);
                startActivity(intent);
            }
        });


    }
}