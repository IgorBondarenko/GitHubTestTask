package tech.gen.githubtesttask.core.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import tech.gen.githubtesttask.core.network.models.RepositorySearchResult

interface ServerApi {

    companion object {

        const val BASE_URL = "https://api.github.com"

    }

    @GET("/search/repositories")
    fun fetchUsersRepositoriesSingle(
        @Query("q") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String = "stars",
    ): Single<RepositorySearchResult>

}