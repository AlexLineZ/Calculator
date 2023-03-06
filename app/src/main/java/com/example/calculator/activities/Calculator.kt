package com.example.calculator.activities

import android.annotation.SuppressLint
import java.util.jar.Attributes.Name
import kotlin.math.max

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

    fun addOperation(operation: String) : String{
        return if (previousValue.isNotEmpty() && typeOfOperation.isNotEmpty()){
            clickEqualOrOperation(operation)
        } else {
            setOperation(operation)
        }
    }

    fun addAnotherOperation(operation: String) : String {
        when (operation) {
            NameButtons.PERCENT.symbol -> {
                return setPercent()
            }
            NameButtons.COMMA.symbol -> {
                return setComma()
            }
            NameButtons.AC.symbol -> {
                return deleteText()
            }
            NameButtons.PLUSMINUS.symbol -> {
                return setMinusOrPlus()
            }
            NameButtons.DELETE.symbol -> {
                return deleteLastSymbol()
            }
            NameButtons.EQUAL.symbol -> {
                return clickEqualOrOperation(operation)
            }
        }
        return deleteText()
    }

    fun setText(symbol: String) : String {
        if (isNew){
            isNew = false
            isLastOperation = false
            nowValue = symbol
            return nowValue
        }
        if (nowValue.length >= maxSymbols){
            isLastOperation = false
            return nowValue
        }
        isLastOperation = false
        nowValue += symbol
        return nowValue
    }

    private fun setComma() : String{
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

    private fun setMinusOrPlus() : String{
        if (nowValue == errorMessage || isNew){
            return deleteText()
        }

        return if (nowValue.isEmpty()){
            nowValue
        } else if (nowValue[0] == '-'){
            nowValue = nowValue.substring(1, nowValue.length)
            nowValue
        } else {
            nowValue = "-$nowValue"
            nowValue
        }
    }

    private fun deleteText() : String{
        nowValue = ""
        previousValue = ""
        typeOfOperation = ""
        isSetComma = false
        isNew = true
        isLastOperation = false
        return "0"
    }

    private fun setPercent() : String {
        return if (nowValue == errorMessage){
            deleteText()
        } else if (nowValue[nowValue.length - 1] in operators){
            setError()
        } else if (previousValue.isNotEmpty()) {
            nowValue = formatNumber(previousValue.toDouble() * nowValue.toDouble() / 100)
            nowValue
        } else {
            nowValue
        }
    }

    private fun deleteLastSymbol(): String {
        if (nowValue == errorMessage){
            return deleteText()
        }

        if (nowValue.isNotEmpty() && nowValue[nowValue.length - 1] == '.'){
            isSetComma = false
        }

        if (nowValue.isNotEmpty()){
            if (isLastOperation){
                isLastOperation = false
                typeOfOperation = ""
            }
            nowValue = nowValue.substring(0, nowValue.length - 1)
            return nowValue
        }
        return nowValue
    }


    private fun clickEqualOrOperation(operation: String) : String{
        var newText = ""

        if (operation == "=" && (nowValue.isEmpty() || nowValue == errorMessage || isNew)){
            return deleteText()
        }

        if (operation == "=" && typeOfOperation.isEmpty()){
            return nowValue
        }

        if (nowValue[nowValue.length - 1] in operators && operation != "="){
            newText = nowValue.substring(0, nowValue.length - 1)
            newText += operation
            typeOfOperation = operation
            nowValue = newText
            return nowValue
        }

        else if (nowValue[nowValue.length - 1] in operators && operation == "="){
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

        return if (operation == "="){
            previousValue = nowValue
            typeOfOperation = ""
            nowValue = newText
            nowValue
        } else if (newText == errorMessage){
            setError()
        } else {
            previousValue = newText
            isNew = true
            isLastOperation = true
            typeOfOperation = operation
            nowValue = newText + operation
            nowValue
        }
    }

    private fun setOperation(operation: String) : String {
        if (nowValue == errorMessage){
            return deleteText()
        }
        else if (isLastOperation){
            var newText = nowValue.substring(0, nowValue.length - 1)
            newText += operation
            typeOfOperation = operation
            nowValue = newText
            return nowValue
        }
        else if (nowValue.isNotEmpty() && !isLastOperation){
            previousValue = nowValue
            typeOfOperation = operation
            isSetComma = false
            isNew = true
            isLastOperation = true
            nowValue += operation
            return nowValue
        }
        else {
            return nowValue
        }
    }

    private fun setError() : String {
        isNew = true
        isSetComma = false
        isLastOperation = false
        nowValue = errorMessage
        typeOfOperation = ""
        previousValue = ""
        return errorMessage
    }

    private fun formatNumber(value: Double): String {
        if (value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY || value.isNaN()){
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