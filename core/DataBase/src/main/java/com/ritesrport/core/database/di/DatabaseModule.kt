package com.ritesrport.core.database.di

import android.content.Context
import androidx.room.Room
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
    fun provideMyModelDao(appDatabase: MyBalanceDatabase): TransactionDao {
        return appDatabase.transactionDao()
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