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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button0.setOnClickListener{
            binding.textScreen.text = calculator.setText("0", binding.textScreen.text.toString())
        }

        binding.button1.setOnClickListener{
            binding.textScreen.text = calculator.setText("1", binding.textScreen.text.toString())
        }

        binding.button2.setOnClickListener{
            binding.textScreen.text = calculator.setText("2", binding.textScreen.text.toString())
        }

        binding.button3.setOnClickListener{
            binding.textScreen.text = calculator.setText("3", binding.textScreen.text.toString())
        }

        binding.button4.setOnClickListener{
            binding.textScreen.text = calculator.setText("4", binding.textScreen.text.toString())
        }

        binding.button5.setOnClickListener{
            binding.textScreen.text = calculator.setText("5", binding.textScreen.text.toString())
        }

        binding.button6.setOnClickListener{
            binding.textScreen.text = calculator.setText("6", binding.textScreen.text.toString())
        }

        binding.button7.setOnClickListener{
            binding.textScreen.text = calculator.setText("7", binding.textScreen.text.toString())
        }

        binding.button8.setOnClickListener{
            binding.textScreen.text = calculator.setText("8", binding.textScreen.text.toString())
        }

        binding.button9.setOnClickListener{
            binding.textScreen.text = calculator.setText("9", binding.textScreen.text.toString())
        }

        binding.buttonPlus.setOnClickListener{
           binding.textScreen.text = calculator.addOperation("+", binding.textScreen.text.toString())
        }

        binding.buttonMinus.setOnClickListener{
            binding.textScreen.text = calculator.addOperation("-", binding.textScreen.text.toString())
        }

        binding.buttonMultiply.setOnClickListener{
            binding.textScreen.text = calculator.addOperation("×", binding.textScreen.text.toString())
        }

        binding.buttonDevide.setOnClickListener{
            binding.textScreen.text = calculator.addOperation("/", binding.textScreen.text.toString())
        }

        binding.buttonPercent.setOnClickListener{
            binding.textScreen.text = calculator.setPercent(binding.textScreen.text.toString())
        }

        binding.buttonComma.setOnClickListener{
            binding.textScreen.text = calculator.setComma(binding.textScreen.text.toString())
        }

        binding.buttonPlusMinus.setOnClickListener{
            binding.textScreen.text = calculator.setMinusOrPlus(binding.textScreen.text.toString())
        }

        binding.buttonAC.setOnClickListener{
            binding.textScreen.text = calculator.deleteText()
        }

        binding.buttonDelete.setOnClickListener{
            binding.textScreen.text = calculator.deleteLastSymbol(binding.textScreen.text.toString())
        }

        binding.buttonEquals.setOnClickListener{
            binding.textScreen.text = calculator.clickEqualOrOperation(binding.textScreen.text.toString())
        }

    }
}