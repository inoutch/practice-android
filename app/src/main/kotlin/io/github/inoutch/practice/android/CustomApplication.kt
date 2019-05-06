package io.github.inoutch.practice.android

import android.app.Application
import io.github.inoutch.practice.android.model.MessageViewModel
import io.github.inoutch.practice.android.repository.MessageRepository
import io.github.inoutch.practice.android.repository.OekakiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.dsl.module

@ExperimentalCoroutinesApi
class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(repositoryModule)
        }
    }

    private val repositoryModule = module {
        single { MessageRepository() }
        single { OekakiRepository() }
    }
}
