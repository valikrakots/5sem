package com.example.lab8_4

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener {

    private var gLibrary: GestureLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureSetup()
    }


    private fun gestureSetup(){
        gLibrary = GestureLibraries.fromRawResource(this, com.example.lab8_4.R.raw.gestures)

        if(gLibrary?.load() == false){
            finish()
        }
        val i: GestureOverlayView = this.findViewById(com.example.lab8_4.R.id.ingest)
        i.addOnGesturePerformedListener(this)
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val prediction = gLibrary?.recognize(gesture)

        prediction?.let{
            if(it.size > 0 && it[0].score > 1.0){
                val action = it[0].name
                val text: TextView = this.findViewById(R.id.textView2)
                if (action == "а")
                    text.text = text.text.toString() + "ф"
                else if (action == "б")
                    text.text = text.text.toString() + "б"
                else if (action == "в")
                    text.text = text.text.toString() + "в"
                else if (action == "г")
                    text.text = text.text.toString() +"г"
                else if (action == "д")
                    text.text = text.text.toString() + "д"
                else if (action == "е")
                    text.text = text.text.toString() +"е"
                else if (action == "ж")
                    text.text = text.text.toString() +"ж"
                else if (action == "з")
                    text.text = text.text.toString() +"з"
                else if (action == "и")
                    text.text = text.text.toString() +"и"
                else if (action == "к")
                    text.text = text.text.toString() +"к"
                else if (action == "л")
                    text.text = text.text.toString() +"л"
                else if (action == "м")
                    text.text = text.text.toString() +"м"
                else if (action == "н")
                    text.text = text.text.toString() +"н"
                else if (action == "о")
                    text.text = text.text.toString() +"о"
                else if (action == "п")
                    text.text = text.text.toString() +"п"
                else if (action == "р")
                    text.text = text.text.toString() +"р"
                else if (action == "с")
                    text.text = text.text.toString() +"с"
                else if (action == "т")
                    text.text = text.text.toString() +"т"
                else if (action == "ф")
                    text.text = text.text.toString() +"ф"
                else if (action == "х")
                    text.text = text.text.toString() +"х"
                else if (action == "ц")
                    text.text = text.text.toString() +"ц"
                else if (action == "ч")
                    text.text = text.text.toString() +"ч"
                else if (action == "щ")
                    text.text = text.text.toString() +"щ"
                else if (action == "ы")
                    text.text = text.text.toString() +"ы"
                else if (action == "э")
                    text.text = text.text.toString() +"э"
                else if (action == "ю")
                    text.text = text.text.toString() +"ю"
                else if (action == "я")
                    text.text = text.text.toString() +"я"
                else if (action == "3")
                    text.text = text.text.toString() +" "
                else if (action == "2") {
                    var s: String = text.text.toString()
                    s = s.substring(0, s.length-1)
                    text.text = s
                }
            }
        }
    }
}