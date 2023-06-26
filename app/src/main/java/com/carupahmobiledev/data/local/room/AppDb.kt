package com.carupahmobiledev.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carupahmobiledev.data.remote.response.ChatbotResponse

/*@Database(
    entities = [ChatbotResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun chatDao() : ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        @JvmStatic
        fun getInstance(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java, "stories_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}*/