package com.gostsoft.giftivus.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.databinding.ActivityMainBinding
import com.gostsoft.giftivus.models.db.GiftivusDb
import com.gostsoft.giftivus.models.db.RecipientDbTable

private lateinit var mainBinding: ActivityMainBinding
// TODO: this should open RecipientListView until we get a splash page
//
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        val dbHelper = GiftivusDb(this)
//        val db = dbHelper.writableDatabase
//        dbHelper.resetDb(db)

        val mainRv = mainBinding.mainRv
        mainRv.setHasFixedSize(true)
        mainRv.layoutManager = LinearLayoutManager(this)
        mainRv.adapter = RecipientAdapter(RecipientDbTable(this).getAllRecipients(), this)
        Log.d("MainActivity", "Main activity created" )

    }

}
