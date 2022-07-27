package com.codebaron.planetcomics.repository.errormanager

import java.io.Serializable

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */

data class ErrorModel(
    val code: Int? = null,
    val message: String? = null,
    val resolve: String? = null
): Serializable