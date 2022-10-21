package co.innovatespace.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import co.innovatespace.data.cache.model.CacheCategory

@Dao
interface CategoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CacheCategory>)
}
