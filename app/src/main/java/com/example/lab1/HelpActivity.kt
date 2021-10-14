package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class HelpActivity : AppCompatActivity() {

    private lateinit var HelptextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
    }

    fun yesHelp(view: View) { //если пользователь согласился на подсказку
        var totalAnswer = intent.getIntExtra("Answer", 0)
        var stringAnswer = totalAnswer.toString() //ответ переводим в строку
        val firstNumber: Char = stringAnswer.first() // нашли число для подсказки

        HelptextView = findViewById(R.id.textViewHelp)
        HelptextView.text = getString(R.string.help_heading, firstNumber)

        HelptextView.isVisible = true //подсказка стала видимой
        val buttonExit = findViewById<Button>(R.id.buttonreturn)
        buttonExit.isVisible = true //кнопка выхода появилась
        val buttonyes = findViewById<Button>(R.id.yesbutton)
        buttonyes.isVisible = false //больше нельзя нажать на кнопку да и нет
        val buttonno = findViewById<Button>(R.id.nobutton)
        buttonno.isVisible = false

    }

    fun noHelp(view: View) { //если пользователь сказал нет, то сразу выходим
        val helpReturn = Intent(this, TestingPart::class.java)
        helpReturn.putExtra("helpPoint", 0);//отправляем, что пользователь не получил подсказку
        setResult(RESULT_OK, helpReturn)
        finish();

    }

    fun exitHelp(view: View) { //кнопка если пользователь согласился на подсказку, получил ее и захотел выйти
        val helpReturn = Intent(this, TestingPart::class.java)
        helpReturn.putExtra("helpPoint", 1);//отправляем ответ, что он получил подсказку
        setResult(RESULT_OK, helpReturn)
        finish();
    }
}