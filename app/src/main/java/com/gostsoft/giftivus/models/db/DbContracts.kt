package com.gostsoft.giftivus.models.db

import android.provider.BaseColumns

const val DATABASE_NAME = "giftivus.db"
const val DATABASE_VERSION = 10

object RecipientEntry : BaseColumns {
    const val TABLE_NAME = "recipients"
    const val _ID = "id"
    const val FIRSTNAME_COL = "first_name"
    const val LASTNAME_COL = "last_name"
    const val IMAGE_COL = "image"
}


object GiftEntry : BaseColumns {
    const val TABLE_NAME = "gifts"
    const val _ID = "id"
    const val NAME_COL = "name"
    const val RECIPIENT_COL = "recipient"
    const val LINK_COL = "link"
    const val QTY_COL = "qty"
    const val IMAGE_COL = "image"
}