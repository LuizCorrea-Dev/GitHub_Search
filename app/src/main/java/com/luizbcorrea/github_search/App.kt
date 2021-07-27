package com.luizbcorrea.github_search

import android.app.Application
import com.luizbcorrea.github_search.data.di.DataModule
import com.luizbcorrea.github_search.domain.di.DomainModule
import com.luizbcorrea.github_search.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load() // CARREGANDO TUDO NO DATAMODULE
        DomainModule.load()
        PresentationModule.load()
    }
}