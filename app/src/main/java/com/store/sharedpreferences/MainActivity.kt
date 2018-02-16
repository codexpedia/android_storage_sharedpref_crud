package com.store.sharedpreferences

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        save_button.setOnClickListener(this)
        show_button.setOnClickListener(this)
        delete_button.setOnClickListener(this)
        clear_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        var message = ""
        when (v.id) {
            R.id.save_button -> {
                SavePreferences("PrefDemo", pref_editText.text.toString())
                message = "Text Saved in Preferences"
            }
            R.id.delete_button -> {
                deletePreferences("PrefDemo")
                message = "Text Deleted from Preferences"
            }
            R.id.show_button -> {
                showPreferences("PrefDemo")
                message = "Text Displayed from Preferences"
            }
            R.id.clear_button -> {
                clearAllPreferences()
                message = "Removed All Text from All Preferences"
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun SavePreferences(key: String, value: String) {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    private fun deletePreferences(key: String) {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.commit()
    }

    private fun clearAllPreferences() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    private fun showPreferences(key: String) {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val savedPref = sharedPreferences.getString(key, "")
        pref_textView.text = savedPref
    }
}
