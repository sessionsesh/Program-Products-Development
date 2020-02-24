package com.mirea.ikbo0218.sorokin.lab1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*

class NumberAdapter(private val numberList: List<NumberItem>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.number_item, parent, false)//confuse line
        return NumberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val currentItem = numberList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1//equal to "holder.itemView.text_view_1.text = ..." (1)
        holder.textView2.text = currentItem.text2
        holder.cardView.setBackgroundResource(currentItem.color)
    }

    override fun getItemCount(): Int = numberList.size

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view//itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.text_view_1//cached (1)
        val textView2: TextView = itemView.text_view_2
        val cardView: RelativeLayout = itemView.cardView
    }


}