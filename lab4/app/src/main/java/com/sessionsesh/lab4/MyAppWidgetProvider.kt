package com.sessionsesh.lab4

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

class MyAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds?.forEach { appWidgetId ->
            // Create an Intent to launch ExampleActivity
            val pendingIntent: PendingIntent = Intent(context, DatePickerDialogActivity::class.java)
                .let { intent ->
                    intent.putExtra("id", appWidgetId)
                    Log.e("provider","${appWidgetId}")
                    PendingIntent.getActivity(context, 0, intent, 0)
                }

            // Get the layout for the App Widget and attach an on-click listener
            val views: RemoteViews = RemoteViews(
                context?.packageName,
                R.layout.widget
            ).apply {
                setOnClickPendingIntent(R.id.time_layout, pendingIntent)
                val sharedPref = context!!.getSharedPreferences("PREF", Context.MODE_PRIVATE)
                val diffTime: Long = sharedPref!!.getLong("date", -1)
                setTextViewText(R.id.days, "${diffTime / 1000 / 60 / 60 / 24} days")
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}