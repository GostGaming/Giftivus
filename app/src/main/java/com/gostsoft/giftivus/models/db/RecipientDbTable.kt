package com.gostsoft.giftivus.models.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.gostsoft.giftivus.R
import com.gostsoft.giftivus.models.Gift
import com.gostsoft.giftivus.models.Recipient


class RecipientDbTable(private val context: Context) {

    private val dbHelper = GiftivusDb(context)
    private val TAG = RecipientDbTable::class.simpleName

    fun storeRecipient (recipient: Recipient): Long { // TODO: make sure entries are unique
        val values = ContentValues ()
        with (values) {
            put (RecipientEntry.FIRSTNAME_COL, recipient.firstName)
            put (RecipientEntry.LASTNAME_COL, recipient.lastName)
            put (RecipientEntry.IMAGE_COL, toByteArray(recipient.image))
        }
        val db = dbHelper.readableDatabase
        val id = db.transaction {
            insert(RecipientEntry.TABLE_NAME, null, values)
        }

        Log.d(TAG, "Stored new gift to DB: $recipient")
        return id
    }
    fun getAllRecipients (): List<Recipient> {
        //storeRecipient(fakeRecipient)
        var cursor: Cursor
        with (RecipientEntry) {
            val columns = arrayOf(
                _ID,
                FIRSTNAME_COL,
                LASTNAME_COL,
                IMAGE_COL
            )
            val order = "$_ID ASC"
            val db = dbHelper.readableDatabase
            cursor = db.doQuery(TABLE_NAME, columns = columns, orderBy = order)
        }
        return parseGiftsFromCursor(cursor)

    }

    private fun parseGiftsFromCursor(cursor: Cursor): List<Recipient> {
        val gifts = mutableListOf<Recipient>()
        while (cursor.moveToNext ()) {
            with (RecipientEntry) {
                val id = cursor.getInt(_ID)
                val firstName = cursor.getString(FIRSTNAME_COL)
                val lastName = cursor.getString(LASTNAME_COL)
                val bitmap = cursor.getBitmap(IMAGE_COL)
                gifts.add(Recipient(id, firstName, lastName, bitmap))
            }
        }
        cursor.close()
        if (gifts.isEmpty()) gifts.add(Recipient(1, "Brandon", "Bryant", defaultBitmap())) // TESTING ONLY
        return gifts
    }

    private val fakeRecipient = Recipient(1, "Brandon", "Bryant", defaultBitmap ())

    private fun defaultBitmap (): Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.smile)
}