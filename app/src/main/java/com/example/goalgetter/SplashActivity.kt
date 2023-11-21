package com.example.goalgetter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val moveInAnimation = AnimationUtils.loadAnimation(this, R.anim.move_in_right)
        val imageView = findViewById<ImageView>(R.id.splash_image)
        imageView.startAnimation(moveInAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            // Start your main activity here
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}