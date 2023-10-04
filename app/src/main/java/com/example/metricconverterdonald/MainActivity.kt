package com.example.metricconverterdonald

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var valueEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        valueEditText = findViewById(R.id.editTextValue)
        convertButton = findViewById(R.id.buttonConvert)
        resultTextView = findViewById(R.id.textViewResult)

        convertButton.setOnClickListener {
            convert()
        }

        valueEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Enable or disable the convert button based on input
                val input = s.toString().trim()
                convertButton.isEnabled = input.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }

    private fun convert() {
        val inputValue = valueEditText.text.toString().toDoubleOrNull()

        if (inputValue == null) {
            resultTextView.text = "Invalid input"
            return
        }

        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        val result = when (selectedRadioButtonId) {
            R.id.radioButtonMeterToKilometer -> {
                val kilometers = inputValue / 1000
                "$inputValue meters = $kilometers kilometers"
            }
            R.id.radioButtonKilometerToMeter -> {
                val meters = inputValue * 1000
                "$inputValue kilometers = $meters meters"
            }
            else -> "Invalid selection"
        }

        resultTextView.text = result
    }
}
