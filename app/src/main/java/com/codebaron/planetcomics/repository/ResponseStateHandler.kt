package com.codebaron.planetcomics.repository

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */
open class ResponseStateHandler<out R> {
    object Loading : ResponseStateHandler<Nothing>()
    data class Success<out T>(val data: T) : ResponseStateHandler<T>()
    data class Exception(val exception: kotlin.Exception) : ResponseStateHandler<Nothing>()
    data class ErrorMessage(val message: String?) : ResponseStateHandler<Nothing>()
    data class Throwable(val throwable: kotlin.Throwable) : ResponseStateHandler<Nothing>()
}