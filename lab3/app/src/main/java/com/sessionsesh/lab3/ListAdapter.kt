package com.sessionsesh.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val dbHelper: DBHelper) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cursor = dbHelper.readableDatabase.rawQuery(
            "SELECT * FROM ${StudentsContract.StudentEntry.TABLE_NAME} LIMIT 1 OFFSET $position",
            arrayOf()
        )
        cursor.moveToFirst()
        holder.textViewID.text = cursor.getString(cursor.getColumnIndex(StudentsContract.StudentEntry.ID))
        holder.textViewName.text = cursor.getString(cursor.getColumnIndex(StudentsContract.StudentEntry.COLUMN_NAME))
        holder.textViewDate.text = cursor.getString(cursor.getColumnIndex(StudentsContract.StudentEntry.COLUMN_DATE_ADDED))
        cursor.close()
    }

    override fun getItemCount(): Int {
        val cursor = dbHelper.readableDatabase
            .rawQuery("SELECT COUNT(*) AS counter FROM ${StudentsContract.StudentEntry.TABLE_NAME}", arrayOf())

        cursor.moveToFirst()
        val count = cursor.getInt(cursor.getColumnIndex("counter"))
        cursor.close()
        return count
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewID: TextView = itemView.item_id
        val textViewName: TextView = itemView.item_name
        val textViewDate: TextView = itemView.item_date
    }


}

