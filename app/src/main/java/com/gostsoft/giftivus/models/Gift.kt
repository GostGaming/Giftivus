package com.gostsoft.giftivus.models

import android.graphics.Bitmap

data class Gift (val name: String, var giftee: Int = 0, val link: String = "", val quantity: Int = 1, val image: Bitmap)
 // TODO: might change link to url type