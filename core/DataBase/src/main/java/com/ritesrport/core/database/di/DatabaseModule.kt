package com.ritesrport.core.database.di

import android.content.Context
import androidx.room.Room
import com.ritesrport.core.database.data.dao.AccountDao
import com.ritesrport.core.database.data.dao.CategoryDao
import com.ritesrport.core.database.data.dao.TransactionDao
import com.ritesrport.core.database.data.db.MyBalanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTransactionDao(appDatabase: MyBalanceDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    fun provideAccountDao(appDatabase: MyBalanceDatabase): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    fun provideCategoryDao(appDatabase: MyBalanceDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MyBalanceDatabase {
        return Room.databaseBuilder(
            appContext,
            MyBalanceDatabase::class.java,
            "MyBalance"
        ).build()
    }
}