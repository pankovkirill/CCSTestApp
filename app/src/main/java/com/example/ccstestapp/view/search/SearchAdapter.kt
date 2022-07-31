package com.example.ccstestapp.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccstestapp.R

class SearchAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private val data: ArrayList<String>
) :
    RecyclerView.Adapter<SearchAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.nameTextView).text = data
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: String)
    }
}