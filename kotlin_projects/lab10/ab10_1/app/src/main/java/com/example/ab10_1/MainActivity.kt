package com.example.ab10_1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*
import android.os.AsyncTask







class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private var enabled: Boolean = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val one:TextView = this.findViewById(R.id.textView10)
        val two:TextView = this.findViewById(R.id.textView11)
        val three:TextView = this.findViewById(R.id.textView12)
        val four:TextView = this.findViewById(R.id.textView13)
        val but1:Button = this.findViewById(R.id.button)
        val but2:Button = this.findViewById(R.id.button2)
        but1.setOnClickListener {
            enabled = true
            but1.isEnabled = false
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            one.text = currentDate
            getLocation()
        }
        but2.setOnClickListener {
            enabled = false
            but1.isEnabled = true
            one.text = ""
            two.text = ""
            three.text = ""
            four.text = ""
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0.1f,this)
    }


    override fun onLocationChanged(location: Location) {
        if(enabled) {
            val two: TextView = this.findViewById(R.id.textView11)
            val three: TextView = this.findViewById(R.id.textView12)
            val four: TextView = this.findViewById(R.id.textView13)
            two.text = location.latitude.toString()
            three.text = location.longitude.toString()
            four.text = location.altitude.toString()
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


