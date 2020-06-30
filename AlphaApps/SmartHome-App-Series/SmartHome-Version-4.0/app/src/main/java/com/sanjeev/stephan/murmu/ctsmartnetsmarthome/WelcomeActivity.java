package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * One Time Splash Welcome Screen consists of SmartHome logo plus Appname.
 *      all the 'getPreference(*) method you see below are intentionally extended by me just to explain myself what i did back then.
 *          the returned instances or boolean value can simply be written after 'equals' sign for simplicity.
 *          for more information about SharedPreferences
 *          check out : <a href="file:///E:/Android/Android%20Developer%20Guide/guide/topics/data/data-storage.html#pref">SharedPreferences</a>
 * @author Sanjeev Stephan Murmu
 * @since 19 Oct 2019
 */
public class WelcomeActivity extends AppCompatActivity
{
    SharedPreferences sharedPreferences;
    Boolean firstTime;
    String preferenceFilename = "MyFirstPrefs", nameOfPreference = "firstTime";
    long  delayTime = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getPreferenceShared(preferenceFilename,MODE_PRIVATE);

        firstTime = getPreferenceBoolean(nameOfPreference,true);

        if(firstTime)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   shareThisPreferences();
                }
            },delayTime);
        }
        else
        {
            openMainActivity();
            finish();
        }

    }

    /**
     * Retrieve a boolean value from the preferences!.
     * @param nameOfPreference The name of the preferences to retrieve.
     * @param defValue Value to return if this preference does not exits!.
     * @return Returns the preferences value if it exists or defValue.
     */
    private boolean getPreferenceBoolean(String nameOfPreference,boolean defValue)
    {
        return sharedPreferences.getBoolean(nameOfPreference,defValue);
    }

    /**
     * Retrieve and hold the contents of the preferences file 'name',
     * returning a SharedPreferences through which you can retrieve and modify its values.
     * @param name Desired preferences file.If a preference file by this name does not exists.
     *             It will be created when you call an editor(SharedPreferences.edit()).
     * @param mode Operating Mode.Use '0' or MODE_PRIVATE for the default operation,
     *             MODE_WORLD_READABLE and MODE_WORLD_WRITABLE to control permissions.
     * @return The single SharedPreferences instances that can be used to retrieve and modify the preference values.
     */
    private SharedPreferences getPreferenceShared(String name,int mode)
    {
       return getSharedPreferences(name,mode);
    }

    /**
     * Interface used for modifying values in a SharedPreferences object.
     *          All changes you make in an editor are batched,and not copied back to the original SharedPreferences
     *          until you call commit() or apply().
     *          for more <a href="file:///E:/Android/Android%20Developer%20Guide/reference/android/content/SharedPreferences.Editor.html">SharedPreferences.Editor</a>
     * @return returns a new instance of the SharedPreferences.Editor interface,allowing you to modify the values in this
     *         'sharedPreferences' object
     */
    private SharedPreferences.Editor getPreferencesEditor()
    {
        return sharedPreferences.edit();
    }
    private void shareThisPreferences()
    {
        SharedPreferences.Editor editor = getPreferencesEditor();
        firstTime = false;
        editor.putBoolean(nameOfPreference,firstTime);
        editor.apply(); //dont forget to call apply() or commit().or no changes to the 'sharedPreferences' will be saved!.

        openMainActivity();
        finish();
    }

    private void openMainActivity()
    {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
    }
}
