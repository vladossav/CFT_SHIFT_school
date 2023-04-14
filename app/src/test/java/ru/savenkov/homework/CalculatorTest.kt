package ru.savenkov.homework

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.stream.Stream

class CalculatorTest {

    private companion object {

        @JvmStatic
        fun argsForDividing(): Stream<Arguments> = Stream.of(
            Arguments.arguments(3.0, 1.5, 2.0),
            Arguments.arguments(0.0, 1.0, 0.0),
            Arguments.arguments(1000.0, -1.0, -1000.0)
        )

        @JvmStatic
        fun argsForPowFunction(): Stream<Arguments> = Stream.of(
            Arguments.arguments(2.0, 4.0, 16.0),
            Arguments.arguments(-1.0, 5.0, -1.0),
            Arguments.arguments(23.3, 1.0, 23.3),
            Arguments.arguments(233.13, 0.0, 1),
            Arguments.arguments(Double.NaN, 41.0, Double.NaN)
        )
    }

    private val converter: Converter = mock()
    private val calculator = Calculator(converter)

    @Test
    fun `WHEN multiply 2 by 2 EXPECT 4`() {
        val expected = 4.0
        val actual = calculator.multiply(multiplicand = 2.0, multiplier = 2.0)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("argsForDividing")
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

    @Test
    fun `WHEN deduct 20 from 5 EXPECT -15`() {
        val expected = -15.0
        val actual = calculator.deduct(5.0,20.0)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("argsForPowFunction")
    fun `WHEN raise to degree EXPECT correct result`(number: Double, degree: Double, expected: Double) {
        val actual = calculator.raiseToDegree(number, degree)
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN evaluate multiplying with input string args EXPECT choice of correct multiplying action`() {
        val mock = Mockito.anyString()
        whenever(converter.convertInputStringNumberToDouble(mock)) doReturn 3.0
        val actual = calculator.evaluateFromInputStringArguments(Action.MULTIPLY, mock,mock)
        val expected = 9.0
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN natural logarithm is 1 EXPECT result 0`() {
        val actual = calculator.calculateNaturalLogarithm(1.0)
        val expected = 0.0
        assertEquals(expected, actual)
    }
}