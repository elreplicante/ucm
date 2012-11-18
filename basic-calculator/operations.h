#include <iostream>
#include <cmath>
#include <fstream>

using namespace std;

#ifndef OPERATIONS_H_
#define OPERATIONS_H_

/**
 * @brief Calculates the sum of two real numbers
 * @param number1 the first operand
 * @param number2 the second operand
 * @return a double that represents the sum of the operands
 */
double addNumbers(double number1, double number2);

/**
 * @brief Calculates the difference between two real numbers
 * @param number1 the first operand
 * @param number2 the second operand
 * @return a double that represents the subtraction of the operands
 */
double subtractNumbers(double number1, double number2);

/**
 * @brief Calculates the multiplication of two real numbers
 * @param number1 the first operand
 * @param number2 the second operand
 * @return a double that represents the multiplication of the operands
 */
double multiplyNumbers(double number1, double number2);

/**
 * @brief Calculates the division of two real numbers
 * @param number1 the first operand
 * @param number2 the second operand
 * @return a double that represents the division of the operands
 */
double divideNumbers(double number1, double number2);

/**
 * @brief Calculates the square root of a real numbers
 * @param number the number for which calculate its square root. It must be a positive double
 * @return the square root of the given number
 */
double square(double number);

/**
 * @brief Calculates the factorial of an integer number
 * @param number the number for which calculate its factorial
 * @return the factorial of the given number
 */
double factorial(double number);

/**
 * @brief Calculates the power of a real number raised to an integer.
 * @param number a double representing the base
 * @param exponent an integer representing the exponent.  Can be a negative integer
 * @return base raised to exponent
 */
double powerOfNumber(double number, int exponent);


/**
 * @brief
 */
int divideFractions(int numerator, int denominator);

/**
 * @brief Simplifies a fraction
 * @param numerator the fraction numerator
 * @param denominator the fraction denominator
 */
void simplifyFraction(int &numerator, int &denominator);

/**
 * @brief Calculates the sum of two fractions
 * @param numerator1 the first numerator
 * @param denominator1 the first denominator
 * @param numerator2 the second numerator
 * @param denominator2 the second denominator
 * @param resultNumerator the result numerator, returned by reference
 * @param resultDenominator the result denominator, returned by reference
 */
void addFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator);

/**
 * @brief Calculates the subtraction of two fractions
 * @param numerator1 the first numerator
 * @param denominator1 the first denominator
 * @param numerator2 the second numerator
 * @param denominator2 the second denominator
 * @param resultNumerator the result numerator, returned by reference
 * @param resultDenominator the result denominator, returned by reference
 */
void subtractFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator);

/**
 * @brief Calculates the multiplication of two fractions
 * @param numerator1 the first numerator
 * @param denominator1 the first denominator
 * @param numerator2 the second numerator
 * @param denominator2 the second denominator
 * @param resultNumerator the result numerator, returned by reference
 * @param resultDenominator the result denominator, returned by reference
 */
void multiplyFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator);

/**
 * @brief Calculates the division of two fractions
 * @param numerator1 the first numerator
 * @param denominator1 the first denominator
 * @param numerator2 the second numerator
 * @param denominator2 the second denominator
 * @param resultNumerator the result numerator, returned by reference
 * @param resultDenominator the result denominator, returned by reference
 */
void divideFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator);

/**
 * @brief Calculates the result of raising a fraction to an exponent
 * @param numerator the numerator
 * @param denominator the denominator
 * @param power the power to raise to
 * @param resultNumerator the result numerator, returned by reference
 * @param resultDenominator the result denominator, returned by reference
 */
void powerOfFractions(int numerator, int denominator, int power,
		int &resultNumerator, int &resultDenominator);

/**
 * @brief Calculates greatest common divisor of two fractions
 * @param denominator1 the first denominator
 * @param denominator2 the second denominator
 * @return an integer that represents the greatest common divisor
 */
int greatestCommonDivisor(int denominator1, int denominator2);


/**
 * @brief Calculates least common multiple of two fractions
 * @param denominator1 the first denominator
 * @param denominator2 the second denominator
 * @return an integer that represents the least common multiple
 */
int leastCommonMultiple(int denominator1, int denominator2);

#endif /* OPERATIONS_H_ */
