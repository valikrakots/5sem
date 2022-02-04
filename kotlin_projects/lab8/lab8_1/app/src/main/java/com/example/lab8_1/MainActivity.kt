package com.example.lab8_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener {

    private lateinit var mDetector: GestureDetectorCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDetector = GestureDetectorCompat(this, this)
        mDetector.setOnDoubleTapListener(this)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "On down"
        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onFling"
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text =  "onLongPress"
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onScroll"
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onShowPress"
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onSingleTapUp"
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onDoubleTapEvent"
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        val res: TextView = findViewById(com.example.lab8_1.R.id.textView)
        res.text = "onSingleTapConfirmed"
        return true
    }
}