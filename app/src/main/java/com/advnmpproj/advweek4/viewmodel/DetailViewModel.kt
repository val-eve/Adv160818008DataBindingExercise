package com.advnmpproj.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.advnmpproj.advweek4.model.Student

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()

    fun getStudentData()
    {
        val student1 = Student("05-1324293","Ilise", "3/7/2021","152-967-2142", "http://dummyimage.com/100x76.png/ff4444/ffffff")
        studentLD.value = student1
    }
}