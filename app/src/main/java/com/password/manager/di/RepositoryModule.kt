package com.password.manager.di

import com.password.manager.data.repository.PasswordRepositoryImpl
import com.password.manager.domain.PasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDeviceFrameRepository(repository: PasswordRepositoryImpl): PasswordRepository

//    @ActivityRetainedScoped
//    @Binds
//    abstract fun bindDataStoreRepository(repository: DataStoreRepositoryImpl): DataStoreRepository

}