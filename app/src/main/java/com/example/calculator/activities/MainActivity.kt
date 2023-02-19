package com.example.calculator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

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
        binding.buttonPlus.setOnClickListener{setText("+")}
        binding.buttonMinus.setOnClickListener{setText("-")}
        binding.buttonMultiply.setOnClickListener{setText("×")}
        binding.buttonDevide.setOnClickListener{setText("/")}
        binding.buttonAC.setOnClickListener{deleteText()}
        binding.buttonDelete.setOnClickListener { deleteLastSymbol() }
    }

    fun setText(str: String){
        if ((str == "+" || str == "-" || str == "×" || str == "/") && !binding.textScreen.text.toString().isNullOrEmpty()){
            if (binding.textScreen.text.toString()[binding.textScreen.text.length - 1] == '+'
                || binding.textScreen.text.toString()[binding.textScreen.text.length - 1] == '-'
                || binding.textScreen.text.toString()[binding.textScreen.text.length - 1] == '×'
                || binding.textScreen.text.toString()[binding.textScreen.text.length - 1] == '/'){

                val newText = binding.textScreen.text.toString()
                binding.textScreen.text = newText.substring(0, newText.length - 1)
                binding.textScreen.append(str)

            }
            else {
                binding.textScreen.append(str)
            }
        }

        else if (str != "+" && str != "-" && str != "×" && str != "/") {
            binding.textScreen.append(str)
        }
    }

    fun deleteText(){
        binding.textScreen.text = ""
    }

    fun deleteLastSymbol(){
        var newText = binding.textScreen.text.toString()
        if (!newText.isNullOrEmpty()){
            binding.textScreen.text = newText.substring(0, newText.length - 1)
        }
    }
}