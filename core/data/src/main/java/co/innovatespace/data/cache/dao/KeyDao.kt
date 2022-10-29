package co.innovatespace.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheRemoteKeys

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cachedKey: CacheRemoteKeys)

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()

    @Query("SELECT * FROM remote_keys where id = :id ")
    suspend fun getKey(id: Long): CacheRemoteKeys?
}