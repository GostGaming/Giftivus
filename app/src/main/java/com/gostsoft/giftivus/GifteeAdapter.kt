package com.gostsoft.giftivus

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.gostsoft.giftivus.databinding.SingleGifteeBinding

class GifteeAdapter (val giftees: List<Giftee>, val context: Context): RecyclerView.Adapter<GifteeAdapter.GifteeViewHolder> () {
    val TAG = GifteeAdapter::class.simpleName
    class GifteeViewHolder(val singleGifteeBinding: SingleGifteeBinding): RecyclerView.ViewHolder(singleGifteeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifteeViewHolder {
        val binder = SingleGifteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d(TAG, "view holder created..")
        return GifteeViewHolder(binder)
    }

    override fun onBindViewHolder(holder: GifteeViewHolder, index: Int) {
        val giftee = giftees[index]
        val card = holder.singleGifteeBinding
        card.root.setOnClickListener { openGiftList(index)}
        card.gifteeImg.setImageBitmap(giftee.image)
        card.gifteeName.text = giftee.toString() // Concats first and last name
    }

    private fun openGiftList(index: Int) {
        val intent = Intent(context, UserGiftList::class.java).putExtra(EXTRA_ID, index)
        context.startActivity(intent)
    }


    override fun getItemCount() = giftees.size

}