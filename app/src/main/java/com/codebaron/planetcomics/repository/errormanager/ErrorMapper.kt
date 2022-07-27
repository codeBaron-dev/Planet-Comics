package com.codebaron.planetcomics.repository.errormanager

import com.codebaron.planetcomics.repository.BaseServices
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */

/**
 * this class works like an interpreter, it receives the [ErrorBody] returned from
 * from a network request and maps it to a data class [ErrorModel] which then makes
 * it readable and accessible for both developer and user
 */
class ErrorMapper {

    fun parseErrorMessage(response: ResponseBody?): ErrorModel? {
        val converter: Converter<ResponseBody, ErrorModel>? =
            BaseServices.retrofit()
                ?.responseBodyConverter(ErrorModel::class.java, arrayOfNulls<Annotation>(0))
        return converter?.convert(response)
    }
}