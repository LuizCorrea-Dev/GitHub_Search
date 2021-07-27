package com.luizbcorrea.github_search.data.repositories

import com.luizbcorrea.github_search.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}