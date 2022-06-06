package com.example.outlookaddins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.addins.store.office.com/"
    private lateinit var rcv: RecyclerView
    private var list:ArrayList<MyAddIns> = ArrayList()
    private var rcvAdapter = RcvAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initView()
        getData()
    }

    private fun getData() {
        val url = BASE_URL + "api/addins/search?client=And_Outlook&cv=16.9.0.0&rs=en-US&skiptoitem=0&top=75"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val jsonArray : JSONArray = response.getJSONArray("Values")
                for (i in 0 until jsonArray.length()) {
                    val myAddInsObject = jsonArray.getJSONObject(i)
                    list.add(
                        MyAddIns(
                            myAddInsObject.getString("Title"),
                            myAddInsObject.getString("IconUrl")
                        )
                    )
                }

                //layout manager
                rcv.layoutManager = LinearLayoutManager(this)

                //attaching the adapter
                rcv.adapter = rcvAdapter


            },{
                Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(jsonObjectRequest)
    }

    private fun initView() {
        rcv = findViewById(R.id.rcv)
    }
}