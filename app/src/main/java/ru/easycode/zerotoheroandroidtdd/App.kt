package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(), ProvideViewModel {

    private lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val singleLiveEvent = SingleLiveEvent<UiState>()
        val liveDataWrapper: LiveDataWrapper.Mutable = LiveDataWrapper.Base(singleLiveEvent)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: SimpleService = retrofit.create(SimpleService::class.java)
        val repository: Repository = Repository.Base(service, URL)

        viewModel = MainViewModel(liveDataWrapper, repository)
    }

    override fun provideViewModel(): MainViewModel {
        return viewModel
    }

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }

}