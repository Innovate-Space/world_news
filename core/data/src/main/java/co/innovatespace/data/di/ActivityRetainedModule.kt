package co.innovatespace.data.di

import co.innovatespace.data.NewsRepository
import co.innovatespace.domain.repository.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindNewsRepository(repository: NewsRepository): NewsRepo
}