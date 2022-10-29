package co.innovatespace.data.di

import android.content.Context
import androidx.room.Room
import co.innovatespace.data.cache.Cache
import co.innovatespace.data.cache.RoomCache
import co.innovatespace.data.cache.RoomDbEngine
import co.innovatespace.data.cache.dao.CategoryDao
import co.innovatespace.data.cache.dao.KeyDao
import co.innovatespace.data.cache.dao.NewsDao
import co.innovatespace.data.cache.dao.SourceDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object{

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): RoomDbEngine {
            return Room.databaseBuilder(context, RoomDbEngine::class.java,"news_app.db").build()
        }

        @Provides
        fun provideNewsDao(roomDatabaseEngine: RoomDbEngine): NewsDao = roomDatabaseEngine.newsDao()

        @Provides
        fun provideCategoryDao(roomDatabaseEngine: RoomDbEngine): CategoryDao = roomDatabaseEngine.categoryDao()

        @Provides
        fun provideSourceDao(roomDatabaseEngine: RoomDbEngine): SourceDao = roomDatabaseEngine.sourceDao()

        @Provides
        fun provideKeyDao(roomDatabaseEngine: RoomDbEngine): KeyDao = roomDatabaseEngine.keyDao()
    }
}