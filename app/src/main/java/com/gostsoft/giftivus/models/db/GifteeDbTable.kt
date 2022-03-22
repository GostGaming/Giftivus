package com.gostsoft.giftivus.models.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.gostsoft.giftivus.models.Giftee

class GifteeDbTable(context: Context) {

    private val dbHelper = GiftivusDb(context)
    val TAG = GifteeDbTable::class.simpleName

    fun storeGift (giftee: Giftee): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues ()
        with (values) {
            put (GifteeEntry.FIRSTNAME_COL, giftee.firstName)
            put (GifteeEntry.LASTNAME_COL, giftee.lastName)
            put (GifteeEntry.IMAGE_COL, toByteArray(giftee.image))
        }
        val id = db.transaction {
            insert(GifteeEntry.TABLE_NAME, null, values)
        }
        Log.d(TAG, "Stored new gift to DB: $giftee")
        return id
    }
    fun getAllGiftees (): List<Giftee> {
        var cursor: Cursor
        with (GifteeEntry) {
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

    private fun parseGiftsFromCursor(cursor: Cursor): List<Giftee> {
        val gifts = mutableListOf<Giftee>()
        while (cursor.moveToNext ()) {
            with (GifteeEntry) {
                val id = cursor.getInt(_ID)
                val firstName = cursor.getString(FIRSTNAME_COL)
                val lastName = cursor.getString(LASTNAME_COL)
                val bitmap = cursor.getBitmap(IMAGE_COL)
                gifts.add(Giftee(id, firstName, lastName, bitmap))
            }
        }
        cursor.close()

        return gifts
    }


}