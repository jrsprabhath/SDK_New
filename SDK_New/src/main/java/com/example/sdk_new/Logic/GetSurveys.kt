package com.example.sdk_new.Logic

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opinionbar_sdk.R
import com.example.sdk_new.Adapter.Adapter
import com.example.sdk_new.Models.Survey
import com.example.sdk_new.Service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetSurveys:AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userGuid : String
    private lateinit var progressbar : ProgressBar
    private val USER_GUID_EXTRA = "user_guid"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surveys_list)
        progressbar = findViewById(R.id.progressBar)
        progressbar.visibility = View.VISIBLE
        userGuid = intent.getStringExtra(USER_GUID_EXTRA) ?: ""
        getSurveyList(this,userGuid){surveys ->
            progressbar.visibility = View.GONE
            recyclerView =  findViewById(R.id.listRecyclerView)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = Adapter(this, surveys)
        }
    }
    private fun getSurveyList(context: Context, userGuid: String, callback: (List<Survey>) -> Unit){
        val retrofitClient = RetrofitClient(1)
        retrofitClient.apiService().GetSurveys(userGuid).enqueue( object :
            Callback<List<Survey>> {
            override fun onResponse(
                call: Call<List<Survey>>,
                response: Response<List<Survey>>
            ) {
                val listSurveys = response.body() ?: emptyList()
                callback.invoke(listSurveys)
            }
            override fun onFailure(call: Call<List<Survey>>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                callback.invoke(emptyList())
            }
        })
    }
}