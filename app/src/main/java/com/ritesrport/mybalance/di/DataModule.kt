package com.ritesrport.mybalance.di

import com.ritesrport.core.database.data.db.DefaultCategoriesProvider
import com.ritesrport.mybalance.data.DefaultCategoriesProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDefaultCategoriesProvider(
        impl: DefaultCategoriesProviderImpl
    ): DefaultCategoriesProvider
}
