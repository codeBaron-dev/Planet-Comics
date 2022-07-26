package com.codebaron.planetcomics.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.repository.ResponseStateHandler

class HomeViewModel : ViewModel() {
    companion object {
        var homeRepository: HomeRepository? = null
        var comicDTO: MutableLiveData<ResponseStateHandler<ComicDTO?>>? = null
    }

    fun homeComics(comicId: String): MutableLiveData<ResponseStateHandler<ComicDTO?>>? {
        homeRepository = HomeRepository().homeRepositoryInstance()
        comicDTO = homeRepository?.homeComics(comicId)
        return comicDTO
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}