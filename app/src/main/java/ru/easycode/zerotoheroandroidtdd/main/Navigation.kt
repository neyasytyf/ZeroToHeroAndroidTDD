package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface Navigation {

    interface Read : LiveDataWrapper.Read<Screen>

    interface Update : LiveDataWrapper.Update<Screen>

    interface Mutable : Read, Update

    class Base(liveData: MutableLiveData<Screen>) : LiveDataWrapper.Abstract<Screen>(liveData),
        Mutable
}