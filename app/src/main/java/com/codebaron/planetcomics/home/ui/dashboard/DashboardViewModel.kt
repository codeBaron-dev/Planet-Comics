package com.codebaron.planetcomics.home.ui.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.roomdb.ComicRoomDatabase

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class DashboardViewModel : ViewModel() {

    fun loadLocalComics(context: Context): List<ComicDTO> {
        val localDatabase = ComicRoomDatabase(context)
        return localDatabase.ComicDao().getAllLocalComics()
    }
}