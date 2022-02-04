package com.example.lab_kr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val im: ImageView = this.findViewById(R.id.imageView)

        val animation = AnimationUtils.loadAnimation(this, R.anim.falling)
        im.startAnimation(animation)

    }
}