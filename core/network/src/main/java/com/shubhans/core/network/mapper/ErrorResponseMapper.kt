package com.shubhans.core.network.mapper

import com.shubhans.core.network.model.PokemonErrorResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.skydoves.sandwich.retrofit.statusCode

object ErrorResponseMapper : ApiErrorModelMapper<PokemonErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error): PokemonErrorResponse {
        return PokemonErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}