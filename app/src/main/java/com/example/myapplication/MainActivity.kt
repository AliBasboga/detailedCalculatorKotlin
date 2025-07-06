package com.example.myapplication


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculatorViewModel.result.observe(this) { result ->
            binding.displayScreen.text = result
        }


        setNumberButtonListeners()

        setOperationButtonListeners()
    }

    private fun setNumberButtonListeners() {
        binding.btn0.setOnClickListener { onNumberClick(0) }
        binding.btn1.setOnClickListener { onNumberClick(1) }
        binding.btn2.setOnClickListener { onNumberClick(2) }
        binding.btn3.setOnClickListener { onNumberClick(3) }
        binding.btn4.setOnClickListener { onNumberClick(4) }
        binding.btn5.setOnClickListener { onNumberClick(5) }
        binding.btn6.setOnClickListener { onNumberClick(6) }
        binding.btn7.setOnClickListener { onNumberClick(7) }
        binding.btn8.setOnClickListener { onNumberClick(8) }
        binding.btn9.setOnClickListener { onNumberClick(9) }
    }

    private fun setOperationButtonListeners() {
        binding.btnPlus.setOnClickListener { onOperationClick(CalculatorViewModel.Operation.ADD) }
        binding.btnMinus.setOnClickListener { onOperationClick(CalculatorViewModel.Operation.SUBTRACT) }
        binding.btnMultiply.setOnClickListener { onOperationClick(CalculatorViewModel.Operation.MULTIPLY) }
        binding.btnDivide.setOnClickListener { onOperationClick(CalculatorViewModel.Operation.DIVIDE) }
        binding.btnClear.setOnClickListener { onClearClick() }
        binding.btnEquals.setOnClickListener { onEqualsClick() }
    }

    private fun onNumberClick(number: Int) {
        val currentText = binding.displayScreen.text.toString()
        if (currentText == "0") {
            binding.displayScreen.text = number.toString()
        } else {
            binding.displayScreen.append(number.toString())
        }
    }

    private fun onOperationClick(operation: CalculatorViewModel.Operation) {
        val currentText = binding.displayScreen.text.toString()
        val number = currentText.toDoubleOrNull()
        if (number != null) {
            calculatorViewModel.setOperation(operation, number)
            binding.displayScreen.text = "0"
        }
    }

    private fun onEqualsClick() {
        val currentText = binding.displayScreen.text.toString()
        val number = currentText.toDoubleOrNull()
        if (number != null) {
            calculatorViewModel.calculateResult(number)
        }
    }

    private fun onClearClick() {
        binding.displayScreen.text = "0"
        calculatorViewModel.clear()
    }
}