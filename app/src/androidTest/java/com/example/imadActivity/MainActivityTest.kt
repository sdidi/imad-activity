package com.example.imadActivity

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        // Launch the activity before each test
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testButtonClickUpdatesTextView() {
        activityScenario.onActivity { activity ->
            // Set up references to UI elements
            val button: Button = activity.findViewById(R.id.button)
            val textView: TextView = activity.findViewById(R.id.textView)
            val editText: EditText = activity.findViewById(R.id.editText)

            // Simulate user input
            editText.setText("John")

            // Perform button click
            button.performClick()

            // Check if the TextView's text was updated
            assertEquals("Hello, John! Button was clicked!", textView.text)
            assertEquals(Color.RED, textView.currentTextColor)
        }
    }
}
