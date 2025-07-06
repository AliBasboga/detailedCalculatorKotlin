package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private var firstNumber: Double? = null
    private var currentOperation: Operation? = null

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun setOperation(operation: Operation, number: Double) {
        if (firstNumber == null) {
            firstNumber = number
        }
        currentOperation = operation
    }

    fun calculateResult(secondNumber: Double) {
        if (firstNumber != null && currentOperation != null) {
            when (currentOperation) {
                Operation.ADD -> _result.value = "Sonuç: ${firstNumber!! + secondNumber}"
                Operation.SUBTRACT -> _result.value = "Sonuç: ${firstNumber!! - secondNumber}"
                Operation.MULTIPLY -> _result.value = "Sonuç: ${firstNumber!! * secondNumber}"
                Operation.DIVIDE -> {
                    if (secondNumber != 0.0) {
                        _result.value = "Sonuç: ${firstNumber!! / secondNumber}"
                    } else {
                        _result.value = "Sıfıra Bölünemez"
                    }
                }
                else -> _result.value = "Geçersiz İşlem"
            }
            firstNumber = null
            currentOperation = null
        }
    }

    fun clear() {
        firstNumber = null
        currentOperation = null
        _result.value = "0"
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}