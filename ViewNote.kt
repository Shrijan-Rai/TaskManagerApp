package com.example.listtask

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewNote : AppCompatActivity() {

    private lateinit var noteRecyclerView: RecyclerView
    private lateinit var loadingDataTv: TextView
    private lateinit var noteList: ArrayList<Users>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        noteRecyclerView = findViewById(R.id.viewNoteDetail)
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
        noteRecyclerView.setHasFixedSize(true)
        loadingDataTv = findViewById(R.id.loadData)
        noteList = arrayListOf<Users>()

        getNoteData()
    }


    private fun getNoteData() {
        noteRecyclerView.visibility = View.GONE
        loadingDataTv.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noteList.clear()
                if(snapshot.exists()) {
                    for(stuSnap in snapshot.children) {
                        val studentData = stuSnap.getValue(Users::class.java)
                        noteList.add(studentData!!)
                    }
                    val mAdapter = NoteAdapter(noteList)
                    noteRecyclerView.adapter = mAdapter

                    noteRecyclerView.visibility = View.VISIBLE
                    loadingDataTv.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}