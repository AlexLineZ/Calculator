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

        binding.button0.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("0")
        }

        binding.button1.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("1")
        }

        binding.button2.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("2")
        }

        binding.button3.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("3")
        }

        binding.button4.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("4")
        }

        binding.button5.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("5")
        }

        binding.button6.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("6")
        }

        binding.button7.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("7")
        }

        binding.button8.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("8")
        }

        binding.button9.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setText("9")
        }

        binding.buttonPlus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("+")
        }

        binding.buttonMinus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("-")
        }

        binding.buttonMultiply.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("×")
        }

        binding.buttonDevide.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.addOperation("/")
        }

        binding.buttonPercent.setOnClickListener{
            var test = calculator.setPercent()
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

        binding.buttonComma.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setComma()
        }

        binding.buttonPlusMinus.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.setMinusOrPlus()
        }

        binding.buttonAC.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteText()
        }

        binding.buttonDelete.setOnClickListener{
            val baseColor = ContextCompat.getColor(this, R.color.text_screen_color)
            binding.textScreen.setTextColor(baseColor)
            binding.textScreen.text = calculator.deleteLastSymbol()
        }

        binding.buttonEquals.setOnClickListener{
            var test = calculator.clickEqualOrOperation("=")
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
