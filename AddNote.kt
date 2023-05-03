package com.example.listtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listtask.databinding.ActivityAddNoteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNote : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener {

            val firstName = binding.regNo.text.toString()
            val lastName = binding.name.text.toString()
            val age = binding.course.text.toString()
            val userName = binding.courseCode.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = Users(firstName,lastName,age,userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.regNo.text.clear()
                binding.name.text.clear()
                binding.course.text.clear()
                binding.courseCode.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }


        }


    }
}


