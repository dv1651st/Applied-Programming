package com.example.rockpaperscissors

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.models.GameSize
import com.example.rockpaperscissors.models.RockPaperScissorsImages
import kotlin.math.min

class RockPaperScissorsAdapter(
    private val context: Context,
    private val gameSize: GameSize,
    private val cards: List<RockPaperScissorsImages>,
    private val cardClickListener: CardClickListener,
    private val confirm: Button,
    private var color: Int = Color.LTGRAY,

) :
    RecyclerView.Adapter<RockPaperScissorsAdapter.ViewHolder>() {

    companion object {
        private const val MARGIN_SIZE = 10
    }

    interface CardClickListener {
        fun onCardClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth = parent.width / gameSize.getWidth() - (2 * MARGIN_SIZE)
        val cardHeight = parent.height / gameSize.getHeight() - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardWidth,cardHeight)
        val view = LayoutInflater.from(context).inflate(R.layout.rps_options, parent, false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount() = gameSize.numCards

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position: Int) {
            val rockPaperScissorsImages = cards[position]
            imageButton.setImageResource(if (rockPaperScissorsImages.random) R.drawable.ic_question else rockPaperScissorsImages.image)
            val colorStateList = if (rockPaperScissorsImages.isSelected) ContextCompat.getColorStateList(context,R.color.teal_200) else null
            ViewCompat.setBackgroundTintList(imageButton,colorStateList)
            imageButton.setOnClickListener {
                cardClickListener.onCardClicked(position)
            }
        }
    }
}
