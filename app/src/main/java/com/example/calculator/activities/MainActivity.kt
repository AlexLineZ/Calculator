package com.example.calculator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var calculator = Calculator()
    private val errorMessage = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
        val errorColor = ContextCompat.getColor(this, R.color.error_color)

        calculator.isError.observe(this@MainActivity){
            if (it == true){
                binding.textScreen.setTextColor(errorColor)
            }
            else {
                binding.textScreen.setTextColor(baseColor)
            }
        }

        val numberButtons = mapOf (
            binding.button0 to NameButtons.ZERO,
            binding.button1 to NameButtons.ONE,
            binding.button2 to NameButtons.TWO,
            binding.button3 to NameButtons.THREE,
            binding.button4 to NameButtons.FOUR,
            binding.button5 to NameButtons.FIVE,
            binding.button6 to NameButtons.SIX,
            binding.button7 to NameButtons.SEVEN,
            binding.button8 to NameButtons.EIGHT,
            binding.button9 to NameButtons.NINE
        )

        val operationButtons = mapOf (
            binding.buttonPlus to NameButtons.PLUS,
            binding.buttonMinus to NameButtons.MINUS,
            binding.buttonMultiply to NameButtons.MULTIPLY,
            binding.buttonDivide to NameButtons.DIVIDE,
            binding.buttonPercent to NameButtons.PERCENT,
            binding.buttonComma to NameButtons.COMMA,
            binding.buttonAC to NameButtons.AC,
            binding.buttonPlusMinus to NameButtons.PLUSMINUS,
            binding.buttonDelete to NameButtons.DELETE,
            binding.buttonEquals to NameButtons.EQUAL
        )


        numberButtons.forEach { (button, index) ->
            button.setOnClickListener {
                binding.textScreen.text = calculator.setText(index)
            }
        }

        operationButtons.forEach { (button, operation) ->
            button.setOnClickListener {
                binding.textScreen.text = calculator.addOperation(operation)
            }
        }
    }
}


