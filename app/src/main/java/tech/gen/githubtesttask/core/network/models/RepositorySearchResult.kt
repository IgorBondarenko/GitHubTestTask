package tech.gen.githubtesttask.core.network.models

import com.google.gson.annotations.SerializedName

data class RepositorySearchResult(
    @SerializedName("items") val items: List<Repository>
)

data class Repository(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("language") val language: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    var isWatched: Boolean = false
)




