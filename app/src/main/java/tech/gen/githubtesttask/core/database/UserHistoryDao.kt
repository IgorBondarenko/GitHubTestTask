package tech.gen.githubtesttask.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import tech.gen.githubtesttask.core.database.enteties.UserHistory

@Dao
interface UserHistoryDao {

    companion object {

        const val HISTORY_LIMIT = 20

    }

    @Insert
    fun insert(userHistory: UserHistory): Completable

    @Query("SELECT * FROM userHistory LIMIT $HISTORY_LIMIT")
    fun getAllRepositories(): Observable<List<UserHistory>>

    @Query("SELECT * FROM userHistory LIMIT $HISTORY_LIMIT")
    fun getAllRepositoriesSingle(): Single<List<UserHistory>>

}