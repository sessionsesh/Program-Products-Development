package com.mirea.ikbo0218.sorokin.lab1

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibm.icu.text.RuleBasedNumberFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

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
            //https://ru.stackoverflow.com/questions/449055/java-%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F-%D1%81%D1%83%D0%BC%D0%BC%D1%8B-%D0%BF%D1%80%D0%BE%D0%BF%D0%B8%D1%81%D1%8C%D1%8E

            val item = NumberItem(drawable, i, "$i", color)
            list += item
        }
        return list
    }
}
