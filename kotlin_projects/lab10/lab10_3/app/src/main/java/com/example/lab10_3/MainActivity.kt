package com.example.lab10_3

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private var enabled: Boolean = false
    private var k = 0
    private lateinit var ar: ArrayList<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val one: TextView = this.findViewById(R.id.textView3)
        val two: TextView = this.findViewById(R.id.textView5)
        val three: TextView = this.findViewById(R.id.textView7)
        val four: TextView = this.findViewById(R.id.textView9)
        val five: TextView = this.findViewById(R.id.textView11)
        val ones: TextView = this.findViewById(R.id.textView2)
        val twos: TextView = this.findViewById(R.id.textView4)
        val threes: TextView = this.findViewById(R.id.textView6)
        val fours: TextView = this.findViewById(R.id.textView8)
        val fives: TextView = this.findViewById(R.id.textView10)
        val gps: TextView = this.findViewById(R.id.textView12)
        val but1: Button = this.findViewById(R.id.button2)
        val but2: Button = this.findViewById(R.id.button)
        ar = ArrayList()
        ar.add(ones)
        ar.add(one)
        ar.add(twos)
        ar.add(two)
        ar.add(threes)
        ar.add(three)
        ar.add(fours)
        ar.add(four)
        ar.add(fives)
        ar.add(five)
        ar.add(gps)
        but1.setOnClickListener {

            but1.isEnabled = false
            k = 0
            enabled = true
            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            one.text = currentDate
            getLocation()
        }
        but2.setOnClickListener {
            but1.isEnabled = true
            enabled = false
            one.text = ""
            two.text = ""
            three.text = ""
            four.text = ""
            five.text = ""
            ones.text = ""
            twos.text = ""
            threes.text = ""
            fours.text = ""
            fives.text = ""
        }
    }


    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            ar[10].setText("GPS is turned on...");

        } else {
            ar[10].setText("GPS is not turned on. Press stop...");
            return
        }
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.1f,this)
    }


    override fun onLocationChanged(location: Location) {
        if(enabled) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            ar[k].text = location.latitude.toString()
            ar[k+1].text = currentDate
            k += 2
            k %= 10
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}