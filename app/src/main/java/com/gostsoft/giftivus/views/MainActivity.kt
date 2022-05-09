package com.gostsoft.giftivus.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.databinding.ActivityMainBinding
import com.gostsoft.giftivus.models.db.GiftivusDb
import com.gostsoft.giftivus.models.db.RecipientDbTable
import com.gostsoft.giftivus.viewmodels.MainViewModel


private lateinit var mainBinding: ActivityMainBinding
// TODO: this should open RecipientListView until we get a splash page
//
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Start testing only

//        val dbHelper = GiftivusDb(this)
//        val db = dbHelper.writableDatabase
//        dbHelper.resetDb(db)

        // End testing only
        // TODO: Should transition to RecipientListView
        val mainRv = mainBinding.mainRv
        mainRv.setHasFixedSize(true)
        mainRv.layoutManager = LinearLayoutManager(this)
        mainRv.adapter = RecipientAdapter(RecipientDbTable(this).getAllRecipients(), this)
        Log.d("MainActivity", "Main activity created" )

    }
}
