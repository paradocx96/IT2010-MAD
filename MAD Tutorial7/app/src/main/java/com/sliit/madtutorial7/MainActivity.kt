package com.sliit.madtutorial7

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputYear = findViewById<EditText>(R.id.input_year)
        val txtAge = findViewById<TextView>(R.id.txtAge)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        btn_submit.setOnClickListener{
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            txtAge.text = "Age : " + (currentYear - inputYear.text.toString().trim().toInt())
        }

        /*fun onSubmit(view: View) {
        }*/
    }


}