package com.example.calculator.activities


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Calculator {
    private var isNew = true
    private var isSetComma = false
    private var isLastOperation = false
    private var nowValue = ""
    private var previousValue = ""
    private var typeOfOperation = ""
    private val maxSymbols = 21
    private val errorMessage = "Error"
    private val operators = listOf('+', '-', '×', '/')

    private val _isError = MutableLiveData<Boolean>(false)
    val isError: LiveData<Boolean> = _isError

    fun addOperation(operation: NameButtons): String {
        return when (operation) {
            NameButtons.PERCENT -> {
                setPercent()
            }
            NameButtons.COMMA -> {
                setComma()
            }
            NameButtons.AC -> {
                deleteText()
            }
            NameButtons.PLUSMINUS -> {
                setMinusOrPlus()
            }
            NameButtons.DELETE -> {
                deleteLastSymbol()
            }
            NameButtons.EQUAL -> {
                clickEqualOrOperation(operation)
            }
            else -> {
                if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()) {
                    clickEqualOrOperation(operation)
                } else {
                    setOperation(operation)
                }
            }
        }
    }

    fun setText(number: NameButtons): String {
        _isError.value = false
        if (isNew) {
            isNew = false
            isLastOperation = false
            nowValue = number.symbol
            return nowValue
        }
        if (nowValue.length >= maxSymbols) {
            isLastOperation = false
            return nowValue
        }
        isLastOperation = false
        nowValue += number.symbol
        return nowValue
    }

    private fun setComma(): String {
        if (nowValue == errorMessage) {
            return deleteText()
        }
        if (!isSetComma && nowValue.isNotEmpty()) {
            isSetComma = true
            isNew = false
            nowValue = "$nowValue."
            return nowValue
        }
        return nowValue
    }

    private fun setMinusOrPlus(): String {
        if (nowValue == errorMessage || isNew) {
            return deleteText()
        }

        return if (nowValue.isEmpty()) {
            nowValue
        } else if (nowValue[0] == '-') {
            nowValue = nowValue.substring(1, nowValue.length)
            nowValue
        } else {
            nowValue = "-$nowValue"
            nowValue
        }
    }

    private fun deleteText(): String {
        nowValue = ""
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
        isLastOperation = false
        _isError.value = false
        return "0"
    }

    private fun setPercent(): String {
        if (nowValue == errorMessage) {
            return deleteText()
        } else if (nowValue[nowValue.length - 1] in operators) {
            return setError()
        } else if (previousValue.isNotEmpty()) {
            nowValue = formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
            return nowValue
        } else {
            return nowValue
        }
    }

    private fun deleteLastSymbol(): String {
        if (nowValue == errorMessage) {
            return deleteText()
        }

        if (nowValue.isNotEmpty() && nowValue[nowValue.length - 1] == '.') {
            isSetComma = false
        }

        if (nowValue.isNotEmpty()) {
            if (isLastOperation) {
                isLastOperation = false
                typeOfOperation = ""
            }
            nowValue = nowValue.substring(0, nowValue.length - 1)
            return nowValue
        }
        return nowValue
    }

    //я не стал сокращать эту функцию, потому что объединить эти случаи в один было очень удобно
    //но сделал ее чуть более читабельной
    private fun clickEqualOrOperation(operation: NameButtons): String {
        if (operation == NameButtons.EQUAL) {
            if (nowValue.isEmpty() || nowValue == errorMessage || isNew) {
                return deleteText()
            }
            if (typeOfOperation.isEmpty()) {
                return nowValue
            }
            if (nowValue[nowValue.length - 1] in operators) {
                return setError()
            }
        } else if (nowValue[nowValue.length - 1] in operators) {
            nowValue = nowValue.substring(0, nowValue.length - 1)
            nowValue += operation
            typeOfOperation = operation.symbol
            return nowValue
        }

        var newText = ""
        when (typeOfOperation) {
            "+" -> newText = formatNumber(previousValue.toDouble() + nowValue.toDouble())
            "-" -> newText = formatNumber(previousValue.toDouble() - nowValue.toDouble())
            "×" -> newText = formatNumber(previousValue.toDouble() * nowValue.toDouble())
            "/" -> newText = formatNumber(previousValue.toDouble() / nowValue.toDouble())
        }

        if (!isInt(newText)) {
            isSetComma = true
        }

        return if (operation == NameButtons.EQUAL) {
            previousValue = nowValue
            typeOfOperation = ""
            nowValue = newText

            nowValue
        } else {
            previousValue = newText
            isNew = true
            isLastOperation = true
            typeOfOperation = operation.symbol
            nowValue = newText + operation.symbol

            nowValue
        }
    }

    private fun setOperation(operation: NameButtons): String {
        if (nowValue == errorMessage) {
            return deleteText()
        } else if (isLastOperation) {
            var newText = nowValue.substring(0, nowValue.length - 1)
            newText += operation
            typeOfOperation = operation.symbol
            nowValue = newText
            return nowValue
        } else if (nowValue.isNotEmpty() && !isLastOperation) {
            previousValue = nowValue
            typeOfOperation = operation.symbol
            isSetComma = false
            isNew = true
            isLastOperation = true
            nowValue += operation.symbol
            return nowValue
        } else {
            return nowValue
        }
    }

    private fun setError(): String {
        isNew = true
        isSetComma = false
        isLastOperation = false
        nowValue = errorMessage
        typeOfOperation = ""
        previousValue = ""

        _isError.value = true
        return errorMessage
    }

    private fun formatNumber(value: Double): String {
        if (value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY || value.isNaN()) {
            return setError()
        }

        return if (value == value.toInt().toDouble()) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun isInt(value: String): Boolean {
        return try {
            value.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

}