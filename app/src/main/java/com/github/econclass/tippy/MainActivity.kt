package com.github.econclass.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val INIT_TIP = 15

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbTipPercentage.progress = INIT_TIP
        tvTipPercentage.text = "$INIT_TIP%"
        sbTipPercentage.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvTipPercentage.text = "$progress"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })


        etPrice.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                computeTipAndTotal()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun computeTipAndTotal() {
        if (etPrice.text.toString().isEmpty()) {
            tvNumSubTotal.text = ""
            tvNumTipTotal.text = ""
            return
        }

        val price = etPrice.text.toString().toDouble()
        val tipPercent = sbTipPercentage.progress

        val tipAmount = price * tipPercent / 100
        val subTotal = price + tipAmount

        tvNumTipTotal.text = "%.2f".format(tipAmount)
        tvNumSubTotal.text = "%.2f".format(subTotal)
    }
}
