package co.innovatespace.data.cache.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.domain.model.NewsInt

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsList: List<CacheNews>)

    @Query("DELETE FROM news")
    suspend fun clearAll()

    @Query("SELECT * FROM news")
    fun selectAll(): PagingSource<Int, CacheNews>
}
// added