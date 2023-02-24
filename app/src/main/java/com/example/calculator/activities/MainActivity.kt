package com.example.calculator.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    var lastOperation = false
    var isNew = true
    var isSetComma = false
    var previousValue = ""
    var typeOfOperation = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.button0.setOnClickListener{setText("0")}
        binding.button1.setOnClickListener{setText("1")}
        binding.button2.setOnClickListener{setText("2")}
        binding.button3.setOnClickListener{setText("3")}
        binding.button4.setOnClickListener{setText("4")}
        binding.button5.setOnClickListener{setText("5")}
        binding.button6.setOnClickListener{setText("6")}
        binding.button7.setOnClickListener{setText("7")}
        binding.button8.setOnClickListener{setText("8")}
        binding.button9.setOnClickListener{setText("9")}

        binding.buttonPlus.setOnClickListener{
            if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
                clickEqualOrOperation()
            }
            else {
                setOperation("+")
            }
        }
        binding.buttonMinus.setOnClickListener{
            if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
                clickEqualOrOperation()
            }
            else {
                setOperation("-")
            }
        }
        binding.buttonMultiply.setOnClickListener{
            if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
                clickEqualOrOperation()
            }
            else {
                setOperation("×")
            }
        }
        binding.buttonDevide.setOnClickListener{
            if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
                clickEqualOrOperation()
            }
            else {
                setOperation("/")
            }
        }

        binding.buttonPercent.setOnClickListener{setPercent()}
        binding.buttonComma.setOnClickListener{setComma()}
        binding.buttonPlusMinus.setOnClickListener{setMinusOrPlus()}
        binding.buttonAC.setOnClickListener{deleteText()}
        binding.buttonDelete.setOnClickListener{deleteLastSymbol()}
        binding.buttonEquals.setOnClickListener{clickEqualOrOperation()}
    }

    private fun setOperation(str: String){

        if (binding.textScreen.text == "Error"){
            deleteText()
        }

        if (lastOperation){
            val newText = binding.textScreen.text.toString()
            binding.textScreen.text = newText.substring(0, newText.length - 1)
            binding.textScreen.append(str)
            typeOfOperation = str
        }

        if (binding.textScreen.text.toString().isNotEmpty() && !lastOperation){
            previousValue = binding.textScreen.text.toString()
            binding.textScreen.append(str)
            typeOfOperation = str
            isSetComma = false
            isNew = true
            lastOperation = true
        }
    }

    private fun setText(str: String) {
        if (isNew){
            binding.textScreen.text = ""
            isNew = false
        }
        binding.textScreen.append(str)
        lastOperation = false
    }

    private fun setComma(){
        if (!isSetComma && binding.textScreen.text.isNotEmpty()) {
            isSetComma = true
            isNew = false
            binding.textScreen.append(".")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMinusOrPlus() {
        if (binding.textScreen.text.isNullOrEmpty()){
            return
        }
        else if (binding.textScreen.text[0] == '-'){
            binding.textScreen.text = binding.textScreen.text.substring(1, binding.textScreen.text.length)
        }
        else {
            binding.textScreen.text = "-" + binding.textScreen.text
        }
    }

    private fun deleteText() {
        if (lastOperation){
            lastOperation = false
        }
        val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
        binding.textScreen.setTextColor(baseColor)
        binding.textScreen.text = "0"
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
    }

    private fun deleteLastSymbol() {
        val newText = binding.textScreen.text.toString()
        if (newText == "Error"){
            deleteText()
        }

        else if (newText.isNotEmpty() && newText[newText.length - 1] == '.'){
            isSetComma = false
        }

        else if (newText.isNotEmpty()){
            binding.textScreen.text = newText.substring(0, newText.length - 1)
            if (lastOperation){
                lastOperation = false
                typeOfOperation = ""
            }
        }
    }

    fun setError(){
        val errorColor = ContextCompat.getColor(this, R.color.error_color)
        binding.textScreen.setTextColor(errorColor)
        binding.textScreen.text = "Error"
        lastOperation = false
        typeOfOperation = ""
        previousValue = ""
    }

    @SuppressLint("SetTextI18n")
    fun clickEqualOrOperation() {
        var nowValue = binding.textScreen.text.toString()

        if (nowValue[nowValue.length - 1] == '+'
            || nowValue[nowValue.length - 1] == '-'
            || nowValue[nowValue.length - 1] == '×'
            || nowValue[nowValue.length - 1] == '/'){
            setError()
        }

        when(typeOfOperation){
            "+" -> binding.textScreen.text = formatNumber(previousValue.toDouble() + nowValue.toDouble())
            "-" -> binding.textScreen.text = formatNumber(previousValue.toDouble() - nowValue.toDouble())
            "×" -> binding.textScreen.text = formatNumber(previousValue.toDouble() * nowValue.toDouble())
            "/" -> binding.textScreen.text = formatNumber(previousValue.toDouble() / nowValue.toDouble())
        }

        previousValue = nowValue
        typeOfOperation = ""
    }

    fun formatNumber(num: Double): String {
        if (num == Double.POSITIVE_INFINITY || num == Double.NEGATIVE_INFINITY){
            val errorColor = ContextCompat.getColor(this, R.color.error_color)
            binding.textScreen.setTextColor(errorColor)
            return "Error"
        }
        return if (num == num.toInt().toDouble()) {
            num.toInt().toString()
        } else {
            num.toString()
        }
    }

    fun setPercent(){
        if (previousValue.isNotEmpty()){
            val nowValue = binding.textScreen.text.toString()
            binding.textScreen.text = formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
        }
    }
}