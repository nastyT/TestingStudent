package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun exitButton(view: View){ //выход
        this.finishAffinity()
    }
    fun testButton(view: View){ //кнопка для входа на тестирование
        val testIntent = Intent(this, TestingPart::class.java)
        startActivity(testIntent)
    }
}