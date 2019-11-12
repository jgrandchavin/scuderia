package dev.juliengrandchavin.scuderia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class SplashActivity : AppCompatActivity() {

    private var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LoginActivity::class.java)

        fbAuth.addAuthStateListener {
                firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        startActivity(intent)
                    }
                }, 2000)
            }
        }
    }
}
