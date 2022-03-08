package com.az.moviesapp.core.data.network

import com.az.moviesapp.core.data.network.model.MoviesResponse
import com.az.moviesapp.core.data.network.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.xml.xpath.XPathExpression


interface Api {

    @GET("MostPopularMovies/k_tue0gur8")
    suspend fun getMovies(): Response<MoviesResponse?>

    @GET("Search/k_tue0gur8/{expression}")
    suspend fun searchForMovie(@Path("expression") expression: String): Response<SearchResponse?>
}