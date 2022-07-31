package com.example.ccstestapp.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccstestapp.R
import com.example.ccstestapp.model.data.RatesModel

class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<RatesModel> = arrayListOf()

    fun setData(data: List<RatesModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: RatesModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.valueTextView).text = data.value.toString()
                itemView.findViewById<TextView>(R.id.nameTextView).text = data.name
                itemView.findViewById<ImageView>(R.id.addToFavorite).setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: RatesModel)
    }
}