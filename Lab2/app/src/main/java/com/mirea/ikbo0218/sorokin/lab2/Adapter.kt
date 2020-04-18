package com.mirea.ikbo0218.sorokin.lab2

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.lang.Exception


// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
// Each data item is just a string in this case that is shown in a TextView.
class Adapter(private val dataset: ArrayList<Element>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view_1) as TextView
        val imageView: ImageView = itemView.findViewById((R.id.image_view_1)) as ImageView
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val elementView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_element,
            parent,
            false
        ) as View
        return ViewHolder(elementView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataset[position].name
        Picasso.get()
            .load(dataset[position].link_to_img)
            .into(holder.imageView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    Log.e("Picasso", "Success")
                }

                override fun onError(e: Exception?) {
                    holder.imageView.setImageResource(R.drawable.ic_error_black_24dp)
                    Log.e("Picasso", e.toString())
                }

            })


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataset.size

}