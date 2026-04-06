package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Artwork(
    val imageRes: Int,
    val titleRes: Int,
    val authorRes: Int
)

class MainActivity : AppCompatActivity() {

    private lateinit var artworks: List<Artwork>
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        artworks = listOf(
            Artwork(R.drawable.mona_lisa, R.string.mona_title, R.string.mona_author),
            Artwork(R.drawable.starry_night, R.string.starry_title, R.string.starry_author),
            Artwork(R.drawable.last_supper, R.string.supper_title, R.string.supper_author)
        )

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("index", 0)
        }

        val imageView = findViewById<ImageView>(R.id.imageArtwork)
        val titleText = findViewById<TextView>(R.id.textTitle)
        val authorText = findViewById<TextView>(R.id.textAuthor)
        val buttonPrevious = findViewById<Button>(R.id.buttonPrevious)
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        fun updateUI() {
            val artwork = artworks[currentIndex]

            imageView.setImageResource(artwork.imageRes)
            titleText.setText(artwork.titleRes)
            authorText.setText(artwork.authorRes)

            // Accessibility
            imageView.contentDescription = getString(artwork.titleRes)
            buttonPrevious.contentDescription = getString(R.string.button_previous)
            buttonNext.contentDescription = getString(R.string.button_next)

            // Disable buttons on bounds
            buttonPrevious.isEnabled = currentIndex > 0
            buttonNext.isEnabled = currentIndex < artworks.size - 1
        }

        buttonPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        buttonNext.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateUI()
            }
        }

        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", currentIndex)
    }
}
