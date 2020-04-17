package com.sessionsesh.lab3

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
import kotlinx.android.synthetic.*

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
                        val firstName =
                            "${view.findViewById<EditText>(R.id.edit_text_first_name).text}"
                        val lastName =
                            "${view.findViewById<EditText>(R.id.edit_text_last_name).text}"
                        addEntry(firstName,lastName)


                        val message: String = "$firstName $lastName"
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

    private fun addEntry(firstName: String, lastName: String) {
        val dbHelper = context?.let { DBHelper(it) }
        val db = dbHelper?.writableDatabase

        val values = ContentValues()
        values.put(StudentsContract.StudentEntry.COLUMN_FIRST_NAME, firstName)
        values.put(StudentsContract.StudentEntry.COLUMN_LAST_NAME, lastName)

        val newRowId = db?.insert(StudentsContract.StudentEntry.TABLE_NAME, null, values)
        Log.d("MyLog", "Data was added to database on ID($newRowId)")
    }
}