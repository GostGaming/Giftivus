package com.gostsoft.giftivus.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gostsoft.giftivus.databinding.ActivityRecipientListBinding
import com.gostsoft.giftivus.models.Recipient
import com.gostsoft.giftivus.models.db.RecipientDbTable
import com.gostsoft.giftivus.viewmodels.MainViewModel
import com.gostsoft.giftivus.viewmodels.RecipientListViewModel

class RecipientListView : AppCompatActivity() {
    private lateinit var binding: ActivityRecipientListBinding
    private val TAG = RecipientListView::class.simpleName
    private val viewModel: RecipientListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipientListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipientRv = binding.recipientRv
        recipientRv.setHasFixedSize(true)
        recipientRv.layoutManager = LinearLayoutManager(this)
        recipientRv.adapter = RecipientAdapter(RecipientDbTable(this).getAllRecipients(), this)

        Log.d(TAG, "RecipientListView created")
        // TODO: need to add FAB and hook it up
    }
}