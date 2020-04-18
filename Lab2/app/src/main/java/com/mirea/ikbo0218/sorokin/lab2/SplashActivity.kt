package com.mirea.ikbo0218.sorokin.lab2

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import java.io.Serializable


class SplashActivity : AppCompatActivity(), Serializable {
    val firstTime: Long = System.currentTimeMillis()
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
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            val image_url_string =
                "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"

            super.onPostExecute(result)
            val elemsArrayList = pr.getElements()
            val resultArrayList = ArrayList<Element>()

            for (i in elemsArrayList.indices) {
                resultArrayList.add(
                    Element(
                        elemsArrayList[i].name,
                        "$image_url_string${elemsArrayList[i].graphic}"
                    )
                )
            }


            val i = Intent(this@SplashActivity, MainActivity::class.java)
            i.putExtra("serializableArrayList", resultArrayList)
            startActivity(i)
            Toast.makeText(applicationContext, "Loading time: ${System.currentTimeMillis() - firstTime} ms", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}


