package com.advnmpproj.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.advnmpproj.advweek4.model.Student
import com.advnmpproj.advweek4.view.StudentDetailFragmentArgs
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    private val TAG = "STRINGTAG"
    private var queue: RequestQueue?= null

    fun getStudentData(studentId:String)
    {
//        val student1 = Student("05-1324293","Ilise", "3/7/2021","152-967-2142", "http://dummyimage.com/100x76.png/ff4444/ffffff")
//        studentLD.value = student1

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val res = Gson().fromJson<Student>(response, Student::class.java)
                studentLD.value = res
                Log.d("showRes", response.toString())
            },
            {
                Log.d("showRes", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}