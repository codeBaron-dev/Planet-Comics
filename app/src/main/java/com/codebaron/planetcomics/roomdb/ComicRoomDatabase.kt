package com.codebaron.planetcomics.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codebaron.planetcomics.models.ComicDTO

@Database(entities = [ComicDTO::class], version = 1)
abstract class ComicRoomDatabase : RoomDatabase() {
    abstract fun ComicDao(): ComicsDao

    companion object {
        @Volatile
        private var instance: ComicRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ComicRoomDatabase::class.java, "comic_db")
                .allowMainThreadQueries()
                .build()
    }
}