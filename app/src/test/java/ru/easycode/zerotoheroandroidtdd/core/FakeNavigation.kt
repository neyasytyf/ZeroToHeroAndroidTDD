package ru.easycode.zerotoheroandroidtdd.core

import org.junit.Assert.assertEquals

interface FakeNavigation : Navigation.Update {

    companion object {
        const val NAVIGATE = "FakeNavigation#navigate"
    }

    fun checkScreen(screen: Screen)

    class Base(private val order: Order) : FakeNavigation {

        private lateinit var actual: Screen

        override fun update(screen: Screen) {
            actual = screen
            order.add(NAVIGATE)
        }

        override fun checkScreen(expected: Screen) {
            assertEquals(expected, actual)
        }
    }
}