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
    val errorMessage = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button0.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("0", binding.textScreen.text.toString())
        }

        binding.button1.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("1", binding.textScreen.text.toString())
        }

        binding.button2.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("2", binding.textScreen.text.toString())
        }

        binding.button3.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("3", binding.textScreen.text.toString())
        }

        binding.button4.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("4", binding.textScreen.text.toString())
        }

        binding.button5.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("5", binding.textScreen.text.toString())
        }

        binding.button6.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("6", binding.textScreen.text.toString())
        }

        binding.button7.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("7", binding.textScreen.text.toString())
        }

        binding.button8.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("8", binding.textScreen.text.toString())
        }

        binding.button9.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("9", binding.textScreen.text.toString())
        }

        binding.buttonPlus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
           binding.textScreen.text = calculator.addOperation("+", binding.textScreen.text.toString())
        }

        binding.buttonMinus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("-", binding.textScreen.text.toString())
        }

        binding.buttonMultiply.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("×", binding.textScreen.text.toString())
        }

        binding.buttonDevide.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("/", binding.textScreen.text.toString())
        }

        binding.buttonPercent.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setPercent(binding.textScreen.text.toString())
        }

        binding.buttonComma.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setComma(binding.textScreen.text.toString())
        }

        binding.buttonPlusMinus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setMinusOrPlus(binding.textScreen.text.toString())
        }

        binding.buttonAC.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteText()
        }

        binding.buttonDelete.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteLastSymbol(binding.textScreen.text.toString())
        }

        binding.buttonEquals.setOnClickListener{
            var test = calculator.clickEqualOrOperation("=", binding.textScreen.text.toString())
            if (test == errorMessage){
                val errorColor = ContextCompat.getColor(this, R.color.error_color)
                binding.textScreen.setTextColor(errorColor)
                binding.textScreen.text = test
            }
            else {
                val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
                binding.textScreen.setTextColor(baseColor)
                binding.textScreen.text = test
            }
        }
    }
}