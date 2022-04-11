package com.gostsoft.giftivus.models

import android.graphics.Bitmap

/**
 * TODO: Setup save buttons, setup edit stuff
 * setup loading of gifts for Recipient. -> Where Recipient.id == gift.Recipient
 * put in some sort of title bar
 * change background
 * add fab buttons?
 */

data class Recipient (val id: Int, val firstName: String, val lastName: String = "", var image: Bitmap) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}

