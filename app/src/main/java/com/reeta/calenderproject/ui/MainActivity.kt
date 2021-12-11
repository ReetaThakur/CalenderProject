package com.reeta.calenderproject.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.reeta.calenderproject.R
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity(),ClickListner,View.OnClickListener {

    lateinit var previousMonth:Button
    lateinit var nextMonth:Button
    lateinit var monthOfYear:TextView
    lateinit var selectedDate:LocalDate
    lateinit var calenderAdapter: CalenderAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        selectedDate= LocalDate.now()
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setMonthView(){
        monthOfYear.text=monthYearFromDate(selectedDate)
        var daysInMonth:ArrayList<String> = daysInMonthArray(selectedDate)
        calenderAdapter=CalenderAdapter(daysInMonth,this)
        val gridLayoutManager=GridLayoutManager(applicationContext,7)
        recyclerView.apply {
            adapter=calenderAdapter
            layoutManager=gridLayoutManager

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate): String {
        var formatter :DateTimeFormatter=DateTimeFormatter.ofPattern("MMMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }


  fun  initialize(){
      previousMonth=findViewById(R.id.tvDecreaseMonth)
      nextMonth=findViewById(R.id.tvIncreaseMonth)
      monthOfYear=findViewById(R.id.tvMonthShow)
      previousMonth.setOnClickListener(this)
      nextMonth.setOnClickListener(this)
  }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        var id=v!!.id
        when(id){
            R.id.tvDecreaseMonth ->{
              selectedDate=selectedDate.minusMonths(1)
                setMonthView()
            }
            R.id.tvIncreaseMonth ->{
             selectedDate=selectedDate.plusMonths(1)
                setMonthView()
            }
        }
    }

    override fun onDateClick(positon: Int, day: String) {

            Toast.makeText(this, "day $positon and $day", Toast.LENGTH_SHORT).show()
        }

}