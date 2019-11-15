package dev.juliengrandchavin.scuderia.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import dev.juliengrandchavin.scuderia.R

class RaceResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_race_result)

        val driver1Rank: Int = intent.getIntExtra("driver1Place", 20)
        val driver2Rank: Int = intent.getIntExtra("driver2Place", 19)
        val cashPrize: Int = intent.getIntExtra("cashPrize", 10)

        val backButton = findViewById<Button>(R.id.backButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val cashPrizeText = findViewById<TextView>(R.id.cashprizeText)

        resultText.text = "Vos pilotes ont terminé  P${driver1Rank} et P${driver2Rank}!"
        cashPrizeText.text = "${cashPrize}k de cashprize"

        backButton.setOnClickListener {
            finish()
        }
    }
}
