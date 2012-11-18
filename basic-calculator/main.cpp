#include "configuration.h"
#include "checkers.h"
#include "interaction.h"

int main() {

	char exitOperator = 'x';
	char cancelOperator = 'c';
	char addOperator = '+';
	char subtractOperator = '-';
	char multiplyOperator = '*';
	char divideOperator = '/';
	char squareOperator = 'r';
	char powerOperator = '^';
	char factorialOperator = '!';
	char simplifyOperator = 's';

	int numerator1, denominator1, numerator2, denominator2, resultNumerator,
			resultDenominator, exponent;
	double number1, number2, powerResult;
	char operator1 = '0', operator2, menuCharacter, character;

	loadOperatorsFromFile(exitOperator, cancelOperator, addOperator,
			subtractOperator, multiplyOperator, divideOperator, squareOperator,
			powerOperator, factorialOperator, simplifyOperator);
	bool exitMenu = false;
	do {
		menuCharacter = printMenu();

		switch (menuCharacter) {

		case '1':

			printOperators(exitOperator, cancelOperator, addOperator,
					subtractOperator, multiplyOperator, divideOperator,
					squareOperator, powerOperator, factorialOperator);

			cout << "Write an inline expresion:" << endl;

			do {

				cin.sync();
				checkFirstCharacter(character, number1);
				checkOperator(character, operator1, exitOperator);

				do {

					checkOperation(operator1, addOperator, number2, number1,
							subtractOperator, multiplyOperator, divideOperator,
							squareOperator, factorialOperator, powerOperator,
							exponent, powerResult, exitOperator, operator2,
							cancelOperator);
					if (operator2 == addOperator
							|| operator2 == subtractOperator
							|| operator2 == divideOperator
							|| operator2 == multiplyOperator
							|| operator2 == powerOperator
							|| operator2 == factorialOperator
							|| operator2 == squareOperator) {

						cin.get(operator1);
					}

				} while (operator1 != '\n' && operator1 != exitOperator);

			} while (operator2 != exitOperator);

			break;

		case '2':

			configureOperators(character, exitOperator, cancelOperator,
					addOperator, subtractOperator, multiplyOperator,
					divideOperator, squareOperator, powerOperator,
					factorialOperator);
			break;

		case '3':

			cout << endl << "Fractions mode" << endl << endl;

			printOperators(exitOperator, cancelOperator, addOperator,
					subtractOperator, multiplyOperator, divideOperator,
					squareOperator, powerOperator, factorialOperator);

			do {

				cout << "Write an inline fraction expresion:" << endl;
				cin.sync();

				character = cin.peek();

				if (isdigit(character)) {

					readFraction(numerator1, denominator1);

					if (numerator1 != 0 && denominator1 != 0) {
						cin.get(operator1);
					}

					else {
						operator1 = cancelOperator;
					}
				} else {
					cin.get(operator1);
				}

				do {
//TODO extract
					checkSimplifyOperation(operator1, simplifyOperator,
							numerator1, denominator1);

					if (operator1 == addOperator) {

						readFraction(numerator2, denominator2);
						addFractions(numerator1, denominator1, numerator2,
								denominator2, resultNumerator,
								resultDenominator);
						printFraction(resultNumerator, resultDenominator);
						numerator1 = resultNumerator;
						denominator1 = resultDenominator;
					}

					if (operator1 == subtractOperator) {

						readFraction(numerator2, denominator2);
						subtractFractions(numerator1, denominator1, numerator2,
								denominator2, resultNumerator,
								resultDenominator);
						printFraction(resultNumerator, resultDenominator);
						numerator1 = resultNumerator;
						denominator1 = resultDenominator;
					}

					if (operator1 == multiplyOperator) {

						readFraction(numerator2, denominator2);
						multiplyFractions(numerator1, denominator1, numerator2,
								denominator2, resultNumerator,
								resultDenominator);
						printFraction(resultNumerator, resultDenominator);
						numerator1 = resultNumerator;
						denominator1 = resultDenominator;
					}

					if (operator1 == divideOperator) {

						readFraction(numerator2, denominator2);
						divideFractions(numerator1, denominator1, numerator2,
								denominator2, resultNumerator,
								resultDenominator);
						printFraction(resultNumerator, resultDenominator);
						numerator1 = resultNumerator;
						denominator1 = resultDenominator;
					}

					if (operator1 == powerOperator) {

						cin >> exponent;

						powerOfFractions(numerator1, denominator1, exponent,
								resultNumerator, resultDenominator);
						numerator1 = resultNumerator;
						denominator1 = resultDenominator;
						printFraction(numerator1, denominator1);
					}

					if (operator1 != cancelOperator) {

						cin.get(operator1);

						operator2 = cin.peek();
						if (operator2 == cancelOperator) {
							numerator1 = 0;
							cout << numerator1 << endl;
							operator1 = exitOperator;
						}

						if (operator2 == simplifyOperator
								|| operator2 == addOperator
								|| operator2 == subtractOperator
								|| operator2 == divideOperator
								|| operator2 == multiplyOperator
								|| operator2 == powerOperator) {

							cin.get(operator1);

						}

					}

				} while (operator1 != '\n' && operator1 != exitOperator);

			} while (operator2 != exitOperator);

			break;

		case '4':
			saveConfigurationToFile(exitOperator, cancelOperator, addOperator,
					subtractOperator, multiplyOperator, divideOperator,
					squareOperator, powerOperator, factorialOperator,
					simplifyOperator);
			exitMenu = true;

			break;

		}

	} while (!exitMenu);

	return 0;
}
