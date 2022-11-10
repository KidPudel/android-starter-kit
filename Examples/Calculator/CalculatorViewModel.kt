package org.hyperskill.calculator

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    private var firstValue: Double = 0.0
    // this is for safety so we can change result and hint itself only in this inner class,
    // but outside we could only get that
    private val result = MutableLiveData<String>()
    val getResult: LiveData<String>
        get() = result
    // postValue is running on the background thread, changes are visible only when main thread is acti  ve
    private val hint = MutableLiveData<String>().apply { postValue("0") }
    val getHint: LiveData<String>
        get() = hint


    /**
     * method to handle numeric buttons from 1-9
     * @param buttonValue the name of the button
     */
    fun handleNumeric(buttonValue: String) {
        /* logic: if in result we have a zero then we need to assign with a new value
                  if we don't have a zero then add to an existing result
         */

        // elvis operator to protect from null result.value != null ? result.value : ""
        // ?: "" instead of null
        result.value = if ((result.value ?: "") == "0") buttonValue else (result.value ?: "") + buttonValue
    }

    // handle operations
    /**
     * method to handle operational buttons like +, -, =, etc
     * @param buttonValue the name of the button
     */
    fun handleOperational(buttonValue: String) {
        /* logic: We need to handle different operations so, we need to use conditions
                  When operational button is clicked handle operation
         */

        // protect second value from null
        val secondValue = if ((result.value ?: "0").toDouble() == 0.0) 0.0 else result.value.toString().toDouble()

        when (buttonValue) {
            "+" -> firstValue += secondValue
            "-" -> firstValue -= secondValue
            "*" -> firstValue *= secondValue
            "/" -> firstValue /= secondValue
            "=" -> {if ((result.value ?: "").isEmpty()) Unit else result.value = secondValue.toString()}
        }
    }

    // listener for zero
    fun handleZero(zero: String) {
        result.value = if ((result.value ?: "") == "0") zero else (result.value ?: "") + zero
    }


    // creates an instructions for "-" and call the function for handling operational buttons
    fun handleSubtraction(subtraction: String) {
        if ((result.value ?: "").isEmpty()) result.value = subtraction else handleOperational(subtraction)
    }

    fun handleDot(dot: String) {
        if ((result.value ?: "").toString().isEmpty()) result.value = dot else if ((result.value ?: "").contains('.')) (result.value ?: "") + "."
    }

    // method for clearing
    fun clear() {
        firstValue = 0.0
        result.value = ""
        hint.value = "0"
    }
}
