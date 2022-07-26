package com.codebaron.planetcomics.mvvm

import com.codebaron.planetcomics.models.ComicDTO

interface ComicRepository {
    suspend fun latestComicsAndSearchComics(comicId: String): ComicDTO?
}