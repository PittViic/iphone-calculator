package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf("0")
        private set

    private var number1: String = ""
    private var number2: String = ""
    private var operation: String = ""
    
    private var calculationPerformed = false

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operator -> enterOperator(action.symbol)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Decimal -> enterDecimal()
        }
    }

    private fun enterNumber(number: String) {
        if (calculationPerformed) {
            clear()
        }

        if (operation.isNotEmpty()) {
            if (number2.length >= 8) return
            number2 += number
            state = number2
        } else {
            if (number1.length >= 8) return
            if (number1.isEmpty() || number1 == "0") {
                number1 = number
            } else {
                number1 += number
            }
            state = number1
        }

        calculationPerformed = false
    }

    private fun enterOperator(op: String) {

        if (number1.isNotEmpty()) {
            operation = op
            state = number1
        }

        calculationPerformed = false
    }

    private fun enterDecimal() {
        // Comporta-se de forma similar a 'enterNumber'.
        if (calculationPerformed) {
            clear()
        }

        if (operation.isEmpty()) {
            if (number1.isEmpty()) number1 = "0"
            if (!number1.contains(".")) {
                number1 += "."
                state = number1
            }
        } else {
            if (number2.isEmpty()) number2 = "0"
            if (!number2.contains(".")) {
                number2 += "."
                state = number2
            }
        }
        calculationPerformed = false
    }

    private fun performCalculation() {
        if (number1.isEmpty() || number2.isEmpty() || operation.isEmpty()) {
            return
        }

        val n1 = number1.toDoubleOrNull() ?: 0.0
        val n2 = number2.toDoubleOrNull() ?: 0.0

        val result: Any = when (operation) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else "Erro"
            else -> "Erro"
        }

        if (result is String) {
            state = result
            number1 = ""
            number2 = ""
            operation = ""
            calculationPerformed = true
            return
        }

        val resultNumber = result as Double
        val resultString = if (resultNumber.toString().endsWith(".0")) {
            resultNumber.toLong().toString()
        } else {
            resultNumber.toString().take(10)
        }

        state = resultString
        number1 = resultString
        number2 = ""
        operation = ""

        calculationPerformed = true
    }

    private fun clear() {
        state = "0"
        number1 = ""
        number2 = ""
        operation = ""
        calculationPerformed = false
    }
}

sealed class CalculatorAction {
    data class Number(val number: String) : CalculatorAction()
    data class Operator(val symbol: String) : CalculatorAction()
    object Calculate : CalculatorAction()
    object Clear : CalculatorAction()
    object Decimal : CalculatorAction()
}