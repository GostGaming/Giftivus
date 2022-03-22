package com.gostsoft.giftivus.models.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class GiftivusDb (context: Context): SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION){

    private val SQL_CREATE_GIFTEE_ENTRIES = "CREATE TABLE ${GifteeEntry.TABLE_NAME} (" +
            "${GifteeEntry._ID} INTEGER PRIMARY KEY," +
            "${GifteeEntry.FIRSTNAME_COL} TEXT," +
            "${GifteeEntry.LASTNAME_COL} TEXT," +
            "${GifteeEntry.IMAGE_COL} BLOB" +
            ")"

    private val SQL_CREATE_GIFT_ENTRIES = "CREATE TABLE ${GiftEntry.TABLE_NAME} (" +
            "${GiftEntry._ID} INTEGER PRIMARY KEY," +
            "${GiftEntry.NAME_COL} TEXT," +
            "${GiftEntry.GIFTEE_COL} INTEGER," +
            "${GiftEntry.LINK_COL} TEXT," +
            "${GiftEntry.QTY_COL} INTEGER," +
            "${GiftEntry.IMAGE_COL} BLOB" +
            ")"

    private val SQL_DELETE_GIFTEE_ENTRIES = "DROP TABLE IF EXISTS ${GifteeEntry.TABLE_NAME}"
    private val SQL_DELETE_GIFT_ENTRIES = "DROP TABLE IF EXISTS ${GiftEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_GIFTEE_ENTRIES)
        db.execSQL(SQL_CREATE_GIFT_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_GIFTEE_ENTRIES)
        db.execSQL(SQL_DELETE_GIFT_ENTRIES)
        onCreate(db)
    }

}

fun SQLiteDatabase.doQuery(table: String, columns: Array<String>, selection: String? = null,
                            selectionArgs: Array<String>? = null, groupBy: String? = null,
                            having: String? = null, orderBy: String? = null): Cursor {
    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}


fun Cursor.getString (columnName: String) = getString (getColumnIndexOrThrow(columnName))

fun Cursor.getBitmap (columnName: String): Bitmap {
    val byteArray = getBlob (this.getColumnIndexOrThrow(columnName))
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}
fun Cursor.getInt (columnName: String) = getInt (getColumnIndexOrThrow(columnName))


inline fun <T> SQLiteDatabase.transaction (function: SQLiteDatabase.() -> T): T {
    beginTransaction ()
    val result = try {
        val returnValue = function ()
        setTransactionSuccessful()
        returnValue
    } finally {
        endTransaction()
    }
    close()
    return result
}
fun toByteArray (bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream ()
    bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
    return stream.toByteArray()
}