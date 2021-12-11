package com.reeta.calenderproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reeta.calenderproject.R

class CalenderAdapter(val dateOfMonth:ArrayList<String>,val listner: ClickListner):RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.calender_date_layout,parent,false)
        var layoutParams:ViewGroup.LayoutParams=view.layoutParams
        layoutParams.height=((parent.height * 0.1).toInt())
        return CalenderViewHolder(view,listner)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
       val list=dateOfMonth[position]
        holder.setData(list)

    }

    override fun getItemCount(): Int {
        return dateOfMonth.size
    }

    class CalenderViewHolder(itemView: View,val listner: ClickListner):RecyclerView.ViewHolder(itemView) {

        val date:TextView=itemView.findViewById(R.id.tvDate)
        fun setData(list:String){
            date.text=list
            date.setOnClickListener {
                listner.onDateClick(adapterPosition,list)
            }
        }


    }


}