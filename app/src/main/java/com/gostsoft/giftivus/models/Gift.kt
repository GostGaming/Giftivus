package com.gostsoft.giftivus.models

import android.graphics.Bitmap

data class Gift (val name: String, var recipient: Int = 0, val link: String = "", val quantity: Int = 1, var image: Bitmap) {
    override fun toString(): String {
        return name // TODO: Might change this to some combination of recipient and name?
    }
}
 // TODO: might change link to url type

