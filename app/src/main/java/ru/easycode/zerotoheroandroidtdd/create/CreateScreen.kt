package ru.easycode.zerotoheroandroidtdd.create

import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.main.Screen

object CreateScreen : Screen {
    override fun show(fragmentManager: FragmentManager, containerId: FragmentContainerView) {
        val fragment = CreateFragment()
        fragmentManager.beginTransaction()
            .replace(containerId.id, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }
}