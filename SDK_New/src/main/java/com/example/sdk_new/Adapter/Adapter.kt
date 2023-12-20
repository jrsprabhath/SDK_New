package com.example.sdk_new.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.opinionbar_sdk.R
import com.example.sdk_new.Models.Survey

class Adapter(private val context: Context, private val dataList: List<Survey>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val layoutInflator : LayoutInflater = LayoutInflater.from(context)
    // ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.tProjectId)
        val surveyNameView:TextView = itemView.findViewById(R.id.tSurveyName)
        val rewardView : TextView = itemView.findViewById(R.id.tReward)
        val surveyLengthView : TextView = itemView.findViewById(R.id.tLengSurvey)
        val go : Button = itemView.findViewById(R.id.bGo)
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = layoutInflator.inflate(R.layout.card_view,parent,false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.idTextView.text = item.ProjectId.toString()
        holder.surveyNameView.text = item.SurveyName
        holder.rewardView.text = item.RewardText
        val length = item.SurveyLength.toString() + " Minutes"
        holder.surveyLengthView.text = length
        holder.go.setOnClickListener(){
            val url = item.SurveyUrl // Replace with your URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
// Set the package to Chrome if you want to explicitly open it in Chrome
            intent.setPackage("com.android.chrome")
            startActivity(context,intent,null)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        val size = if(dataList.size > 20){
            20
        }else{
            dataList.size
        }
        return size
    }
}