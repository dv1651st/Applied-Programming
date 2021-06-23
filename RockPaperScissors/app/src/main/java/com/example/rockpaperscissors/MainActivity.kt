package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.models.GameSize
import com.example.rockpaperscissors.models.RockPaperScissorsGame
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
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

        val intent = Intent(this, Login::class.java)
        startActivity(intent)
//        setupGame()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_newgame -> {
                showAlertDialog("Start a new game?",null, View.OnClickListener {
                    setupGame()
                })
                return true
            }
            R.id.mi_new_size -> {
                showNewSizeDialog()
                return true
            }
            R.id.mi_find_player -> {
                showFindPlayerDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFindPlayerDialog() {
        val intent = Intent(this, FindPlayer::class.java)
        startActivity(intent)
//        FIND_PLAYER_REQUEST_CODE to use for startActivityForResult(this, requestcode)
    }

    private fun showNewSizeDialog() {
        val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
        val radioGroupSize = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
        when (gameSize) {
            GameSize.REGULAR -> radioGroupSize.check(R.id.rbReg)
            GameSize.RANDOM -> radioGroupSize.check(R.id.rbRan)
        }
        showAlertDialog("Choose new size", boardSizeView, View.OnClickListener {
            gameSize = when (radioGroupSize.checkedRadioButtonId) {
                R.id.rbReg -> GameSize.REGULAR
                else -> GameSize.RANDOM
            }
            setupGame()
        })
    }

    private fun showAlertDialog(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Ok") {_,_ ->
                positiveClickListener.onClick(null)
            }.show()
    }

    private fun setupGame() {

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