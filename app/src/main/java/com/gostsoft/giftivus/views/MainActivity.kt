package com.gostsoft.giftivus.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.databinding.ActivityMainBinding
import com.gostsoft.giftivus.models.db.GifteeDbTable

private lateinit var mainBinding: ActivityMainBinding
// TODO: this should open GifteeListView until we get a splash page
//
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val mainRv = mainBinding.mainRv
        mainRv.setHasFixedSize(true)
        mainRv.layoutManager = LinearLayoutManager(this)
        mainRv.adapter = GifteeAdapter(GifteeDbTable(this).getAllGiftees(), this)
        Log.d("MainActivity", "Main activity created" )
    }

}
