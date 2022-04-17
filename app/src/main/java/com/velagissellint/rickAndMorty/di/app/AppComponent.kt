package com.velagissellint.rickAndMorty.di.app

import android.app.Application
import com.velagissellint.brave.di.app.AppModule
import com.velagissellint.presentation.containersDi.AppContainer
import com.velagissellint.rickAndMorty.App
import com.velagissellint.rickAndMorty.di.listOfCharacters.ListOfCharactersComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppContainer {
    override fun plusListOfCharactersComponent(): ListOfCharactersComponent

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
