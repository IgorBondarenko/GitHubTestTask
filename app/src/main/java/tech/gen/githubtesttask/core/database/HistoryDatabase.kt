package tech.gen.githubtesttask.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.gen.githubtesttask.core.database.enteties.UserHistory

@Database(entities = [UserHistory::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun userHistoryDao(): UserHistoryDao

    companion object {

        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getDatabaseClient(context: Context): HistoryDatabase {

            return INSTANCE ?: run {
                synchronized(this) {
                    Room
                        .databaseBuilder(context, HistoryDatabase::class.java, "github-history-db")
                        .build()
                        .apply {
                            INSTANCE = this
                        }
                }
            }

        }

    }

}