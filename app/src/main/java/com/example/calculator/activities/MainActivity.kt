package com.example.calculator.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    var calculator = Calculator()
    private val errorMessage = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
        val errorColor = ContextCompat.getColor(this, R.color.error_color)

        val numberButtons = mapOf(binding.button0 to NameButtons.ZERO.symbol, binding.button1 to NameButtons.ONE.symbol, binding.button2 to NameButtons.TWO.symbol,
            binding.button3 to NameButtons.THREE.symbol, binding.button4 to NameButtons.FOUR.symbol, binding.button5 to NameButtons.FIVE.symbol,
            binding.button6 to NameButtons.SIX.symbol, binding.button7 to NameButtons.SEVEN.symbol, binding.button8 to NameButtons.EIGHT.symbol, binding.button9 to NameButtons.NINE.symbol)

        val operationOperationButtons = mapOf(binding.buttonPlus to NameButtons.PLUS.symbol, binding.buttonMinus to NameButtons.MINUS.symbol,
                binding.buttonMultiply to NameButtons.MULTIPLY.symbol, binding.buttonDivide to NameButtons.DIVIDE.symbol)

        val anotherButtons = mapOf(binding.buttonPercent to NameButtons.PERCENT.symbol, binding.buttonComma to NameButtons.COMMA.symbol,
            binding.buttonAC to NameButtons.AC.symbol, binding.buttonPlusMinus to NameButtons.PLUSMINUS.symbol,
            binding.buttonDelete to NameButtons.DELETE.symbol, binding.buttonEquals to NameButtons.EQUAL.symbol)


        numberButtons.forEach { (button, index) ->
            button.setOnClickListener {
                if (binding.textScreen.currentTextColor == errorColor){
                    binding.textScreen.setTextColor(baseColor)
                }
                binding.textScreen.text = calculator.setText(index)
            }
        }

        operationOperationButtons.forEach { (button, operation) ->
            button.setOnClickListener {
                val newValue = calculator.addOperation(operation)
                if (binding.textScreen.currentTextColor == errorColor){
                    binding.textScreen.setTextColor(baseColor)
                }
                else if (newValue == errorMessage){
                    binding.textScreen.setTextColor(errorColor)
                }
                binding.textScreen.text = newValue
            }
        }

        anotherButtons.forEach{ (button, operation) ->
            button.setOnClickListener {
                val newValue = calculator.addAnotherOperation(operation)
                if (binding.textScreen.currentTextColor == errorColor){
                    binding.textScreen.setTextColor(baseColor)
                }
                else if (newValue == errorMessage){
                    binding.textScreen.setTextColor(errorColor)
                }
                binding.textScreen.text = newValue
            }
        }
    }
}
