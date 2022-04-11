package com.gostsoft.giftivus.views

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gostsoft.giftivus.databinding.SingleRecipientBinding
import com.gostsoft.giftivus.models.Recipient

class RecipientAdapter (private val recipients: List<Recipient>, private val context: Context): RecyclerView.Adapter<RecipientAdapter.RecipientViewHolder> () {
    private val TAG = RecipientAdapter::class.simpleName
    class RecipientViewHolder(val singleRecipientBinding: SingleRecipientBinding): RecyclerView.ViewHolder(singleRecipientBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipientViewHolder {
        val binder = SingleRecipientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d(TAG, "view holder created..")
        return RecipientViewHolder(binder)
    }

    override fun onBindViewHolder(holder: RecipientViewHolder, index: Int) {
        val recipient = recipients[index]
        val card = holder.singleRecipientBinding
        card.root.setOnClickListener { openGiftList(index)}
        card.recipientImg.setImageBitmap(recipient.image)
        card.recipientName.text = recipient.toString() // Concats first and last name
    }

    private fun openGiftList(index: Int) {
        val intent = Intent(context, GiftListView::class.java).putExtra(EXTRA_ID, index)
        context.startActivity(intent)
    }


    override fun getItemCount() = recipients.size

}