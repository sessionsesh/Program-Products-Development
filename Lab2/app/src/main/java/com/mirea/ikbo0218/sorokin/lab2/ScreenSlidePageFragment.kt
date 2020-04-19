package com.mirea.ikbo0218.sorokin.lab2

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class ScreenSlidePageFragment(val obj: Element) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment: View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        val imageView = fragment.findViewById<ImageView>(R.id.imageView)
        val textView = fragment.findViewById<TextView>(R.id.textView)

        textView.text = obj.name
        Picasso.get()
            .load(obj.link_to_img).resize(900,900)
            .into(imageView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    Log.d("Picasso", "Success")
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.ic_error_black_24dp)

                    Log.e("Picasso", "Cant download and set the image because of:\n$e")
                }
            })

        return fragment
    }


}