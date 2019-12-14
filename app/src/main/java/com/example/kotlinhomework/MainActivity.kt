package com.example.kotlinhomework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val messageForEmptyText = "Пожалуйста, заполните поле"
        const val RC_START_SECOND_ACTIVITY = 1
        const val EXTRA_TEXT = "text"
    }

    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            text = editext.text.toString()
            when (text) {
                null, "" -> toaster(messageForEmptyText)
                else -> startSecondActivity(text!!)
            }
        }
    }

    private fun toaster(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun startSecondActivity(message: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_TEXT, message)
        startActivityForResult(intent, RC_START_SECOND_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_START_SECOND_ACTIVITY) {
                if (data != null) {
                    val resultText = data.getStringExtra(EXTRA_TEXT)
                    if (!resultText.equals(text))
                        toaster("Данные из SecondActivity были изменены с $text на $resultText")
                }
            }
        }
    }
}

