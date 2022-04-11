package com.gostsoft.giftivus.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.models.Gift
import com.gostsoft.giftivus.R
import com.gostsoft.giftivus.databinding.ActivityGiftListBinding
import com.gostsoft.giftivus.models.db.GiftDbTable

const val EXTRA_ID = "com.gostsoft.giftivus.USERID"

class GiftListView : AppCompatActivity()  {

    private lateinit var binding: ActivityGiftListBinding
    private val TAG = GiftListView::class.simpleName

    val giftList = mutableListOf<Gift>()

    private var userId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.extras?.getInt(EXTRA_ID) ?: 0

        val giftRv = binding.giftRv
        giftRv.setHasFixedSize(true)
        giftRv.layoutManager = LinearLayoutManager(this)
        giftRv.adapter = GiftAdapter(GiftDbTable(this).getAllGifts(), this)

       // updateGifts ()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)  // TODO: hook up to addNewGift
        }
    }

    fun updateGifts () {

        for (gift in giftList) {
            val giftLayout: ConstraintLayout = layoutInflater.inflate(R.layout.single_gift,null) as ConstraintLayout
            val giftBar = giftLayout.findViewById<LinearLayout>(R.id.gift_bar)
            val qty = giftBar.findViewById<TextView>(R.id.gift_qty)
            val name = giftBar.findViewById<TextView>(R.id.gift_name)
            val link = giftBar.findViewById<TextView>(R.id.gift_link)
            qty.text = gift.quantity.toString()// TODO: FIx this!
            name.text = gift.name
            link.text = gift.link
        }
    }
    fun addNewGift () {
        // TODO: For adding new gifts
    }

    fun saveCurrentGift () {
        // TODO: for editing existing gifts
    }
}