#include "configuration.h"

void configureExitOperator(char &character, char &exitOperator) {
	cout << "Exit character:";
	cin >> character;

	if (character != '\n') {
		exitOperator = character;
	}
}

void configureCancelOperator(char &character, char &cancelOperator) {
	cout << "Cancel character:";
	cin >> character;

	if (character != '\n') {
		cancelOperator = character;
	}
}

void configureAddOperator(char &character, char &addOperator) {
	cout << "Add character:";
	cin >> character;

	if (character != '\n') {
		addOperator = character;
	}
}

void configureSubtractOperator(char &character, char &subtractOperator) {
	cout << "Sustract character:";
	cin >> character;

	if (character != '\n') {
		subtractOperator = character;
	}
}

void configureMultiplyOperator(char &character, char &multiplyOperator) {
	cout << "Multiply character:";
	cin >> character;

	if (character != '\n') {
		multiplyOperator = character;
	}
}

void configureDivideOperator(char &character, char &divideOperator) {
	cout << "Divide character:";
	cin >> character;

	if (character != '\n') {
		divideOperator = character;
	}
}

void configurSquareOperator(char &character, char &squareOperator) {
	cout << "Square root character:";
	cin >> character;

	if (character != '\n') {
		squareOperator = character;
	}
}

void configurePowerOperator(char &character, char &powerOperator) {
	cout << "Power character:";
	cin >> character;

	if (character != '\n') {
		powerOperator = character;
	}
}

void configureFactorialOperator(char &character, char &factorialOperator) {
	cout << "Factorial character:";
	cin >> character;

	if (character != '\n') {
		factorialOperator = character;
	}
}

void configureOperators(char &character, char &exitOperator,
		char &cancelOperator, char &addOperator, char &subtractOperator,
		char &multiplyOperator, char &divideOperator, char &squareOperator,
		char &powerOperator, char &factorialOperator) {

	configureExitOperator(character, exitOperator);
	configureCancelOperator(character, cancelOperator);
	configureAddOperator(character, addOperator);
	configureSubtractOperator(character, subtractOperator);
	configureMultiplyOperator(character, multiplyOperator);
	configureDivideOperator(character, divideOperator);
	configurSquareOperator(character, squareOperator);
	configurePowerOperator(character, powerOperator);
	configureFactorialOperator(character, factorialOperator);
}

void saveConfigurationToFile(char &exitOperator, char &cancelOperator,
		char &addOperator, char &subtractOperator, char &multiplyOperator,
		char &divideOperator, char &squareOperator, char &powerOperator,
		char &factorialOperator, char &simplifyOperator) {

	ofstream configurationFile;
	configurationFile.open("configuracion.txt");
	configurationFile << exitOperator << endl;
	configurationFile << cancelOperator << endl;
	configurationFile << addOperator << endl;
	configurationFile << subtractOperator << endl;
	configurationFile << multiplyOperator << endl;
	configurationFile << divideOperator << endl;
	configurationFile << squareOperator << endl;
	configurationFile << powerOperator << endl;
	configurationFile << factorialOperator << endl;
	configurationFile << simplifyOperator << endl;
	configurationFile.close();
}
