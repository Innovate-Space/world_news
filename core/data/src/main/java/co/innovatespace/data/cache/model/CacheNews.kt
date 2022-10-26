package co.innovatespace.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.innovatespace.domain.model.NewsInt

@Entity(tableName = "news")
data class CacheNews(
    @PrimaryKey(autoGenerate = true)
    override val  id: Long = 0,
    override val title: String,
    override val description: String,
    override val content: String,
): NewsInt
