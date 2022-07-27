package com.codebaron.comicapp.repository

import com.codebaron.planetcomics.models.ComicDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */
interface ApiEndPoints {

    @GET(SEARCH_COMIC)
    fun latestComicsAndSearchComics(@Path("comicId") comicId: String): Call<ComicDTO>
}