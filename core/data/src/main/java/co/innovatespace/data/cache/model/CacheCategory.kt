package co.innovatespace.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CacheCategory(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0
)