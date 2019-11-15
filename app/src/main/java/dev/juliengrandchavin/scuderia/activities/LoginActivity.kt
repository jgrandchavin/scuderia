package dev.juliengrandchavin.scuderia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import dev.juliengrandchavin.scuderia.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login)
        val intent = Intent(this, MenuActivity::class.java)

        loginButton.setOnClickListener {
            startActivity(intent)
        }
    }
}
