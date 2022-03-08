package com.gostsoft.giftivus.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.gostsoft.giftivus.Gift

class GiftDbTable (context: Context) {
    private val dbHelper = GiftivusDb(context)
    val TAG = GiftDbTable::class.simpleName

    fun storeGift (gift: Gift): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues ()
        with (values) {
            put (GiftEntry.NAME_COL, gift.name)
            put (GiftEntry.GIFTEE_COL, gift.giftee)
            put (GiftEntry.LINK_COL, gift.link)
            put (GiftEntry.QTY_COL, gift.quantity)
            put (GiftEntry.IMAGE_COL, toByteArray(gift.image))
        }
        val id = db.transaction {
            insert(GiftEntry.TABLE_NAME, null, values)
        }
        Log.d(TAG, "Stored new gift to DB: ${gift.name}")
        return id
    }
    fun getAllGifts (): List<Gift> {
        var cursor: Cursor
        with (GiftEntry) {
            val columns = arrayOf(
                _ID,
                NAME_COL,
                GIFTEE_COL,
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
                val giftee = cursor.getInt(GIFTEE_COL)
                val link = cursor.getString(LINK_COL)
                val qty = cursor.getInt(QTY_COL)
                val bitmap = cursor.getBitmap(IMAGE_COL)
                gifts.add(Gift(name, giftee, link, qty, bitmap))
            }
        }
        cursor.close()

        return gifts
    }
}

