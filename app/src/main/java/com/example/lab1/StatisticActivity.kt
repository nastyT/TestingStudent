package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StatisticActivity : AppCompatActivity() {
    private lateinit var textViewRight: TextView
    private lateinit var textViewRes: TextView

    companion object {
        val TOTAL_RESULT = "total_result"
        val TOTAL_RIGHT = "total_Right"
    }

    override fun onCreate(savedInstanceState: Bundle?) { //вывод результатов
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)
        textViewRight = findViewById(R.id.textRight)
        textViewRes = findViewById(R.id.textResults)
        val RightAnswer=intent.getIntExtra(TOTAL_RIGHT,0)
        val totalResults=intent.getDoubleExtra(TOTAL_RESULT,0.0)
        textViewRight.text = Integer.toString(RightAnswer)
        textViewRes.text = totalResults.toString()
    }
    fun exitButton(view: View){ //кнопка выхода
        this.finishAffinity();
    }

    fun againButton(view: View){ //начать снова
        val startIntent = Intent(this, MainActivity::class.java)
        startActivity(startIntent)
    }
}