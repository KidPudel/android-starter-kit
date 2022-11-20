package org.hyperskill.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // create a variable of a view data binding class
    // lateinit to ensure that instance is going to initialized
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // create an instance/object of the binding class with inflate https://github.com/KidPudel/android-starter-kit/blob/main/Android/inflate.md
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        // make the view active on the screen
        setContentView(view)

        // lazy instantiation of the ViewModel to actually create an instance on the first access
        val calculatorViewModel: CalculatorViewModel by viewModels()
        // Create a universal listener with the instructions for the listener to handle on the event
        // we need to call a logic on click
        val numericListener = View.OnClickListener { view -> calculatorViewModel.handleNumeric((view as Button).text.toString()) }
        val operationListener = View.OnClickListener { }

        // set listeners for the buttons
        setListener(binding.button1, numericListener)
        setListener(binding.button2, numericListener)
        setListener(binding.button3, numericListener)
        setListener(binding.button4, numericListener)
        setListener(binding.button5, numericListener)
        setListener(binding.button6, numericListener)
        setListener(binding.button7, numericListener)
        setListener(binding.button8, numericListener)
        setListener(binding.button9, numericListener)
        setListener(binding.addButton, numericListener)
        setListener(binding.multiplyButton, numericListener)
        setListener(binding.divideButton, numericListener)
        setListener(binding.equalButton, numericListener)
        setListener(binding.button0, View.OnClickListener { calculatorViewModel.handleZero(binding.button0.text.toString()) })
        setListener(binding.subtractButton, View.OnClickListener { calculatorViewModel.handleSubtraction(binding.subtractButton.text.toString()) })
        setListener(binding.dotButton, View.OnClickListener { calculatorViewModel.handleDot(binding.dotButton.text.toString())}) // setOnClickListener is for specific button, while ViewOnClickListener is universal and awaits when this listener will be used
        setListener(binding.clearButton, View.OnClickListener { calculatorViewModel.clear() })

        // we need to update hint and result values on the screen
        // subscribe on observation
        calculatorViewModel.getResult.observe(this) {
            // action on execution of the event
            binding.editText.setText(it)
        }

        calculatorViewModel.getHint.observe(this) {
            binding.editText.setHint(it)
        }
    }

    /**
     * method to set listener for the specified button
     * @param button the specified button to which to set a listener
     * @param listener set specified listener with the specific instructions
     */
    fun setListener(button: Button, listener: View.OnClickListener) =
        button.setOnClickListener(listener)
}
