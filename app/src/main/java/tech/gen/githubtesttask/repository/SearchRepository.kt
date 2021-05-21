package tech.gen.githubtesttask.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import tech.gen.githubtesttask.core.database.HistoryDatabase
import tech.gen.githubtesttask.core.database.enteties.UserHistory
import tech.gen.githubtesttask.core.network.models.Repository
import tech.gen.githubtesttask.data.UserSearchDataSource

class SearchRepository {

    private fun getDatabase(context: Context) =
        HistoryDatabase
            .getDatabaseClient(context)

    fun fetchUsersRepositories(context: Context, userName: String, perPage: Int) =
        Pager(
            pagingSourceFactory = { UserSearchDataSource(userName, getDatabase(context)) },
            config = PagingConfig(pageSize = perPage)
        ).flow

    fun addUserRepositoryToHistory(context: Context, repository: Repository) =
        getDatabase(context)
            .userHistoryDao()
            .insert(
                UserHistory(
                    id = repository.id,
                    name = repository.name,
                    htmlUrl = repository.htmlUrl,
                    language = repository.language,
                    stargazersCount = repository.stargazersCount
                )
            )

}