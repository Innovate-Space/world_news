package co.innovatespace.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source")
data class CacheSource(
    @PrimaryKey(autoGenerate = true)
    val  id: Long = 0
)
