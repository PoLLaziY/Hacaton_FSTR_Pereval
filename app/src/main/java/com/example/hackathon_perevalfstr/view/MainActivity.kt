package com.example.hackathon_perevalfstr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackathon_perevalfstr.R
import com.example.hackathon_perevalfstr.view.fragments.WelcomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentRoot, WelcomeFragment())
            .commit()
    }
}