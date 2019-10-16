package com.busbadajoz;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import com.busbadajoz.Utils.ThemeUtil;

public class BusBadajozApplication extends Application {

    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themePref", ThemeUtil.DEFAULT_MODE);
        ThemeUtil.applyTheme(themePref);
    }
}
