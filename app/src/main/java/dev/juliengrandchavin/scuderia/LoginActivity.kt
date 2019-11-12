package dev.juliengrandchavin.scuderia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login)


    }
}
