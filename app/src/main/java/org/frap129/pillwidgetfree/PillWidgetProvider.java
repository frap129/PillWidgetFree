package org.frap129.pillwidget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PillWidgetProvider extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        PackageManager pm = context.getPackageManager();
        String packageName = "com.google.android.googlequicksearchbox";
        for(int j = 0; j < appWidgetIds.length; j++)
        {
            int appWidgetId = appWidgetIds[j];

            try {
                Intent intent = pm.getLaunchIntentForPackage(packageName);
                intent.addCategory("android.intent.category.LAUNCHER");
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context, 0, intent, 0);
                RemoteViews views = new RemoteViews(context.getPackageName(),
                        R.layout.pill);
                views.setOnClickPendingIntent(R.id.imageView, pendingIntent);
                appWidgetManager.updateAppWidget(appWidgetId, views);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context.getApplicationContext(),
                        "Please install the Google app",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}
