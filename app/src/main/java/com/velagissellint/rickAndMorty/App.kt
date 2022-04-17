package com.velagissellint.rickAndMorty

import android.app.Application
import com.velagissellint.presentation.containersDi.AppContainer
import com.velagissellint.presentation.containersDi.ContainerAppContainer
import com.velagissellint.rickAndMorty.di.app.AppComponent
import com.velagissellint.rickAndMorty.di.app.DaggerAppComponent

lateinit var applicationComponent: AppComponent

class App : Application(), ContainerAppContainer {
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder().application(this).build()
    }

    override fun appContainer(): AppContainer = applicationComponent
}
