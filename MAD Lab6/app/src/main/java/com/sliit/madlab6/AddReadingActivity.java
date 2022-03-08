package com.sliit.madlab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddReadingActivity extends AppCompatActivity {

    int flag;
    TextView lblAuthor, lblTitle;
    EditText txtTitle, txtAuthor;
    Button view;
    String title, author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);

        lblAuthor = findViewById(R.id.lblAuthor);
        lblTitle = findViewById(R.id.lblTitle);
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        view = findViewById(R.id.btn_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            flag = bundle.getInt("number");
            changeFragment();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = txtTitle.getText().toString().trim();
                author = txtAuthor.getText().toString().trim();

                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(AddReadingActivity.this);
                builder.setTitle("Your Reading!");
                builder.setMessage(title + " by " + author);
                builder.setPositiveButton("OK",null);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void changeFragment() {
        if (flag == 1) {
            lblAuthor.setText("Author: ");
            lblTitle.setText("Book Title: ");
            Fragment fragment1;
            fragment1 = new BookFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment1);
            fragmentTransaction.commit();
        }
        else {
            lblAuthor.setText("Editor: ");
            lblTitle.setText("Article Title: ");
            Fragment fragment2;
            fragment2 = new PaperFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment2);
            fragmentTransaction.commit();
        }
    }
}