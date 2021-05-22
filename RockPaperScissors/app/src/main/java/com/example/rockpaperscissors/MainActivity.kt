package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.models.GameSize
import com.example.rockpaperscissors.models.RockPaperScissorsGame
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RockPaperScissorsAdapter
    private lateinit var rockPaperScissorsGame: RockPaperScissorsGame
    private lateinit var clRoot: ConstraintLayout

    private lateinit var rvOptions: RecyclerView
    private lateinit var confirm: Button
    private var gameSize :GameSize = GameSize.RANDOM

    companion object {
        private const val TAG = "Main activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clRoot = findViewById(R.id.clRoot)
        rvOptions = findViewById(R.id.rvOptions)
        confirm = findViewById(R.id.confirm)


        confirm.text = "confirm"
        rockPaperScissorsGame = RockPaperScissorsGame(gameSize)


        adapter = RockPaperScissorsAdapter(this, gameSize,rockPaperScissorsGame.cards, object: RockPaperScissorsAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {updateGameWithClick(position)} },confirm)
        rvOptions.adapter = adapter
        rvOptions.setHasFixedSize(true)
        rvOptions.layoutManager = GridLayoutManager(this,gameSize.getWidth())


        ViewCompat.setBackgroundTintList(confirm,ContextCompat.getColorStateList(this,R.color.teal_700))
        confirm.alpha = .4f
        confirm.setOnClickListener {
            Log.i(TAG, "Clicked!")

            if (!rockPaperScissorsGame.selectionMade()) {
                Snackbar.make(clRoot, "Please make a selection!", Snackbar.LENGTH_LONG).show()
            } else {
                rockPaperScissorsGame.updateConfirmed()
                val colorStateList = if (rockPaperScissorsGame.hasConfirmed()) ContextCompat.getColorStateList(this,R.color.teal_200) else ContextCompat.getColorStateList(this,R.color.teal_700)
                ViewCompat.setBackgroundTintList(confirm,colorStateList)
                confirm.alpha = 1.0f
            }
        }
    }

    private fun updateGameWithClick(position: Int) {

        rockPaperScissorsGame.selectCard(position)
        if (rockPaperScissorsGame.selectionMade()) {
            confirm.alpha = 1.0f}
        else {
            confirm.alpha = .4f
        }
        adapter.notifyDataSetChanged()
    }
}