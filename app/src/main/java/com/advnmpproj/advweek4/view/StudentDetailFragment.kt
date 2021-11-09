package com.advnmpproj.advweek4.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.advnmpproj.advweek4.R
import com.advnmpproj.advweek4.databinding.FragmentStudentDetailBinding
import com.advnmpproj.advweek4.util.loadImage
import com.advnmpproj.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonNotificationClickListener {
    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)

//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getStudentData(studentId)

        dataBinding.updateListener = this
        dataBinding.notifListener = this

        observeViewModel()
    }

    fun observeViewModel()
    {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            Log.d("tes", it.toString())
            dataBinding.student = it

            /* txtId.setText(it.id)
            txtName.setText(it.name)
            txtDob.setText(it.dob)
            txtPhone.setText(it.phone)
            imageViewDetail.loadImage(it.photoUrl.toString(), progressBarDetail)

            var student = it
            btnNotif.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                    MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
                }
            } */
        })
    }

    override fun onButtonUpdateClick(v: View) {
        TODO("Not yet implemented")
    }

    override fun onButtonNotificationClick(v: View) {
        var student = dataBinding.student

        Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            MainActivity.showNotification(
                student?.name.toString(),
                "A new notification created",
                R.drawable.ic_baseline_person_24
            )
        }
    }
}