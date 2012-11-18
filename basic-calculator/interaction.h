#include <iostream>
#include <fstream>
using namespace std;

#ifndef INTERACTION_H_
#define INTERACTION_H_

/**
 * @brief reads a fraction from the console
 * @param numerator the fraction numerator
 * @param denominator the fraction denominator
 */
void readFraction(int &numerator, int &denominator);

/**
 * @brief prints the fraction on the console
 * @param numerator the fraction numerator
 * @param denominator the fraction denominator
 */
void printFraction(int numerator, int denominator);

/**
 * @brief Prints all the operators asigned characters
 * @params all the calculator operators
 */
void printOperators(char exitOperator, char cancelOperator, char addOperator,
		char subtractOperator, char multiplyOperator, char divideOperator,
		char squareOperator, char powerOperator, char factorialOperator);

/**
 * @brief Prints the menu
 * @return a char with the chosen character
 */
char printMenu();

/**
 * @brief Tries to read the configuration file. If success, reads the operator from file.
 * @params all the calculator operators
 */
void loadOperatorsFromFile(char &exitOperator, char &cancelOperator,
		char &addOperator, char &subtractOperator, char &multiplyOperator,
		char &divideOperator, char &squareOperator, char &powerOperator,
		char &factorialOperator, char &simplifyOperator);

#endif /* INTERACTION_H_ */
