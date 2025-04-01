package com.example.imadclass

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)
        val button3: Button = findViewById(R.id.button3)
        val editText: EditText = findViewById(R.id.editText)
        val button5: Button = findViewById(R.id.button5)
        val timeText: EditText = findViewById(R.id.editText2)

        //add the action listener to the first button
        button.setOnClickListener {
            //button.text = "I have been clicked"
            getString(R.string.button_clicked)
            button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))

            val userName = editText.text.toString()
            val stringMessage = getString(R.string.button_message)

            val message = if (userName.isNotEmpty()) {
                "Hello, $userName! $stringMessage"
            } else {
                stringMessage
            }

            //textView.text = "Button was clicked!"
            textView.text = message
            textView.setTextColor(Color.RED)
            textView.setTypeface(null, Typeface.BOLD)
            textView.textSize = 24f
        }
        //add action listener to the second button
        button3.setOnClickListener {
            finish()
        }

        button5.setOnClickListener{
            val inputTime = timeText.text.toString()
            if (inputTime.isNotEmpty()) {
                val format = SimpleDateFormat("hh:mm", Locale.getDefault())

                try {
                    val date = format.parse(inputTime)
                    val calendar = Calendar.getInstance()
                    if (date != null) {
                        calendar.time = date
                    }
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    when (hour) {
                        in 6..10 -> {
                            button5.text = getString(R.string.morning_time)
                        }
                        in 11..15 -> {
                            button5.text = getString(R.string.mid_afternoon_time)
                        }
                        in 16..20 -> {
                            button5.text = " 16 to 20"
                        }
                        else -> {
                            button5.text = "any other time"
                        }
                    }
                } catch (e: Exception) {
                    button5.text = "error time"
                }
            } else {
                button5.text  = "Invalid time"
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            // Called before the text is changed (e.g., when the user is about to delete text)
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

            }

            // Called when the text is being modified (each character typed or deleted)
            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if ((charSequence?.length ?: 0) > 10) {
                    editText.setError("Maximum 10 characters allowed!")
                }

                val buttonSubmit: Button = findViewById(R.id.button4)
                buttonSubmit.isEnabled = charSequence?.isNotEmpty() == true
                buttonSubmit.text = "typing"
                buttonSubmit.setBackgroundColor(1)
            }

            // Called after the text has been changed (e.g., after the user finished typing or text was cleared)
            override fun afterTextChanged(editable: Editable?) {
                val enteredText = editable.toString()

                if (enteredText.length > 2) {
                    textView.text = "OK"
                    textView.setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            android.R.color.holo_green_light
                        )
                    )
                } else {
                    textView.text = "Invalid input"
                    textView.setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            android.R.color.holo_red_light
                        )
                    )
                }

            }
        })

//        button3.setOnClickListener {
//            val dialogBuilder = AlertDialog.Builder(this)
//            dialogBuilder.setMessage("Are you sure you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes") { _, _ ->
//                    finish()
//                }
//                .setNegativeButton("No") { dialog, _ ->
//                    dialog.cancel()
//                }
//
//            val alert = dialogBuilder.create()
//            alert.show()
//        }
    }
}
