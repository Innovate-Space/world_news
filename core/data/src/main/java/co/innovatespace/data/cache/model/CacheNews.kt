package co.innovatespace.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class CacheNews(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0
)
