package com.velagissellint.rickAndMorty.di.app

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {
    @Singleton
    @Binds
    fun context(appInstance: Application): Context
}
