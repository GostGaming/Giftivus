package com.gostsoft.giftivus.models.db

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.gostsoft.giftivus.R
import com.gostsoft.giftivus.models.Gift

class GiftDbTable (private val context: Context) {
    private val dbHelper = GiftivusDb(context)
    private val TAG = GiftDbTable::class.simpleName

    fun storeGift (gift: Gift): Long {
        val values = ContentValues () // TODO: make sure entries are unique
        with (values) {
            put (GiftEntry.NAME_COL, gift.name)
            put (GiftEntry.RECIPIENT_COL, gift.recipient)
            put (GiftEntry.LINK_COL, gift.link)
            put (GiftEntry.QTY_COL, gift.quantity)
            put (GiftEntry.IMAGE_COL, toByteArray(gift.image))
        }
        val db = dbHelper.readableDatabase
        val id = db.transaction {
            insert(GiftEntry.TABLE_NAME, null, values)
        }
        Log.d(TAG, "Stored new gift to DB: ${gift.name}")
        return id
    }
    fun getAllGifts (): List<Gift> {
       // storeGift(fakeGift)
        var cursor: Cursor
        with (GiftEntry) {
            val columns = arrayOf(
                _ID,
                NAME_COL,
                RECIPIENT_COL,
                LINK_COL,
                QTY_COL,
                IMAGE_COL
            )
            val order = "$_ID ASC"
            val db = dbHelper.readableDatabase
            cursor = db.doQuery(TABLE_NAME, columns = columns, orderBy = order)
        }
        return parseGiftsFromCursor(cursor)

    }

    private fun parseGiftsFromCursor(cursor: Cursor): List<Gift> {
        val gifts = mutableListOf<Gift>()
        while (cursor.moveToNext ()) {
            with (GiftEntry) {
                val name = cursor.getString(NAME_COL)
                val recipient = cursor.getInt(RECIPIENT_COL)
                val link = cursor.getString(LINK_COL)
                val qty = cursor.getInt(QTY_COL)
                val bitmap = cursor.getBitmap(IMAGE_COL)
                gifts.add(Gift(name, recipient, link, qty, bitmap))
            }
        }
        cursor.close()
        return gifts
    }

    private val fakeGift = Gift("Toy", 0, "amazon.com", 1, defaultBitmap())
    private fun defaultBitmap (): Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.smile)
}

