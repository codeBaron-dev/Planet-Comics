package com.codebaron.planetcomics.home.ui.home

import androidx.lifecycle.MutableLiveData
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.repository.BaseServices.Companion.instanceService
import com.codebaron.planetcomics.repository.ResponseStateHandler
import com.codebaron.planetcomics.repository.errormanager.ErrorMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    companion object {
        lateinit var homeRepository: HomeRepository
        var errorMapper: ErrorMapper = ErrorMapper()
    }

    fun homeRepositoryInstance(): HomeRepository {
        homeRepository = HomeRepository()
        return homeRepository
    }

    fun homeComics(comicsId: String): MutableLiveData<ResponseStateHandler<ComicDTO?>> {
        val responseState: MutableLiveData<ResponseStateHandler<ComicDTO?>> = MutableLiveData()
        responseState.postValue(ResponseStateHandler.Loading)
        val call: Call<ComicDTO>? =
            instanceService!!.apiInterface?.latestComicsAndSearchComics(comicsId)
        call?.enqueue(object : Callback<ComicDTO> {
            override fun onResponse(call: Call<ComicDTO>, response: Response<ComicDTO>) {
                try {
                    if (response.isSuccessful) {
                        val comics: ComicDTO? = response.body()
                        responseState.postValue(ResponseStateHandler.Success(comics))
                    } else {
                        val errorMessage = errorMapper.parseErrorMessage(response.errorBody())
                        responseState.postValue(ResponseStateHandler.ErrorMessage(errorMessage?.message))
                    }
                } catch (exception: Exception) {
                    responseState.postValue(ResponseStateHandler.Exception(exception))
                }
            }

            override fun onFailure(call: Call<ComicDTO>, t: Throwable) {
                responseState.postValue(ResponseStateHandler.Throwable(t))
            }
        })
        return responseState
    }
}