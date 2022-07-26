package com.codebaron.planetcomics.repository

import com.codebaron.planetcomics.models.ComicDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    @GET(SEARCH_COMIC)
    suspend fun latestComicsAndSearchComics(@Path("comicId") comicId: String): Response<ComicDTO>
}