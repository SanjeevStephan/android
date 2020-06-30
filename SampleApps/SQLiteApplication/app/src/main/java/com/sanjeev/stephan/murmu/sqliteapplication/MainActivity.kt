package com.sanjeev.stephan.murmu.sqliteapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Kotlin Android SQLite
 * @see https://www.javatpoint.com/kotlin-android-sqlite-tutorial
 *
 * SQLite is an open-source relational database that is used to perform database operations
 * on Android devices such as storing,
 * manipulating or retrieving persistent data from the database.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
