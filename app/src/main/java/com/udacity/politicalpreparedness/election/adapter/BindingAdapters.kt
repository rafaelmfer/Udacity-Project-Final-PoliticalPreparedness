package com.udacity.politicalpreparedness.election.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("dateString")
fun dateToString(textView: TextView, date: Date?) {
    if (date != null) {
        textView.text = SimpleDateFormat("EEE MMM dd yyyy", Locale.US).format(date)
    }
}