package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(last: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(last: UiState) {
            bundle.putSerializable(KEY, last)
        }

        override fun restore(): UiState {
            return bundle.getSerializable(KEY) as UiState
        }

        companion object {
            private const val KEY = "key"
        }
    }

}