package com.example.rockpaperscissors.models


import android.util.Log
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.RockPaperScissorsAdapter


class RockPaperScissorsGame(private val gameSize: GameSize) {

    val cards: List<RockPaperScissorsImages>
//    val TAG = "RockPaperScissorsGame"
    private var indexOfPreviousPosition: Int? = null
    private var indexOfCurrentPosition: Int? = null
    private var confirmed: Boolean = false

    init {

        val paper = RockPaperScissorsImages(R.drawable.ic_paper,"paper")
        val rock = RockPaperScissorsImages(R.drawable.ic_rock,"rock")
        val scissors = RockPaperScissorsImages(R.drawable.ic_scissors,"scissors")
        val random = listOf(paper,rock,scissors).shuffled().take(1)[0].copy(random = true)

//        Log.i(TAG,"paper=${paper.random} rock=${rock.random} scissors=${scissors.random} random=${random.random}")

        cards = when(gameSize.numCards) {
            3 -> listOf(paper,rock,scissors).shuffled()
            else -> listOf(paper,rock,scissors,random).shuffled()
        }
    }

    fun selectCard(position: Int) {
        cards[position].isSelected = !cards[position].isSelected
        if (indexOfPreviousPosition != null && indexOfPreviousPosition != position) {
            cards[indexOfPreviousPosition!!].isSelected = false }
        indexOfPreviousPosition = position

    }

    fun hasConfirmed(): Boolean {
        return confirmed
    }

    fun updateConfirmed() {
        confirmed = !confirmed
    }

    fun selectionMade(): Boolean {
        for (card in cards)
        {
            if (card.isSelected)
                return card.isSelected
        }
        return false
    }

}
