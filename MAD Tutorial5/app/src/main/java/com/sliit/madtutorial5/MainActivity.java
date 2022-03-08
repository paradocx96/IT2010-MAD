package com.sliit.madtutorial5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sliit.madtutorial5.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button selectAll, add, delete, update, signIn;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectAll = (Button) findViewById(R.id.btn_selectAll);
        add = (Button) findViewById(R.id.btn_add);
        delete = (Button) findViewById(R.id.btn_delete);
        update = (Button) findViewById(R.id.btn_update);
        signIn = (Button) findViewById(R.id.btn_signIn);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
    }

    public void addData(View view) {
        DBHandler dbHandler = new DBHandler(this);

        long result = dbHandler.addInfo(username.getText().toString().trim(),password.getText().toString().trim());

        if (result > 0) {
            Toast.makeText(this, "Data Successfully Added!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data Adding Failed!", Toast.LENGTH_SHORT).show();
        }

        clear();
    }

    public void updateData (View view) {
        DBHandler dbHandler = new DBHandler(this);

        int result = dbHandler.updateInfo(username.getText().toString().trim(),password.getText().toString().trim());

        if (result > 0) {
            Toast.makeText(this, "User Update Successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "User Update Failed!", Toast.LENGTH_SHORT).show();
        }

        clear();
    }

    public void selectAllData (View view) {
        DBHandler dbHandler = new DBHandler(this);

        List result = dbHandler.readUserInfo("User");

        Toast.makeText(this,result.toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteData (View view) {
        DBHandler dbHandler = new DBHandler(this);

        int result = dbHandler.deleteInfo(username.getText().toString().trim());

        if (result > 0) {
            Toast.makeText(this, "User Delete Successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "User Delete Failed!", Toast.LENGTH_SHORT).show();
        }

        clear();
    }

    public void signIn (View view) {
        DBHandler dbHandler = new DBHandler(this);

        List USERNAME = dbHandler.readUserInfo("user");
        List PASSWORD = dbHandler.readUserInfo("password");

        String un = username.getText().toString().trim();
        String pw = password.getText().toString().trim();

        if (USERNAME.indexOf(un) >= 0) {
            if (PASSWORD.get(USERNAME.indexOf(un)).equals(pw)) {
                Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                clear();
            }
            else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Login Function Failed!", Toast.LENGTH_SHORT).show();
            clear();
        }


    }

    public void clear(){
        username.setText(null);
        password.setText(null);
    }
}