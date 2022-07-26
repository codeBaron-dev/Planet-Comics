package com.codebaron.planetcomics.repository.errormanager

import com.codebaron.planetcomics.repository.BaseServices
import okhttp3.ResponseBody
import retrofit2.Converter

class ErrorMapper {

    fun parseErrorMessage(response: ResponseBody?): ErrorModel? {
        val converter: Converter<ResponseBody, ErrorModel>? =
            BaseServices.retrofit()
                ?.responseBodyConverter(ErrorModel::class.java, arrayOfNulls<Annotation>(0))
        return converter?.convert(response)
    }
}