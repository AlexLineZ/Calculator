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
    var calculator = Calculator()
    private val errorMessage = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
        val errorColor = ContextCompat.getColor(this, R.color.error_color)

        val numberButtons = listOf(binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)

        val operationButtons = mapOf(binding.buttonPlus to "+", binding.buttonMinus to "-",
            binding.buttonMultiply to "×", binding.buttonDevide to "/")

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                binding.textScreen.setTextColor(baseColor)
                binding.textScreen.text = calculator.setText(index.toString())
            }
        }

        operationButtons.forEach { (button, operation) ->
            button.setOnClickListener {
                binding.textScreen.setTextColor(baseColor)
                binding.textScreen.text = calculator.addOperation(operation)
            }
        }

        binding.buttonPercent.setOnClickListener{
            var test = calculator.setPercent()
            if (test == errorMessage){
                binding.textScreen.setTextColor(errorColor)
                binding.textScreen.text = test
            }
            else {
                binding.textScreen.setTextColor(baseColor)
                binding.textScreen.text = test
            }
        }

        binding.buttonComma.setOnClickListener{
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setComma()
        }

        binding.buttonPlusMinus.setOnClickListener{
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setMinusOrPlus()
        }

        binding.buttonAC.setOnClickListener{
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteText()
        }

        binding.buttonDelete.setOnClickListener{
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteLastSymbol()
        }

        binding.buttonEquals.setOnClickListener{
            var test = calculator.clickEqualOrOperation("=")
            if (test == errorMessage){
                binding.textScreen.setTextColor(errorColor)
                binding.textScreen.text = test
            }
            else {
                binding.textScreen.setTextColor(baseColor)
                binding.textScreen.text = test
            }
        }
    }
}
