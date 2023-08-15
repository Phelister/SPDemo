package com.example.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //shared preferences
    private  lateinit var textName : EditText
    private  lateinit var textAge : EditText
    private lateinit var  sharedPref:SharedPreferences
    private lateinit var  editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textName = findViewById(R.id.etName)
        textAge=findViewById(R.id.etNumber)
        sharedPref=getSharedPreferences("my_sf", MODE_PRIVATE)
        editor=sharedPref.edit();
    }

    override fun onPause() {
        super.onPause()
        val name =textName.text.toString()
        val age=textAge.text.toString().toInt()
        editor.apply{
            putString("name",name)
            putInt("age", age)
            commit()//ensures values are saved
        }
    }

    override fun onResume() {
        super.onResume()
        val name =sharedPref.getString("name",null)
        val age=sharedPref.getInt("age",0)
        textName.setText(name)
        if(age!=0) {
            textAge.setText(age.toString())
        }
    }
}

