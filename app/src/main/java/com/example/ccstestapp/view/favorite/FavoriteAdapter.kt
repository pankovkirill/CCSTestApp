package com.example.ccstestapp.view.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccstestapp.R
import com.example.ccstestapp.model.room.FavoriteEntity

class FavoriteAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private val onLongListItemClickListener: OnLongListItemClickListener,
) :
    RecyclerView.Adapter<FavoriteAdapter.RecyclerItemViewHolder>() {

    private var data = arrayListOf<FavoriteEntity>()

    fun setData(data: ArrayList<FavoriteEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

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

        fun bind(favoriteEntity: FavoriteEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.nameTextView).text = favoriteEntity.name
                itemView.setOnClickListener {
                    onListItemClickListener.onItemClick(favoriteEntity.name)
                }
                itemView.setOnLongClickListener {
                    onLongListItemClickListener.onItemClick(
                        itemView,
                        layoutPosition,
                        favoriteEntity.id,
                        data
                    )
                }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: String)
    }

    interface OnLongListItemClickListener {
        fun onItemClick(
            itemView: View,
            layoutPosition: Int,
            id: Int,
            data: MutableList<FavoriteEntity>
        ): Boolean
    }
}