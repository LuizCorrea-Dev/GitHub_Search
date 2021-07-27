package com.luizbcorrea.github_search.domain

import com.luizbcorrea.github_search.core.UseCase
import com.luizbcorrea.github_search.data.model.Repo
import com.luizbcorrea.github_search.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(

    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}