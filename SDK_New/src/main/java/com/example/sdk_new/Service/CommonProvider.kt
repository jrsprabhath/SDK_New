package com.example.sdk_new.Service

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintSet.Layout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CommonProvider {

    fun showDatePicker(context : Context ,calendar: Calendar,DOB: TextView) {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            context, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time).toString()
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                DOB.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }
}