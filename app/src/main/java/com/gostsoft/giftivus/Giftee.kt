package com.gostsoft.giftivus

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

data class Giftee (val id: Int, val firstName: String, val lastName: String = "", val image: Bitmap = defaultBitmap()) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}


/**
 * TODO: Setup save buttons, setup edit stuff
 * setup loading of gifts for giftee. -> Where giftee.id == gift.giftee
 * put in some sort of title bar
 * change background
 * add fab buttons?
 */

fun defaultBitmap (): Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.smile)


