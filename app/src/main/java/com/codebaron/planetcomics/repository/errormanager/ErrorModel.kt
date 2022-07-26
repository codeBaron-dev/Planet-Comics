package com.codebaron.planetcomics.repository.errormanager

import java.io.Serializable

data class ErrorModel(
    val code: Int? = null,
    val message: String? = null,
    val resolve: String? = null
): Serializable