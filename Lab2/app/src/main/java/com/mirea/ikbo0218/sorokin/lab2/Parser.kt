package com.mirea.ikbo0218.sorokin.lab2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


//https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
class Parser {
    private val json_file_url =
        "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"
    private val image_url_string =
        "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
    private var jsonElementsList: ArrayList<JsonElem> = ArrayList()
    private var imgElementsList: ArrayList<Bitmap> = ArrayList()

    data class JsonElem(    // class for parsing json file by using gson lib from google
        val graphic: String,
        val graphic_alt: String,
        val name: String,
        val req1: String,
        val req2: String
    )


    private fun getJSON(): String {
        val badJSON = URL(json_file_url).readText()
        return "[" + badJSON.substringAfter("},")   //goodJSON
    }

    fun getElements(): ArrayList<JsonElem> {
        return jsonElementsList
    }

    fun getImages(): ArrayList<Bitmap> {
        return imgElementsList
    }

    fun downloadJSONs() {
        val json = getJSON()
        val gson = Gson()
        val sType = object : TypeToken<ArrayList<JsonElem>>() {}.type
        jsonElementsList =
            gson.fromJson<ArrayList<JsonElem>>(json, sType)  // Filling the list of Json Elements
        Log.d("DOWNLOAD_TEST", "OK_JSON")
    }


    //Long time downloading. Need to be fixed. May be new implementation of downloading
    fun downloadPictures() {
        for (i in jsonElementsList.indices) {
            val name: String = jsonElementsList[i].graphic
            val img: Bitmap = getImage(name)
            imgElementsList.add(img)
        }
        Log.d("DOWNLOAD_TEST", "OK_PICTURES")
    }

    private fun getImage(imageID: String): Bitmap {  // imageID would be taken from JsonElem.nam
        try {
            Log.d("DOWNLOAD_TEST", "$imageID downloaded")
            return BitmapFactory.decodeStream(
                URL("$image_url_string$imageID").openStream()
            )

        } catch (e: Exception) {
            Log.d("DOWNLOAD_TEST", "advanced_flight set")
            return BitmapFactory.decodeStream(
                URL("${image_url_string}advanced_flight.jpg").openStream()//openConnection().getInputStream()
            )
        }
    }

}