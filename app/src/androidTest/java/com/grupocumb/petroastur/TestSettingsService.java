package com.grupocumb.petroastur;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.grupocumb.petroastur.service.SettingsService;
import com.grupocumb.petroastur.service.impl.SettingsServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestSettingsService {
    SettingsService settingsService;
    Context mockContext;

    @Before
    public void load() {
        mockContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        settingsService = new SettingsServiceImpl(mockContext);

        //RESET PREFERENCES FOR TEST
        mockContext.getSharedPreferences(
                mockContext.getResources().getString(R.string.SETTINGS_NAME_FILE),
                Context.MODE_PRIVATE).edit().clear().commit();
    }

    @Test
    public void testUpdateService() throws InterruptedException {
        //probamos a meter y quitar todas las settings
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_ID_FAVOURITE_ES)), null);
        settingsService.setSetting(
                mockContext.getString(R.string.SETTINGS_ID_FAVOURITE_ES), "1");
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_ID_FAVOURITE_ES)), "1");

        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_LAST_UPDATE)), null);
        settingsService.setSetting(
                mockContext.getString(R.string.SETTINGS_LAST_UPDATE), "1");
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_LAST_UPDATE)), "1");

        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_NAME_FAVOURITE_FUEL)), null);
        settingsService.setSetting(
                mockContext.getString(R.string.SETTINGS_NAME_FAVOURITE_FUEL), "1");
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_NAME_FAVOURITE_FUEL)), "1");

        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_ORDER_LIST)), null);
        settingsService.setSetting(
                mockContext.getString(R.string.SETTINGS_ORDER_LIST), "1");
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_ORDER_LIST)), "1");

        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_DISTANCE_MAX)), null);
        settingsService.setSetting(
                mockContext.getString(R.string.SETTINGS_DISTANCE_MAX), "1");
        assertEquals(settingsService.getSetting(
                mockContext.getString(R.string.SETTINGS_DISTANCE_MAX)), "1");
    }
}
