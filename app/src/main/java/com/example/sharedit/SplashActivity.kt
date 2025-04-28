package com.example.sharedit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        // Find the ImageView and apply the rotation animation
        val splashIcon: ImageView = findViewById(R.id.splashIcon)
        val rotateAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.splash)
        splashIcon.startAnimation(rotateAnim)

        // Transition to MainActivity after 3 seconds (for example)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Finish SplashActivity to prevent going back
        }, 3000) // 3 seconds delay
    }
}
