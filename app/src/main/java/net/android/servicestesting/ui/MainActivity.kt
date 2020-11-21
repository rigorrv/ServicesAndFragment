package net.android.servicestesting.ui


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.content.Context
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import net.android.servicestesting.R
import net.android.servicestesting.RandomNumberService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

