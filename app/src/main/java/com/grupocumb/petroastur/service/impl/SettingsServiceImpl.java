package com.grupocumb.petroastur.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.service.SettingsService;

public class SettingsServiceImpl implements SettingsService {

    private Context context;

    public SettingsServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setSetting(String setting, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getResources().getString(R.string.SETTINGS_NAME_FILE),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(setting, value);
        editor.commit();
    }

    @Override
    public String getSetting(String setting) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(
                        context.getResources().getString(R.string.SETTINGS_NAME_FILE),
                        Context.MODE_PRIVATE);
        return sharedPreferences.getString(setting, null);
    }
}
