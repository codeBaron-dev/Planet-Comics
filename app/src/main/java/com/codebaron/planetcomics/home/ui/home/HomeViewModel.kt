package com.codebaron.planetcomics.home.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.repository.ResponseStateHandler

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */

class HomeViewModel : ViewModel() {
    companion object {
        var homeRepository: HomeRepository? = null
        var comicDTO: MutableLiveData<ResponseStateHandler<ComicDTO?>>? = null
    }

    /**
     * @param comicId
     * @return MutableLiveData<ResponseStateHandler<[ComicDTO]?>>?
     */
    fun homeComics(comicId: String): MutableLiveData<ResponseStateHandler<ComicDTO?>>? {
        homeRepository = HomeRepository().homeRepositoryInstance()
        comicDTO = homeRepository?.homeComics(comicId)
        return comicDTO
    }
}