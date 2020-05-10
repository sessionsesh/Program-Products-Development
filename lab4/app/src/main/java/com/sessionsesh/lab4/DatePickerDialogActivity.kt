package com.sessionsesh.lab4

import android.app.*
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DatePickerDialogActivity : AppCompatActivity() {
    private var appWidgetId: Int = intent?.extras?.getInt(
       "id"
    ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

    /*Elements*/
    private lateinit var datePicker: CalendarView
    private lateinit var timePicker: TimePicker
    private lateinit var buttonSet: Button
    private lateinit var buttonSwitch: Button

    /*For datetime handle*/
    private lateinit var calendar: Calendar

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        /*Creating dialog from activity*/
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.date_picker_activity)
        this.setFinishOnTouchOutside(false)

        appWidgetId = intent?.extras?.getInt(
        "id"
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        setResult(Activity.RESULT_OK, null)
        /*Creating notification channel*/
        val channel = NotificationChannel(
            "notifyLab",
            "Notification Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)

        /*Getting elements from layout*/
        datePicker = findViewById(R.id.date_picker)
        timePicker = findViewById(R.id.time_picker)
        buttonSet = findViewById(R.id.button_set)
        buttonSwitch = findViewById(R.id.button_switch)

        calendar = Calendar.getInstance()

        /*Invoking listeners*/
        dateChangeListener()
        timeChangeListener()
        buttonSetListener()
        buttonSwitchListener()
    }

    private fun share(diffTime: Long) {
        val sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE)
        sharedPref.edit().putLong("date", diffTime).apply()
    }


    // Here invoked alarm and notification methods
    private fun buttonSetListener() {
        buttonSet.setOnClickListener {
            /*Creating notification*/
            val intent = Intent(this, NotificationBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            /*Reminder timing*/
            val setTime = System.currentTimeMillis() //time, when reminder was set
            val diffTime: Long =
                calendar.timeInMillis - System.currentTimeMillis() //difference between chosen time and current

            //If time difference is negative, say this.
            //Else say remaining seconds to reminder
            if (diffTime < 0) {
                Toast.makeText(
                    this,
                    "Cannot set reminder on the past",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                alarmManager.set(//Setting notification
                    AlarmManager.RTC_WAKEUP,
                    setTime + diffTime, pendingIntent
                )
                Toast.makeText(
                    this,
                    "Reminder will ring after ${diffTime / 1000L} seconds",
                    Toast.LENGTH_SHORT
                ).show()

                //Share time
                share(diffTime)

                val resultValue =
                    Intent().apply {
                        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                    }

                val appWidgetManager = AppWidgetManager.getInstance(this)
                val views = RemoteViews(applicationContext.packageName, R.layout.widget)
                views.setTextViewText(R.id.days, "${diffTime / 1000 / 60 / 60 / 24}")

                val context = applicationContext
                val name = ComponentName(context, MyAppWidgetProvider::class.java)
                val ids =
                    AppWidgetManager.getInstance(context).getAppWidgetIds(name)

                appWidgetManager.updateAppWidget(ids[0], views)

                setResult(Activity.RESULT_OK, resultValue)
                MyAppWidgetProvider().onUpdate(this, appWidgetManager, IntArray(1){37})
                finish()
            }
        }
    }


    private fun dateChangeListener() {
        datePicker.setOnDateChangeListener { _, year, monthOfYear, dayOfMonth ->
            calendar.apply {
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.YEAR, year)
                Log.d("SWITCH_CHECK", "date")
            }
        }
    }

    private fun timeChangeListener() {
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }
    }


    private fun buttonSwitchListener() {
        buttonSwitch.setOnClickListener {
            // If time not picked, show TimePicker
            // Else show DatePicker
            if (buttonSet.visibility == View.GONE) {
                buttonSwitch.text = "Change Date"
                buttonSet.visibility = View.VISIBLE
                datePicker.visibility = View.GONE
                timePicker.visibility = View.VISIBLE
            } else {
                buttonSwitch.text = "Change Time"
                buttonSet.visibility = View.GONE
                datePicker.visibility = View.VISIBLE
                timePicker.visibility = View.GONE
            }

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}





