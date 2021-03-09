package com.sanxynet.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.sanxynet.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /* Global variables */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /* Text changed listener for tip percentage */
        binding.tipPercentageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty() && binding.billEditText.text!!.isNotEmpty()) {
                    // Max tip % cannot be greater than 100
                    if (s.toString().toInt() > 100) {
                        binding.tipPercentageEditText.setText(getString(R.string.maximum_percent))
                    }
                    calculateTip()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

        /* Text changed listener for bill amount */
        binding.billEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty() && binding.tipPercentageEditText.text!!.isNotEmpty()) {
                    calculateTip()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    private fun calculateTip() {
        /* Get the values of both edit text from Text Watcher */
        val billAmountValue = binding.billEditText.text.toString().toDouble()
        val tipPercentValue = binding.tipPercentageEditText.text.toString().toDouble()

        val tipAmountValue = (tipPercentValue / 100) * billAmountValue
        val finalAmount = billAmountValue + tipAmountValue

        /* set the text values for calculate tip amount and total amount */
        binding.tipAmountFinalResult.text = String.format("%.2f", tipAmountValue)
        binding.totalAmountFinalResult.text = String.format("%.2f", finalAmount)
    }
}