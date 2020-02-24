package com.mirea.ikbo0218.sorokin.lab1

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberList = generateList(1000000)

        recycler_view.adapter = NumberAdapter(numberList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//optimization

    }

    private fun generateList(size: Int): List<NumberItem> {
        val list = ArrayList<NumberItem>()

        for (i in 1 until size) {
            val drawable: Int
            val color: Int
            if (i % 2 == 1) {
                drawable = R.drawable.ic_cloud_filled
                color = R.color.colorEven
            } else {
                drawable = R.drawable.ic_cloud_unfilled
                color = R.color.colorOdd
            }
            val item = NumberItem(drawable, "Item", "$i", color)
            list += item
        }
        return list
    }
}
