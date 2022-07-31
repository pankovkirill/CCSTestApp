package com.example.ccstestapp.di

import android.content.Context
import com.example.ccstestapp.view.favorite.FavoriteFragment
import com.example.ccstestapp.view.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DBModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(favoriteFragment: FavoriteFragment)
}