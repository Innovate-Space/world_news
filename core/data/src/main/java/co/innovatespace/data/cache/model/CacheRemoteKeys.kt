package co.innovatespace.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys",)
data class CacheRemoteKeys( @PrimaryKey val id: Long = 1 , val nextKey: Int?, val prevKey: Int?)
