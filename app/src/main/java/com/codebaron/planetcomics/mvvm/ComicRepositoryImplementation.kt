package com.codebaron.planetcomics.mvvm

import com.codebaron.planetcomics.repository.ApiEndPoints
import com.codebaron.planetcomics.models.ComicDTO
import javax.inject.Inject

class ComicRepositoryImplementation @Inject constructor(private val apiEndPoints: ApiEndPoints) :
    ComicRepository {

    override suspend fun latestComicsAndSearchComics(comicId: String): ComicDTO? {
        val response = apiEndPoints.latestComicsAndSearchComics(comicId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            throw Exception()
        }
    }
}