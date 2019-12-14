package com.example.kotlinhomework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var textFromMainActivity: String? = null
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textFromMainActivity = intent.getStringExtra(MainActivity.EXTRA_TEXT)
        editext.setText(textFromMainActivity)
        button.setOnClickListener {
            text = editext.text.toString()
            when (text) {
                null, "" -> toaster(MainActivity.messageForEmptyText)
                else -> {
                    val intent = Intent()
                    intent.putExtra(MainActivity.EXTRA_TEXT, text)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }

    }

    private fun toaster(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
