package com.luizbcorrea.github_search.data.services

import com.luizbcorrea.github_search.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun getListRepositories(@Path("user") user: String) : List<Repo>


}