package com.example.listtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter( private val noteList: ArrayList<Users>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.noteTv.text = "Work :" + currentNote.work+"\nDescription : " + currentNote.description+"\n"+"Priority: "+currentNote.priority+"\n"+"Other Info:: "+currentNote.otherInfo
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTv: TextView = itemView.findViewById(R.id.noteName)
    }



}