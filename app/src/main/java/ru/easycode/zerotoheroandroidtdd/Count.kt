package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            } else if (max <= 0) {
                throw IllegalStateException("max should be positive, but was $max")
            } else if (max < step) {
                throw IllegalStateException("max should be more than step")
            } else if (max < min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            val numberInt = number.toInt()

            return if (numberInt + step > max) {
                UiState.Max(number)
            } else if (numberInt - step < min) {
                UiState.Min(number)
            } else {
                UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val numberInt = number.toInt() + step
            return initial(numberInt.toString())
        }

        override fun decrement(number: String): UiState {
            val numberInt = number.toInt() - step
            return initial(numberInt.toString())
        }
    }
}