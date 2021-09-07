package com.advnmpproj.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.advnmpproj.advweek4.model.Student

class ListViewModel:ViewModel() {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    fun refresh() {
        val student1 = Student("05-1324293","Ilise", "3/7/2021","152-967-2142", "http://dummyimage.com/100x76.png/ff4444/ffffff")
        val student2 = Student("20-4483538","Kristyn", "3/20/2021","646-104-3239", "http://dummyimage.com/100x75.png/cc0000/ffffff")
        val student3 = Student("62-8512409","Gallagher", "10/21/2020","964-177-2675", "http://dummyimage.com/100x75.png/dddddd/000000")

        studentsLD.value = arrayListOf<Student>(student1, student2, student3)
        loadingErrorLD.value = false
        loadingDoneLD.value = false
    }
}