package ru.easycode.zerotoheroandroidtdd.list

import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.main.Screen

object ListScreen : Screen {
    override fun show(fragmentManager: FragmentManager, containerId: FragmentContainerView) {
        fragmentManager.beginTransaction()
            .replace(containerId.id, ListFragment())
            .commit()
    }
}