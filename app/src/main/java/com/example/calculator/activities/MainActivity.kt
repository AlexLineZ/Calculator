package com.example.calculator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    var lastOperation = false
    var isNew = true
    var previousValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textResult.text = previousValue

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
        binding.buttonPlus.setOnClickListener{setOperation("+")}
        binding.buttonMinus.setOnClickListener{setOperation("-")}
        binding.buttonMultiply.setOnClickListener{setOperation("×")}
        binding.buttonDevide.setOnClickListener{setOperation("/")}
        binding.buttonPlusMinus.setOnClickListener{setMinusOrPlus()}
        binding.buttonAC.setOnClickListener{deleteText()}
        binding.buttonDelete.setOnClickListener { deleteLastSymbol() }
    }

    private fun setOperation(str: String){
        println(lastOperation)
        if (lastOperation){
            val newText = binding.textScreen.text.toString()
            binding.textScreen.text = newText.substring(0, newText.length - 1)
            binding.textScreen.append(str)
        }

        if (!binding.textScreen.text.toString().isNullOrEmpty() && !lastOperation){
            binding.textScreen.append(str)
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
        binding.textScreen.text = "0"
        isNew = true
    }

    private fun deleteLastSymbol() {
        var newText = binding.textScreen.text.toString()
        if (!newText.isNullOrEmpty()){
            binding.textScreen.text = newText.substring(0, newText.length - 1)
            if (lastOperation){
                lastOperation = false
            }
        }
    }
}