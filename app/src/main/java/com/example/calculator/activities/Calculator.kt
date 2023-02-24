package com.example.calculator.activities

import android.annotation.SuppressLint

class Calculator {
    private var lastOperation = false
    private var isNew = true
    private var isSetComma = false
    private var previousValue = ""
    private var typeOfOperation = ""


    fun addOperation(str: String, nowValue: String) : String{
        return if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
            clickEqualOrOperation(nowValue)
        } else {
            setOperation(str, nowValue)
        }
    }

    fun setText(str: String, nowValue: String) : String {
        if (isNew){
            isNew = false
            return str
        }
        var newText = nowValue + str
        lastOperation = false
        return newText
    }

    fun setComma(nowValue: String) : String{
        if (!isSetComma && nowValue.isNotEmpty()) {
            isSetComma = true
            isNew = false
            return "$nowValue."
        }
        return nowValue
    }


    fun setMinusOrPlus(nowValue: String) : String{
        if (nowValue.isNullOrEmpty()){
            return nowValue
        }
        else if (nowValue[0] == '-'){
            return nowValue.substring(1, nowValue.length)
        }
        else {
            return "-$nowValue"
        }
    }

    fun deleteText() : String{
        if (lastOperation){
            lastOperation = false
        }
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
        return "0"
    }

    fun setPercent(nowValue: String) : String {
        if (previousValue.isNotEmpty()) {
            return formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
        }
        return nowValue
    }

    fun deleteLastSymbol(nowValue: String): String {
        if (nowValue == "Error"){
            return deleteText()
        }

        else if (nowValue.isNotEmpty() && nowValue[nowValue.length - 1] == '.'){
            isSetComma = false
        }

        else if (nowValue.isNotEmpty()){
            if (lastOperation){
                lastOperation = false
                typeOfOperation = ""
            }
            return nowValue.substring(0, nowValue.length - 1)
        }
        return nowValue
    }

    private fun setError() : String{
        lastOperation = false
        typeOfOperation = ""
        previousValue = ""
        return "Error"
    }

    fun clickEqualOrOperation(nowValue: String) : String{
        var newText = ""

        if (nowValue[nowValue.length - 1] == '+'
            || nowValue[nowValue.length - 1] == '-'
            || nowValue[nowValue.length - 1] == '×'
            || nowValue[nowValue.length - 1] == '/'){
            return setError()
        }

        when(typeOfOperation){
            "+" -> newText = formatNumber(previousValue.toDouble() + nowValue.toDouble())
            "-" -> newText = formatNumber(previousValue.toDouble() - nowValue.toDouble())
            "×" -> newText = formatNumber(previousValue.toDouble() * nowValue.toDouble())
            "/" -> newText = formatNumber(previousValue.toDouble() / nowValue.toDouble())
        }

        previousValue = nowValue
        typeOfOperation = ""
        return newText
    }

    private fun setOperation(str: String, nowValue: String) : String {
        if (nowValue == "Error"){
            return deleteText()
        }

        else if (lastOperation){
            var newText = nowValue.substring(0, nowValue.length - 1)
            newText += str
            typeOfOperation = str
            return newText
        }

        else if (nowValue.isNotEmpty() && !lastOperation){
            previousValue = nowValue
            typeOfOperation = str
            isSetComma = false
            isNew = true
            lastOperation = true
            return nowValue + str
        }
        else {
            return nowValue
        }
    }

    private fun formatNumber(num: Double): String {
        if (num == Double.POSITIVE_INFINITY || num == Double.NEGATIVE_INFINITY){
            return "Error"
        }
        return if (num == num.toInt().toDouble()) {
            num.toInt().toString()
        } else {
            num.toString()
        }
    }
}