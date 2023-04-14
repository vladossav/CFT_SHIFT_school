package ru.savenkov.homework

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CalculatorTest {

    private companion object {

        @JvmStatic
        fun data(): Stream<Arguments> = Stream.of(
            Arguments.arguments(3.0, 1.5, 2.0),
            Arguments.arguments(0.0, 1.0, 0.0),
            Arguments.arguments(1000.0, -1.0, -1000.0)
        )
    }

    private val calculator = Calculator()

    @Test
    fun `WHEN multiply 2 by 2 EXPECT 4`() {
        val expected = 4.0

        val actual = calculator.multiply(multiplicand = 2.0, multiplier = 2.0)

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("data")
    fun `WHEN divide EXPECT correct result`(dividend: Double, divisor: Double, expected: Double) {
        val actual = calculator.divide(dividend, divisor)

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN divide by zero EXPECT illegal argument exception`() {

        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            calculator.divide(dividend = 4.0, divisor = 0.0)
        }
    }
}