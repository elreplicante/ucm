#include "checkers.h"

void checkForAddOperation(char operator1, char addOperator, double &number2,
		double &number1) {
	if (operator1 == addOperator) {
		cin >> number2;
		number1 = addNumbers(number1, number2);
	}
}

void checkForSubtractOperation(char operator1, char subtractOperator,
		double &number2, double &number1) {
	if (operator1 == subtractOperator) {
		cin >> number2;
		number1 = subtractNumbers(number1, number2);
	}
}

void checkForMultiplyOperation(char operator1, char multiplyOperator,
		double &number2, double &number1) {
	if (operator1 == multiplyOperator) {
		cin >> number2;
		number1 = multiplyNumbers(number1, number2);
	}
}

void checkForDivideOperation(char operator1, char divideOperator,
		double &number2, double &number1) {
	if (operator1 == divideOperator) {
		cin >> number2;
		if (number2 != 0) {
			number1 = divideNumbers(number1, number2);
		} else {
			cout << "Error. You can't divide by '0'" << endl;
		}
	}
}

void checkForSquareOperation(char operator1, char squareOperator,
		double &number1) {
	if (operator1 == squareOperator) {
		number1 = square(number1);
	}
}

void checkForFactorialOperation(char operator1, char factorialOperator,
		double &number1) {
	if (operator1 == factorialOperator) {
		if (number1 >= 0) {
			number1 = factorial(number1);
		} else
			cout << "error";
	}
}

void checkForPowerOperation(char operator1, char powerOperator, int exponent,
		double &powerResult, double &number1) {
	if (operator1 == powerOperator) {
		cin >> exponent;
		powerResult = number1;
		if (exponent == 0) {
			number1 = 1;
		} else
			number1 = powerOfNumber(number1, exponent);
	}
}

void checkForCancelOrExit(char &operator1, char exitOperator, double number1,
		char &operator2, char cancelOperator) {
	if (operator1 != exitOperator) {
		cin.get(operator1);
		if (operator1 == '\n') {
			cout << number1 << endl;
			cout << endl;
		}
		operator2 = cin.peek();
		if (operator2 == cancelOperator) {
			number1 = 0;
			cout << number1 << endl;
			operator1 = exitOperator;
		}
	}
}


void checkOperation(char &operator1, char addOperator, double &number2,
		double &number1, char subtractOperator, char multiplyOperator,
		char divideOperator, char squareOperator, char factorialOperator,
		char powerOperator, int exponent, double &powerResult, char exitOperator,
		char &operator2, char cancelOperator) {

	checkForAddOperation(operator1, addOperator, number2, number1);
	checkForSubtractOperation(operator1, subtractOperator, number2, number1);
	checkForMultiplyOperation(operator1, multiplyOperator, number2, number1);
	checkForDivideOperation(operator1, divideOperator, number2, number1);
	checkForSquareOperation(operator1, squareOperator, number1);
	checkForFactorialOperation(operator1, factorialOperator, number1);
	checkForPowerOperation(operator1, powerOperator, exponent, powerResult,
			number1);
	checkForCancelOrExit(operator1, exitOperator, number1, operator2,
			cancelOperator);
}


void checkOperator(char &character, char &operator1, char exitOperator) {
	character = cin.peek();

	if (isdigit(character)) {
		operator1 = exitOperator;
		cout << "error.." << endl;
	} else {
		cin.get(operator1);
	}
}

void checkFirstCharacter(char &character, double &number1) {

	character = cin.peek();
	if (isdigit(character)) {
		cin >> number1;
	} else {
		number1 = 0;
		cout << "error.." << endl;
	}
}


void checkSimplifyOperation(char operator1, char simplifyOperator,
		int &numerator1, int &denominator1) {

	if (operator1 == simplifyOperator) {
		simplifyFraction(numerator1, denominator1);
		printFraction(numerator1, denominator1);
	}
}
