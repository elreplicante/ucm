#include "interaction.h"

void readFraction(int &numerator, int &denominator) {
	char vinculum, character;

	character = cin.peek();

	if (isdigit(character)) {
		cin >> numerator;
		cin >> vinculum;
		cin >> denominator;
	} else {
		numerator = 0;
		denominator = 0;
	}

}

void printFraction(int numerator, int denominator) {
	cout << numerator << "|" << denominator << endl;
	cout << endl;

}

void printOperators(char exitOperator, char cancelOperator, char addOperator,
		char subtractOperator, char multiplyOperator, char divideOperator,
		char squareOperator, char powerOperator, char factorialOperator) {
	cout << endl;
	cout << "Operand Characters:" << endl;
	cout << "Exit to main menu: " << exitOperator << endl;
	cout << "Cancel operation" << cancelOperator << endl;
	cout << "Add: " << addOperator << endl;
	cout << "Sustract: " << subtractOperator << endl;
	cout << "Multiply: " << multiplyOperator << endl;
	cout << "Divide: " << divideOperator << endl;
	cout << "Square root: " << squareOperator << endl;
	cout << "Power: " << powerOperator << endl;
	cout << "Factorial: " << factorialOperator << endl;
	cout << endl << endl;
}

char printMenu() {
	char menuChar;

	cout << "Main Menu" << endl;
	cout << endl;

	cout << "1 - Real Numbers Calculator" << endl;
	cout << "2 - Operands configuration" << endl;
	cout << "3 - Fractions calculator" << endl;
	cout << "4 - Exit" << endl;
	cout << endl;

	cout << "Option:";

	cin.sync();
	cin >> menuChar;

	return menuChar;
}

void loadOperatorsFromFile(char &exitOperator, char &cancelOperator,
		char &addOperator, char &subtractOperator, char &multiplyOperator,
		char &divideOperator, char &squareOperator, char &powerOperator,
		char &factorialOperator, char &simplifyOperator) {

	ifstream configurationFile;
	configurationFile.open("configuracion.txt");

	if (!configurationFile.is_open()) {
		cout << "Configuration file not found" << endl;
		cout << endl;
	} else {
		configurationFile >> exitOperator;
		configurationFile >> cancelOperator;
		configurationFile >> addOperator;
		configurationFile >> subtractOperator;
		configurationFile >> multiplyOperator;
		configurationFile >> divideOperator;
		configurationFile >> squareOperator;
		configurationFile >> powerOperator;
		configurationFile >> factorialOperator;
		configurationFile >> simplifyOperator;

		configurationFile.close();
	}

}
