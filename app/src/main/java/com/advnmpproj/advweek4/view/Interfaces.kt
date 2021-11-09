package com.advnmpproj.advweek4.view

import android.view.View

interface ButtonDetailClickListener{
    fun onButtonDetailClick(view:View)
}

interface ButtonUpdateClickListener{
    fun onButtonUpdateClick(v:View)
}

interface ButtonNotificationClickListener{
    fun onButtonNotificationClick(v:View)
}