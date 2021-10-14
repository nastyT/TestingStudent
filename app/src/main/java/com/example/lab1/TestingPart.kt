package com.example.lab1

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.R.attr.data


class TestingPart : AppCompatActivity() {
    var getHelpPoint: Int = 0
    private var countRight: Int = 0
    private var questionCounter: Int = 0
    private var results: Double = 0.0
    private var answer: Int = 0
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_part)
        randomExpr() //при запуске нужно создать первое выражение
        val button = findViewById<Button>(R.id.buttonHelp)  //кнопка подсказки
        button.setOnClickListener {
            val Helpintent = Intent(this, HelpActivity::class.java)
            Helpintent.putExtra(
                "Answer",
                answer
            ); //отправляем ответ для того, чтобы показать подсказку при запросе
            startActivityForResult(
                Helpintent,
                1
            ) //для того, чтобы получить потом ответ - получил ли подсказку или нет
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            getHelpPoint = data.getIntExtra(
                "helpPoint",
                0
            ) //согласился ли пользователь получить подсказку или нет
        };
    }


    fun randomExpr() { //создание случайного выражения
        textView = findViewById(R.id.textViewOperation)
        var operationString: String = ""
        var a: Int = (10..500).random() //число один
        var b: Int = (10..500).random() //число два
        val rndOperation = (0..3).random()
        when (rndOperation) { //4 варианта операций
            0 -> { //сумма
                operationString = "$a+$b"
                answer = a + b
            }
            1 -> { //разность
                a = (10..999).random()
                b = (10..999).random()
                if (a > b) { //проверка для того, чтобы не получилось отрицательного числа при вычислении
                    operationString = "$a-$b"
                    answer = a - b
                } else {
                    operationString = "$b-$a"
                    answer = b - a
                }
            }
            2 -> {//умножение
                a = (1..31).random() // чтобы не получилось четырехзначное число
                b = (1..31).random()
                operationString = "$a*$b"
                answer = a * b
            }
            3 -> {//деление
                a = (1..10).random()
                b = (1..100).random()
                var Divided: Int = a * b
                answer = b
                operationString = "$Divided/$a"
            }
        }
        textView.text = operationString
    }

    fun Check(view: View) { //проверка ответа
        var helpPoints = getHelpPoint
        var textForm = findViewById<TextInputEditText>(R.id.textInputEdit)
        var answerText = textForm.text.toString()
        var answerNumber = answerText.toInt()
        if (answerNumber == answer) { //если ответ совпал с решением
            countRight++
            if (helpPoints == 0) { //если не получал подсказки
                results++
            } else {
                results = results + 0.5 //если получил подсказку, то добавляется лишь половина
                getHelpPoint =
                    0 //обнуляем, чтобы ,если в следующих вопросах пользователь не нажимает кнопку подсказки, пользователь получал полное количество баллов
            }
        }
        textForm.text?.clear() //очищаем
        questionCounter++
        if (questionCounter > 4) { //если пройдено уже 5 вопросов, то переходим на результат
            val resultIntent = Intent(this, StatisticActivity::class.java)
            resultIntent.putExtra(
                StatisticActivity.TOTAL_RESULT,
                results
            ); //отправляем результат по баллам
            resultIntent.putExtra(
                StatisticActivity.TOTAL_RIGHT,
                countRight
            ); //результат по правильным ответам
            countRight = 0
            questionCounter = 0
            results = 0.0
            answer = 0
            startActivity(resultIntent)
        } else randomExpr() //если не пройдено 5 вопрсов, то заново
    }
}
