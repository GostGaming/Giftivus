package com.gostsoft.giftivus.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.databinding.ActivityRecipientListBinding
import com.gostsoft.giftivus.models.Recipient
import com.gostsoft.giftivus.models.db.RecipientDbTable

class RecipientListView : AppCompatActivity() {
    private lateinit var binding: ActivityRecipientListBinding
    private val TAG = RecipientListView::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipientListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipientRv = binding.recipientRv
        recipientRv.setHasFixedSize(true)
        recipientRv.layoutManager = LinearLayoutManager(this)
        recipientRv.adapter = RecipientAdapter(RecipientDbTable(this).getAllRecipients(), this)

        Log.d(TAG, "RecipientListView created")
    }
}