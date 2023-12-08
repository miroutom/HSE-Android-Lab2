package com.example.lab2

import android.content.Context

class DataSource(private val context: Context) {

    fun loadItems(): MutableList<ShoppingItem>{
        return mutableListOf(
            ShoppingItem(context.getString(R.string.item1)),
            ShoppingItem(context.getString(R.string.item2)),
            ShoppingItem(context.getString(R.string.item3)),
            ShoppingItem(context.getString(R.string.item4)),
            ShoppingItem(context.getString(R.string.item5))
        )
    }
}
