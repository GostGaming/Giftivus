package com.gostsoft.giftivus.views

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gostsoft.giftivus.databinding.SingleGiftBinding
import com.gostsoft.giftivus.databinding.SingleRecipientBinding
import com.gostsoft.giftivus.models.Gift

class GiftAdapter (private val gifts: List<Gift>, private val context: Context): RecyclerView.Adapter<GiftAdapter.GiftViewHolder> () {
    private val TAG = GiftAdapter::class.simpleName

    class GiftViewHolder(val singleGiftBinding: SingleGiftBinding) :
        RecyclerView.ViewHolder(singleGiftBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
        val binder = SingleGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d(TAG, "view holder created..")
        return GiftViewHolder(binder)
    }

    override fun onBindViewHolder(holder: GiftViewHolder, index: Int) {
        val gift = gifts[index]
        val card = holder.singleGiftBinding
        //card.root.setOnClickListener { openSingleGift(index) }
        card.giftImage.setImageBitmap(gift.image)
        card.giftQty.text = gift.quantity.toString()
        card.giftName.text = gift.toString() // Concats first and last name
        card.giftLink.text = gift.link
    }

    private fun openSingleGift(index: Int) {
//        val intent = Intent(context, SingleGiftView::class.java).putExtra(EXTRA_ID, index)
//        context.startActivity(intent)
        // TODO: Option to delete or edit?
    }


    override fun getItemCount() = gifts.size
}