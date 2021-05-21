package tech.gen.githubtesttask.core.database.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userHistory")
data class UserHistory(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "html_url") val htmlUrl: String,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Int,
)