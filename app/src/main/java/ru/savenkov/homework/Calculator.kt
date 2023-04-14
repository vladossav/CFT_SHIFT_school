package ru.savenkov.homework

import kotlin.math.ln
import kotlin.math.pow

class Calculator(private val converter: Converter) {

    fun multiply(multiplicand: Double, multiplier: Double): Double =
        multiplicand * multiplier

    fun divide(dividend: Double, divisor: Double): Double =
        if (divisor != 0.0) {
            dividend / divisor
        } else {
            throw IllegalArgumentException("Divisor cannot be 0")
        }

    fun deduct(reduced: Double, deducted: Double): Double =
        reduced - deducted

    fun raiseToDegree(number: Double, degree: Double): Double =
        number.pow(degree)

   fun evaluateFromInputStringArguments(action: Action, firstArgString: String, secondArgString: String): Double {
       val firstArgument = converter.convertInputStringNumberToDouble(firstArgString)
       val secondArgument = converter.convertInputStringNumberToDouble(secondArgString)

       return when(action) {
            Action.MULTIPLY -> multiply(firstArgument, secondArgument)
            Action.DIVIDE -> divide(firstArgument, secondArgument)
            Action.POW -> calculateNaturalLogarithm(firstArgument)
            Action.DEDUCT -> deduct(firstArgument, secondArgument)
        }
   }

    fun calculateNaturalLogarithm(number: Double): Double =
        if (number <= 0.0)
            throw IllegalArgumentException("Argument cannot be less or equal zero")
        else
            ln(number)


}