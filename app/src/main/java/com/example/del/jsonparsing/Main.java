package com.example.del.jsonparsing;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by del on 5/8/2016.
 */
public class Main extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i=0;i<appWidgetIds.length;i++)
        {
            int appWidgetId=appWidgetIds[i];
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(""));
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);

            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.main);
            views.setOnClickPendingIntent(R.id.image,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,views);



        }
    }
}
