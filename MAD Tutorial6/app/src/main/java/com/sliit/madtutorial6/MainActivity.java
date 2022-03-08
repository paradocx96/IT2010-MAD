package com.sliit.madtutorial6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    Student student;
    EditText id, name, address, contact;
    Button save, show, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = new Student();

        save = (Button) findViewById(R.id.btn_save);
        show = (Button) findViewById(R.id.btn_show);
        update = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);
        id = (EditText) findViewById(R.id.input_id);
        name = (EditText) findViewById(R.id.input_name);
        address = (EditText) findViewById(R.id.input_address);
        contact = (EditText) findViewById(R.id.input_contact);
    }

    public void clear() {
        id.setText(null);
        name.setText(null);
        address.setText(null);
        contact.setText(null);
    }

    public void saveData(View view) {
        reference = FirebaseDatabase.getInstance().getReference().child("Student");

        try {
            if (TextUtils.isEmpty(id.getText().toString().trim())) {
                Toast.makeText(this, "Please enter a ID", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(name.getText().toString().trim())) {
                Toast.makeText(this, "Please enter a Name", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(address.getText().toString().trim())) {
                Toast.makeText(this, "Please enter an Address", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(contact.getText().toString().trim())) {
                Toast.makeText(this, "Please enter a Contact Number", Toast.LENGTH_SHORT).show();
            }
            else {
                student.setID(id.getText().toString());
                student.setName(name.getText().toString());
                student.setAddress(address.getText().toString());
                student.setContact(Integer.parseInt(contact.getText().toString()));

                reference.push().setValue(student);
                // reference.child("Std1").setValue(student);

                Toast.makeText(this, "Data insert Successfully!", Toast.LENGTH_SHORT).show();
                clear();
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Data insert Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showData(View view) {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    id.setText(snapshot.child("id").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    address.setText(snapshot.child("address").getValue().toString());
                    contact.setText(snapshot.child("contact").getValue().toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateData(View view) {
        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("Student");

        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("Std1")) {
                    try {
                        student.setID(id.getText().toString());
                        student.setName(name.getText().toString());
                        student.setAddress(address.getText().toString());
                        student.setContact(Integer.parseInt(contact.getText().toString()));

                        reference = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                        reference.setValue(student);

                        Toast.makeText(MainActivity.this, "Data Update Successfully!", Toast.LENGTH_SHORT).show();
                        clear();
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Data Update Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteData(View view) {
        DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("Student");

        deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("Std1")) {
                    reference = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                    reference.removeValue();

                    Toast.makeText(MainActivity.this, "Data Delete Successfully!", Toast.LENGTH_SHORT).show();
                    clear();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}