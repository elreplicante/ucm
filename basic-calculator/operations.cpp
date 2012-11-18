#include "operations.h"


double addNumbers(double number1, double number2) {
	double result;
	result = number1 + number2;
	return result;
}

double subtractNumbers(double number1, double number2) {
	double result;
	result = number1 - number2;
	return result;
}

double multiplyNumbers(double number1, double number2) {
	double result;
	result = number1 * number2;
	return result;
}

double divideNumbers(double number1, double number2) {
	double result;
	result = number1 / number2;
	return result;
}

double square(double number) {
	double result;
	result = sqrt(number);
	return result;
}

double factorial(double number) {
	double result = 1;
	for (int i = 1; i <= number; i++) {
		result = result * i;

	}
	number = result;
	return number;
}

double powerOfNumber(double number, int exponent) {
	double result = 1;

	if (exponent > 0) {
		for (int i = 1; i <= exponent; i++) {
			result = result * number;
		}

	} else if (exponent < 0) {
		exponent = -exponent;
		for (int i = 1; i <= exponent; i++) {
			result = result * number;
		}
		result = 1 / result;
	}

	return result;
}

// TODO rename, mcm or mcd
int divideFractions(int numerator, int denominator) {

	int c, r, a, b;

	a = numerator;
	b = denominator;

	while (b != 0) {
		c = a / b;

		r = a % b;

		a = b;
		b = r;

	}
	return a;

}

void simplifyFraction(int &numerator, int &denominator) {
	int divi;
	divi = 2;

	while (divi != 1) {
		divi = divideFractions(numerator, denominator);
		numerator = numerator / divi;
		denominator = denominator / divi;
	}
}

void addFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator) {

	resultDenominator = denominator1 * denominator2;
	resultNumerator = (numerator1 * denominator2) + (numerator2 * denominator1);
}

void subtractFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator) {

	resultDenominator = denominator1 * denominator2;
	resultNumerator = (numerator1 * denominator2) - (numerator2 * denominator1);
}

void multiplyFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator) {

	resultDenominator = denominator1 * denominator2;
	resultNumerator = numerator1 * numerator2;
}

void divideFractions(int numerator1, int denominator1, int numerator2,
		int denominator2, int &resultNumerator, int &resultDenominator) {

	resultDenominator = numerator2 * denominator1;
	resultNumerator = numerator1 * denominator2;
}

void powerOfFractions(int numerator, int denominator, int power,
		int &resultNumerator, int &resultDenominator) {

	resultNumerator = 1;
	resultDenominator = 1;

	if (power == 0) {
		return;
	}

	else if (power > 0) {

		for (int i = 1; i <= power; i++) {

			resultNumerator = resultNumerator * numerator;
			resultDenominator = resultDenominator * denominator;
		}
	} else if (power < 0) {
		power = -power;
		for (int i = 1; i <= power; i++) {

			resultDenominator = resultDenominator * numerator;
			resultNumerator = resultNumerator * denominator;
		}
	}
}

int greatestCommonDivisor(int denominator1, int denominator2) {

	int gcd;

	while (denominator1 > 0) {
		gcd = denominator1;
		denominator1 = denominator2 % denominator1;
		denominator2 = gcd;
	}

	return (gcd);
}


int leastCommonMultiple(int denominator1, int denominator2) {

	return (denominator1 / greatestCommonDivisor(denominator1, denominator2)
			* denominator2);
}
