package co.innovatespace.data.cache.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.domain.model.Source

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sources: List<Source>)


    @Query("DELETE FROM source")
    suspend fun clearAll()

    @Query("SELECT * FROM source")
    fun selectAll(): PagingSource<Int, Source>
}