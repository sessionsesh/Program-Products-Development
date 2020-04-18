package com.sessionsesh.lab3_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class NewEntryDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val view: View = inflater.inflate(R.layout.layout_dialog, null)
            builder.setView(view)
                // Add action buttons
                .setMessage("Create New Entry")
                .setPositiveButton("Create",
                    DialogInterface.OnClickListener { dialog, id ->
                        val fullName =
                            "${view.findViewById<EditText>(R.id.edit_text_name).text}"
                        addEntry(fullName)

                        val message: String = fullName
                        Toast.makeText(context, message, Toast.LENGTH_LONG)
                            .show()    // first argument can be fragment context [getContext()] or activity [getActivity()]
                    })
                .setNegativeButton("Cancel Creating",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    private fun addEntry(name: String) {
        val dbHelper = context?.let { DBHelper(it) }
        val db = dbHelper?.writableDatabase

        val fullNameList = name.split(" ")  //splitting fullName into lastName[0], firstName[1], midName[2]

        val values = ContentValues()
        values.put(StudentsContract.StudentEntry.COLUMN_LAST_NAME, fullNameList[0])
        values.put(StudentsContract.StudentEntry.COLUMN_FIRST_NAME, fullNameList[1])
        values.put(StudentsContract.StudentEntry.COLUMN_MIDDLE_NAME, fullNameList[2])

        val newRowId = db?.insert(StudentsContract.StudentEntry.TABLE_NAME, null, values)
        Log.d("MyLog", "Data was added to database on ID($newRowId)")
    }
}