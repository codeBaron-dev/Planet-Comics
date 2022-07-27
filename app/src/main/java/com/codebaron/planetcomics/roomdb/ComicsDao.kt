package com.codebaron.planetcomics.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codebaron.planetcomics.models.ComicDTO

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */
@Dao
interface ComicsDao {

    @Query("SELECT * FROM comics_table")
    fun getAllLocalComics(): List<ComicDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComic(news: ComicDTO)

    @Query("DELETE FROM comics_table")
    fun deleteAll()
}