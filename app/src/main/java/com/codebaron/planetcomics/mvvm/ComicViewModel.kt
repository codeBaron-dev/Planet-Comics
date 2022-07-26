package com.codebaron.planetcomics.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebaron.planetcomics.models.ComicDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val comicRepository: ComicRepository) :
    ViewModel() {

    private val _latestComics = MutableLiveData<ComicDTO?>()

    fun latestComicsAndSearchComics(comicId: String): LiveData<ComicDTO?> {
        viewModelScope.launch {
            _latestComics.postValue(comicRepository.latestComicsAndSearchComics(comicId))
        }
        return _latestComics
    }
}