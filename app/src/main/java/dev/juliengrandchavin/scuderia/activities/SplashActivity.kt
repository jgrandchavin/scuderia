package dev.juliengrandchavin.scuderia.activities

import GameRepository
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.juliengrandchavin.scuderia.R
import java.util.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameRepository =
            GameRepository(this.getSharedPreferences("prefs", Context.MODE_PRIVATE))
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

        gameRepository.initGame()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}
