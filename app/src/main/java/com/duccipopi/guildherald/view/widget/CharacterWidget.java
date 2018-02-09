package com.duccipopi.guildherald.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.local.LocalDAO;
import com.duccipopi.guildherald.util.Utilities;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link CharacterWidgetConfigureActivity CharacterWidgetConfigureActivity}
 */
public class CharacterWidget extends AppWidgetProvider {

    private static final String SP_WIDGET = "widget_pref";
    private static final String WIDGET_PREFIX_NAME = "N:";
    private static final String WIDGET_PREFIX_REALM = "R:";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Character character) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item_character);
        WidgetCallback callback = new WidgetCallback(context, appWidgetManager, views, appWidgetId);

        if (character == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_WIDGET, Context.MODE_PRIVATE);

            if (sharedPreferences.contains(WIDGET_PREFIX_NAME + appWidgetId)) {
                LocalDAO.getInstance(context.getContentResolver()).getCharacterBaseInfo(
                        sharedPreferences.getString(WIDGET_PREFIX_NAME + appWidgetId, ""),
                        sharedPreferences.getString(WIDGET_PREFIX_REALM + appWidgetId, ""),
                        callback
                );
            }

        } else {
            // Save character infos
            LocalDAO.getInstance(context.getContentResolver()).saveCharacter(character);

            // Save base info for query
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_WIDGET, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(WIDGET_PREFIX_NAME + appWidgetId, character.getName());
            editor.putString(WIDGET_PREFIX_REALM + appWidgetId, character.getRealm());

            editor.apply();

            callback.onResponse(character);
        }

    }

    private static class WidgetCallback extends HeraldCallback<Character> {

        Context context;
        AppWidgetManager appWidgetManager;
        RemoteViews remoteViews;
        int widgetId;

        public WidgetCallback(Context context, AppWidgetManager appWidgetManager, RemoteViews remoteViews, int widgetId) {
            this.context = context;
            this.appWidgetManager = appWidgetManager;
            this.remoteViews = remoteViews;
            this.widgetId = widgetId;
        }


        @Override
        public void onResponse(Character character) {

            if (character == null) return;

            // Set Widget views
            Picasso.with(context).load(Utilities.Blizzard.getThumbnailFullURL(character.getThumbnail()))
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            remoteViews.setImageViewBitmap(R.id.portrait, bitmap);
                            appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                            remoteViews.setImageViewResource(R.id.portrait, R.drawable.error_portrait);
                            appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });

            remoteViews.setTextViewText(R.id.char_name, character.getName());
            remoteViews.setTextViewText(R.id.realm, character.getRealm());
            remoteViews.setTextViewText(R.id.race, context.getString(Utilities.Blizzard.getRaceResId(character.getRace())));
            remoteViews.setTextViewText(R.id.gender, context.getString(Utilities.Blizzard.getGenderResId(character.getGender())));
            remoteViews.setTextViewText(R.id.char_class, context.getString(Utilities.Blizzard.getClassResId(character.getCClass())));
            remoteViews.setTextViewText(R.id.level, Integer.toString(character.getLevel()));


            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }

        @Override
        public void onFailure(Character character) {

        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, null);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            // Save base info for query
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_WIDGET, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(WIDGET_PREFIX_NAME + appWidgetId);
            editor.remove(WIDGET_PREFIX_REALM + appWidgetId);

            editor.apply();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

