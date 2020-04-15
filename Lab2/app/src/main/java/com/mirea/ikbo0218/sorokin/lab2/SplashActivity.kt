package com.mirea.ikbo0218.sorokin.lab2

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import kotlin.reflect.typeOf


class SplashActivity : AppCompatActivity() {

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        DownloadData().execute(null, null, null)
    }

    inner class DownloadData() : AsyncTask<Void, Void, Void>() {
        private val pr = Parser()
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: Void?): Void? {
            pr.downloadJSONs()
            pr.downloadPictures()
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val elemsArrayList = pr.getElements()
            val imgArrayList = pr.getImages()

            val resultArrayList = ArrayList<Element>()
            for (i in elemsArrayList.indices) {
                resultArrayList.add(Element(imgArrayList[i], elemsArrayList[i].name))
            }

            val extra = Bundle()
            extra.putSerializable("elemetns", resultArrayList)

            val i = Intent(this@SplashActivity, MainActivity::class.java)
            i.putExtra("extra", extra)
            startActivity(i)
        }
    }
}


