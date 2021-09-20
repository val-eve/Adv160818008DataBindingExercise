package com.advnmpproj.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.advnmpproj.advweek4.model.Student
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application):AndroidViewModel(application) {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "STRINGTAG"
    private var queue:RequestQueue ?= null

    fun refresh() {
//        val student1 = Student("05-1324293","Ilise", "3/7/2021","152-967-2142", "http://dummyimage.com/100x76.png/ff4444/ffffff")
//        val student2 = Student("20-4483538","Kristyn", "3/20/2021","646-104-3239", "http://dummyimage.com/100x75.png/cc0000/ffffff")
//        val student3 = Student("62-8512409","Gallagher", "10/21/2020","964-177-2675", "http://dummyimage.com/100x75.png/dddddd/000000")
//
//        studentsLD.value = arrayListOf<Student>(student1, student2, student3)
//        loadingErrorLD.value = false
//        loadingDoneLD.value = false

        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<List<Student>>() { }.type
                    val res = Gson().fromJson<List<Student>>(response, sType)
                    studentsLD.value = res
                    loadingLD.value = false
                    Log.d("showVolley", response.toString())
                },
                {
                    loadingErrorLD.value = true
                    loadingLD.value = false
                    Log.d("showVolley", it.toString())
                })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}