package tech.gen.githubtesttask.repository

import android.content.Context
import tech.gen.githubtesttask.core.database.HistoryDatabase

class HistoryRepository {

    fun fetchHistory(context: Context) =
        HistoryDatabase
            .getDatabaseClient(context)
            .userHistoryDao()
            .getAllRepositories()

}