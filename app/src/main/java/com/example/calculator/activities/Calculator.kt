package com.example.calculator.activities

import android.annotation.SuppressLint
import kotlin.math.max

class Calculator {
    private var lastOperation = false
    private var isNew = true
    private var isSetComma = false
    private var previousValue = ""
    private var typeOfOperation = ""
    private val errorMessage = "Error"
    private val maxSymbols = 21
    private var nowValue = ""
    private val operators = listOf('+', '-', '×', '/')

    fun addOperation(str: String) : String{
        return if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
            clickEqualOrOperation(str)
        } else {
            setOperation(str)
        }
    }

    fun setText(str: String) : String {
        if (isNew){
            isNew = false
            lastOperation = false
            nowValue = str
            return nowValue
        }
        if (nowValue.length >= maxSymbols){
            lastOperation = false
            return nowValue
        }
        lastOperation = false
        nowValue += str
        return nowValue
    }

    fun setComma() : String{
        if (nowValue == errorMessage){
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


    fun setMinusOrPlus() : String{
        if (nowValue == errorMessage || isNew){
            return deleteText()
        }

        if (nowValue.isNullOrEmpty()){
            return nowValue
        }
        else if (nowValue[0] == '-'){
            nowValue = nowValue.substring(1, nowValue.length)
            return nowValue
        }
        else {
            nowValue = "-$nowValue"
            return nowValue
        }
    }

    fun deleteText() : String{
        nowValue = ""
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
        lastOperation = false
        return "0"
    }

    fun setPercent() : String {
        if (nowValue == errorMessage){
            return deleteText()
        }

        if (nowValue[nowValue.length - 1] in operators){
            return setError()
        }

        if (previousValue.isNotEmpty()) {
            nowValue = formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
            return nowValue
        }
        return nowValue
    }

    fun deleteLastSymbol(): String {
        if (nowValue == errorMessage){
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
            nowValue = nowValue.substring(0, nowValue.length - 1)
            return nowValue
        }
        return nowValue
    }


    fun clickEqualOrOperation(str: String) : String{
        var newText = ""

        if (str == "=" && (nowValue.isEmpty() || nowValue == errorMessage || isNew)){
            return deleteText()
        }

        if (str == "=" && typeOfOperation.isEmpty()){
            return nowValue
        }

        if (nowValue[nowValue.length - 1] in operators && str != "="){
            newText = nowValue.substring(0, nowValue.length - 1)
            newText += str
            typeOfOperation = str
            nowValue = newText
            return nowValue
        }

        else if (nowValue[nowValue.length - 1] in operators && str == "="){
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
            nowValue = newText
            nowValue
        } else if (newText == errorMessage){
            setError()
        } else {
            previousValue = newText
            isNew = true
            lastOperation = true
            typeOfOperation = str
            nowValue = newText + str
            nowValue
        }
    }

    private fun setOperation(str: String) : String {
        if (nowValue == errorMessage){
            return deleteText()
        }
        else if (lastOperation){
            var newText = nowValue.substring(0, nowValue.length - 1)
            newText += str
            typeOfOperation = str
            nowValue = newText
            return nowValue
        }
        else if (nowValue.isNotEmpty() && !lastOperation){
            previousValue = nowValue
            typeOfOperation = str
            isSetComma = false
            isNew = true
            lastOperation = true
            nowValue += str
            return nowValue
        }
        else {
            return nowValue
        }
    }

    private fun setError() : String {
        isNew = true
        isSetComma = false
        lastOperation = false
        nowValue = errorMessage
        typeOfOperation = ""
        previousValue = ""
        return errorMessage
    }

    private fun formatNumber(num: Double): String {
        if (num == Double.POSITIVE_INFINITY || num == Double.NEGATIVE_INFINITY || num.isNaN()){
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