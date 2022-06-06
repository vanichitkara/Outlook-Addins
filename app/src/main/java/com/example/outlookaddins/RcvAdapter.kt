package com.example.outlookaddins

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.addin_layout.view.*


class RcvAdapter (private val list: List<MyAddIns>):
    RecyclerView.Adapter<RcvAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.addin_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val addin_list = list[position]
        val context = holder.itemView.context
        holder.Title.text = addin_list.title
        Glide.with(holder.itemView).load(addin_list.IconUrl).into(holder.Icon)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView:View): RecyclerView.ViewHolder(ItemView){
        val Title: TextView = itemView.findViewById(R.id.txt_title)
        val Icon: ImageView = itemView.findViewById(R.id.img_icon)
    }
}

