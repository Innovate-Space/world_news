package co.innovatespace.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import co.innovatespace.data.cache.dao.CategoryDao
import co.innovatespace.data.cache.dao.KeyDao
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.dao.SourceDao
import co.innovatespace.data.cache.model.CacheCategory
import co.innovatespace.data.cache.model.CacheNews
import co.innovatespace.data.cache.model.CacheRemoteKeys
import co.innovatespace.domain.model.Source

@Database(
    entities = [CacheNews::class, CacheCategory::class, Source::class, CacheRemoteKeys::class ],
    version = 1
)
abstract class RoomDbEngine: RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun sourceDao(): SourceDao
    abstract fun categoryDao(): CategoryDao
    abstract fun keyDao(): KeyDao
}