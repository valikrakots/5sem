package com.example.lab10_4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import kotlinx.coroutines.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {


    var MinskInfoLabel: TextView? = null
    var VitebskInfoLabel: TextView? = null
    var detailedCityInfoLabel: TextView? = null
    var inputCityLabel: EditText? = null

    fun getWeatherInfo(api_url: String, func_ui: (JSONObject) -> (Unit)) {
        GlobalScope.launch {
            var weatherJson: JSONObject? = null
            var isCompleted = false

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, api_url, null,
                { response ->
                    weatherJson = response
                    isCompleted = true
                },
                {
                    isCompleted = true
                })

            val queue = Volley.newRequestQueue(applicationContext)
            queue.add(jsonObjectRequest)

            while (!isCompleted) {
                delay(2)
            }

            weatherJson?.let { func_ui(it) }

        }
    }

    fun onClickMinskInfo() {
        getWeatherInfo("https://api.openweathermap.org/data/2.5/weather?q=Minsk&appid=48b533322284e9b27492fc27baf42dfe",
            fun(weatherJson: JSONObject) {
                runOnUiThread {
                    MinskInfoLabel!!.text = (weatherJson.getJSONObject("main").getInt("temp") - 273).toString() + " Cº"
                }
            }
        )
    }

    fun onClickVitebskInfo() {
        getWeatherInfo("https://api.openweathermap.org/data/2.5/weather?q=Vitebsk&appid=48b533322284e9b27492fc27baf42dfe",
            fun(weatherJson: JSONObject) {
                runOnUiThread {
                    VitebskInfoLabel!!.text = (weatherJson.getJSONObject("main").getInt("temp") - 273).toString() + " Cº"
                }
            }
        )
    }

    fun onClickSomeCity(v: View?) {
        val city_name = inputCityLabel?.text

        getWeatherInfo(
            "https://api.openweathermap.org/data/2.5/forecast?q=${city_name}&appid=48b533322284e9b27492fc27baf42dfe",
            fun (weatherJson: JSONObject) {
                val info_str = "3 days weather forecast for: ${city_name} \n" + getForecast(weatherJson)

                runOnUiThread {
                    detailedCityInfoLabel!!.text = info_str
                }
            }
        )
    }

    fun extractDataFromJSON(weatherJSON: JSONObject): String {
        /* Вытаскиваем нужные данные из JSON. */

        val temperature = (weatherJSON.getJSONObject("main").getDouble("temp") - 273).toString()
        val feel_like_temperature = (weatherJSON.getJSONObject("main").getDouble("feels_like") - 273).toString()
        val wind_speed = (weatherJSON.getJSONObject("wind").getDouble("speed")).toString() + " m/s"

        val result = "Temperature: $temperature\n" + "feel like temperature: $feel_like_temperature\n" +
                "wind speed: $wind_speed\n\n"

        return result
    }

    fun getForecast(weatherJSON: JSONObject): String {
        val list_of_days = weatherJSON.getJSONArray("list")
        var result = ""

        // val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        for (i in 0 until 3 * 8) {
            val date_of_forecast = Date(list_of_days.getJSONObject(i).getLong("dt") * 1000)
            val cal = Calendar.getInstance()
            cal.time = date_of_forecast

            if (cal.get(Calendar.HOUR_OF_DAY) == 12) {
                result += cal.get(Calendar.DAY_OF_MONTH).toString() + ":\n" + extractDataFromJSON(list_of_days.getJSONObject(i))
                println(result)
            }
        }

        return result
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MinskInfoLabel = findViewById(R.id.minskInfo)
        VitebskInfoLabel = findViewById(R.id.seoulInfo)
        detailedCityInfoLabel = findViewById<TextView>(R.id.detailedCityInfo)
        inputCityLabel = findViewById(R.id.inputCityNameLabel)

        detailedCityInfoLabel!!.movementMethod = ScrollingMovementMethod()

        requestPermissions(Array(1) { "android.permission.INTERNET" }, 0) // Получаем доступ в интернет
        val btn1: Button = this.findViewById(R.id.button)
        val btn2: Button = this.findViewById(R.id.button2)
        btn1.setOnClickListener {
            onClickMinskInfo();
        }
        btn2.setOnClickListener {
            onClickVitebskInfo();
        }
    }
}