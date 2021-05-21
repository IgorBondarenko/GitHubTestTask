package tech.gen.githubtesttask.ui.history

import android.content.Context
import androidx.lifecycle.ViewModel
import tech.gen.githubtesttask.repository.HistoryRepository

class HistoryViewModel : ViewModel() {

    private val historyRepository by lazy { HistoryRepository() }

    fun fetchHistory(context: Context) =
        historyRepository.fetchHistory(context)

}