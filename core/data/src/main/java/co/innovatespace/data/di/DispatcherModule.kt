package co.innovatespace.data.di

import co.innovatespace.data.dispatchers.CoroutineDispatchersProvider
import co.innovatespace.utility.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider): co.innovatespace.utility.DispatchersProvider
}