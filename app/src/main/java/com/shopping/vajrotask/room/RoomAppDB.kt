package com.shopping.vajrotask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartData::class], version = 2)
abstract class RoomAppDB : RoomDatabase() {
    abstract fun cartDao(): CartDao?

    companion object {
        private var INSTANCE: RoomAppDB? = null

        fun getRoomAppDB(context: Context): RoomAppDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDB>(
                    context, RoomAppDB::class.java, "AppDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }
}