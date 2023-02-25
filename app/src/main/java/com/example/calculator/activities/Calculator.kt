package com.example.calculator.activities

import android.annotation.SuppressLint

class Calculator {
    private var lastOperation = false
    private var isNew = true
    private var isSetComma = false
    private var previousValue = ""
    private var typeOfOperation = ""
    private val errorMessage = "Error"

    fun addOperation(str: String, nowValue: String) : String{
        return if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
            clickEqualOrOperation(str, nowValue)
        } else {
            setOperation(str, nowValue)
        }
    }

    fun setText(str: String, nowValue: String) : String {
        if (isNew){
            isNew = false
            lastOperation = false
            return str
        }
        lastOperation = false
        return nowValue + str
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
        return if (nowValue.isNullOrEmpty()){
            nowValue
        } else if (nowValue[0] == '-'){
            nowValue.substring(1, nowValue.length)
        } else {
            "-$nowValue"
        }
    }

    fun deleteText() : String{
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
        lastOperation = false
        return "0"
    }

    fun setPercent(nowValue: String) : String {
        if (nowValue[nowValue.length - 1] == '+'
            || nowValue[nowValue.length - 1] == '-'
            || nowValue[nowValue.length - 1] == '×'
            || nowValue[nowValue.length - 1] == '/'){
            return setError()
        }

        if (previousValue.isNotEmpty()) {
            return formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
        }
        return nowValue
    }

    fun deleteLastSymbol(nowValue: String): String {
        if (nowValue == "Error"){
            return deleteText()
        }

        if (nowValue.isNotEmpty() && nowValue[nowValue.length - 1] == '.'){
            isSetComma = false
        }

        if (nowValue.isNotEmpty()){
            if (lastOperation){
                lastOperation = false
                typeOfOperation = ""
            }
            return nowValue.substring(0, nowValue.length - 1)
        }
        return nowValue
    }


    fun clickEqualOrOperation(str: String, nowValue: String) : String{
        var newText = ""

        if (str == "=" && (nowValue.isEmpty() || nowValue == "Error" || isNew)){
            return deleteText()
        }

        if (str == "=" && typeOfOperation.isEmpty()){
            return nowValue
        }

        if ((nowValue[nowValue.length - 1] == '+'
            || nowValue[nowValue.length - 1] == '-'
            || nowValue[nowValue.length - 1] == '×'
            || nowValue[nowValue.length - 1] == '/')
            && str != "="){
            newText = nowValue.substring(0, nowValue.length - 1)
            newText += str
            typeOfOperation = str
            return newText
        }

        else if ((nowValue[nowValue.length - 1] == '+'
                 || nowValue[nowValue.length - 1] == '-'
                 || nowValue[nowValue.length - 1] == '×'
                 || nowValue[nowValue.length - 1] == '/')
                 && str == "="){
                return setError()
        }

        when(typeOfOperation){
            "+" -> newText = formatNumber(previousValue.toDouble() + nowValue.toDouble())
            "-" -> newText = formatNumber(previousValue.toDouble() - nowValue.toDouble())
            "×" -> newText = formatNumber(previousValue.toDouble() * nowValue.toDouble())
            "/" -> newText = formatNumber(previousValue.toDouble() / nowValue.toDouble())
        }

        if (!isInt(newText)){
            isSetComma = true
        }

        return if (str == "="){
            previousValue = nowValue
            typeOfOperation = ""
            newText
        } else {
            previousValue = newText
            isNew = true
            lastOperation = true
            typeOfOperation = str
            newText + str
        }
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

    private fun setError() : String{
        lastOperation = false
        typeOfOperation = ""
        previousValue = ""
        return errorMessage
    }

    private fun formatNumber(num: Double): String {
        if (num == Double.POSITIVE_INFINITY || num == Double.NEGATIVE_INFINITY){
            return setError()
        }

        return if (num == num.toInt().toDouble()) {
            num.toInt().toString()
        } else {
            num.toString()
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