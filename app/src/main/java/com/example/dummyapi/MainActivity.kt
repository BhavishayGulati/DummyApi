package com.example.dummyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null
    private var markets : ArrayList<Market> = ArrayList()
    private lateinit var rvJSON : RecyclerView
    private lateinit var adapter : RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvJSON = findViewById(R.id.rvJSON)

        rvJSON.layoutManager = LinearLayoutManager(this)
        rvJSON.setHasFixedSize(true)

        adapter = RVAdapter(
            this,
            markets
        )
        rvJSON.adapter = adapter

        requestQueue = Volley.newRequestQueue(this)

        fetchJSON()



    }


    private fun fetchJSON(){
        val url = "https://www.cryptingup.com/api/markets"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val jsonArray = response.getJSONArray("markets")
                for (i in 0 until jsonArray.length()) {
                    val marketJSONObject = jsonArray.getJSONObject(i)
                    val market = Market(
                        marketJSONObject.getString("exchange_id"),
                        marketJSONObject.getString("symbol"),
                        marketJSONObject.getDouble("price_unconverted"),
                        marketJSONObject.getDouble("price"),
                        marketJSONObject.getDouble("change_24h"),
                        marketJSONObject.getDouble("spread"),
                        marketJSONObject.getDouble("volume_24h"),
                        marketJSONObject.getString("status"),
                        marketJSONObject.getString("time")
                    )
                    
                    markets.add(market)
                    
                }

                val paginationJSONObject = response.getJSONObject("pagination")
                val next = paginationJSONObject.getString("next")

                adapter.notifyDataSetChanged()

                Log.e( "market size : ", markets.size.toString())

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}