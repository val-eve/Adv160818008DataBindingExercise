package com.advnmpproj.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.advnmpproj.advweek4.R
import com.advnmpproj.advweek4.databinding.StudentListItemBinding
import com.advnmpproj.advweek4.model.Student
import com.advnmpproj.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter (val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(val view: StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    fun updateStudentList(newStudentList:List<Student>)
    {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val v = inflater.inflate(R.layout.student_list_item, parent, false)

        val v = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)

        return StudentViewHolder(v)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this

        /*with(holder.view)
        {
            val studentId = studentList[position].id
            txtID.text = studentId
            txtName.text = studentList[position].name
            imageView.loadImage(studentList[position].photoUrl.toString(), progressBar)

            btnDetail.setOnClickListener {
                val action = StudentListFragmentDirections.actionStudentDetail(studentId.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }*/
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onButtonDetailClick(view: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}