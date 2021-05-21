package tech.gen.githubtesttask.ui.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import tech.gen.githubtesttask.core.network.models.Repository
import tech.gen.githubtesttask.repository.SearchRepository

class SearchViewModel : ViewModel() {

    companion object {

        const val PAGE_ITEMS_LIMIT = 15

    }

    private val searchRepository by lazy { SearchRepository() }

    fun fetchUsersRepositories(context: Context, userName: String) =
        searchRepository
            .fetchUsersRepositories(context, userName, PAGE_ITEMS_LIMIT)
            .cachedIn(viewModelScope)

    fun insert(context: Context, userRepository: Repository) =
        searchRepository.addUserRepositoryToHistory(context, userRepository)

}