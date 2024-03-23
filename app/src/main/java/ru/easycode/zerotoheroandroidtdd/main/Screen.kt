package ru.easycode.zerotoheroandroidtdd.main

import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(fragmentManager: FragmentManager, containerId: FragmentContainerView)

    object Pop : Screen {
        override fun show(fragmentManager: FragmentManager, containerId: FragmentContainerView) {
            fragmentManager.popBackStack()
        }
    }

}