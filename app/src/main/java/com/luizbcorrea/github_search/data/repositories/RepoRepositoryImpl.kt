package com.luizbcorrea.github_search.data.repositories

import com.luizbcorrea.github_search.core.RemoteException
import com.luizbcorrea.github_search.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(
    private val service: GitHubService
    ) : RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.getListRepositories(user)
            emit(repoList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
        }
    }
}