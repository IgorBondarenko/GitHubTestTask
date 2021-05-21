package tech.gen.githubtesttask.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.GitHubApplication
import tech.gen.githubtesttask.core.database.HistoryDatabase
import tech.gen.githubtesttask.core.database.enteties.UserHistory
import tech.gen.githubtesttask.core.network.models.Repository

class UserSearchDataSource(private val username: String, val database: HistoryDatabase) : RxPagingSource<Int, Repository>() {

    companion object {

        private const val INITIAL_PAGE = 1

    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(2)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(2)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Repository>> {

        return try {

            val page = params.key ?: INITIAL_PAGE

            Single.zip(
                GitHubApplication
                    .serverApi
                    .fetchUsersRepositoriesSingle(username, params.loadSize, page)
                    .subscribeOn(Schedulers.io()),
                GitHubApplication
                    .serverApi
                    .fetchUsersRepositoriesSingle(username, params.loadSize, page + 1)
                    .subscribeOn(Schedulers.io()),
                database.userHistoryDao().getAllRepositoriesSingle().subscribeOn(Schedulers.io()),
                { source1, source2, databaseSource ->
                    checkIsWatched(combineLists(source1.items, source2.items), databaseSource)
                }
            )
                .map {
                    LoadResult.Page(
                        data = it,
                        prevKey = if (page == INITIAL_PAGE) null else page - 2,
                        nextKey = if (it.isEmpty()) null else page + 2
                    )
                }

        } catch (e: Exception) {
            Single.just(LoadResult.Error(e))
        }
    }

    private fun <T> combineLists(vararg lists: List<T>): List<T> {
        val combinedList = mutableListOf<T>()
        lists.forEach {
            combinedList.addAll(it)
        }
        return combinedList
    }

    private fun checkIsWatched(
        listOfRepo: List<Repository>,
        listOfHistory: List<UserHistory>
    ): List<Repository> {
        listOfRepo.forEach { repository ->
            listOfHistory.forEach { history ->
                if (repository.id == history.id) repository.isWatched = true
            }
        }
        return listOfRepo
    }

}