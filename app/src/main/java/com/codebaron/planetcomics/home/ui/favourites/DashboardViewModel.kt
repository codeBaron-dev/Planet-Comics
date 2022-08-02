package com.codebaron.planetcomics.home.ui.favourites

import android.content.Context
import androidx.lifecycle.ViewModel
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.roomdb.ComicRoomDatabase

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class DashboardViewModel : ViewModel() {

    /**
     * this function retrieves comics data from local database
     * @param context
     */
    fun loadLocalComics(context: Context): List<ComicDTO> {
        val localDatabase = ComicRoomDatabase(context)
        return localDatabase.comicDao().getAllLocalComics()
    }
}