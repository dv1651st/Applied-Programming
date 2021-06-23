package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FindPlayer : AppCompatActivity() {
    private lateinit var btnFindPlayer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_player)

        btnFindPlayer = findViewById(R.id.btnFind)
        btnFindPlayer.setOnClickListener {
            saveDataToFireBase()
        }
    }

    private fun saveDataToFireBase() {
        TODO("Not yet implemented")
    }

}