#include "operations.h"
#ifndef CONFIGURATION_H_
#define CONFIGURATION_H_

/**
 * @brief Calls all operators configure methodsº
 * @params all the calculator operators
 */
void configureOperators(char &character, char &exitOperator,
		char &cancelOperator, char &addOperator, char &subtractOperator,
		char &multiplyOperator, char &divideOperator, char &squareOperator,
		char &powerOperator, char &factorialOperator);

/**
 * @brief Configures the exit operator
 * @param character a character taken from console
 * @param exitOperator The asigned exit operator. If none is inserted, default char is taken
 */
void configureExitOperator(char &character, char &exitOperator);

/**
 * @brief Configures the cancel operator
 * @param character a character taken from console
 * @param cancelOperator The asigned cancel operator. If none is inserted, default char is taken
 */
void configureCancelOperator(char &character, char &cancelOperator);

/**
 * @brief Configures the add operator
 * @param character a character taken from console
 * @param addOperator The asigned add operator. If none is inserted, default char is taken
 */
void configureAddOperator(char &character, char &addOperator);

/**
 * @brief Configures the subtract operator
 * @param character a character taken from console
 * @param subtractOperator The asigned subtract operator. If none is inserted, default char is taken
 */
void configureSubtractOperator(char &character, char &subtractOperator);

/**
 * @brief Configures the multiply operator
 * @param character a character taken from console
 * @param multiplyOperator The asigned multiply operator. If none is inserted, default char is taken
 */
void configureMultiplyOperator(char &character, char &multiplyOperator);

/**
 * @brief Configures the divide operator
 * @param character a character taken from console
 * @param divideOperator The asigned divide operator. If none is inserted, default char is taken
 */
void configureDivideOperator(char &character, char &divideOperator);

/**
 * @brief Configures the power operator
 * @param character a character taken from console
 * @param powerOperator The asigned power operator. If none is inserted, default char is taken
 */
void configurePowerOperator(char &character, char &powerOperator);

/**
 * @brief Configures the factorial operator
 * @param character a character taken from console
 * @param factorialOperator The asigned factorial operator. If none is inserted, default char is taken
 */
void configureFactorialOperator(char &character, char &factorialOperator);



/**
 * @brief Saves actual configuration into a file
 * @params all operators
 */
void saveConfigurationToFile(char &exitOperator, char &cancelOperator,
		char &addOperator, char &subtractOperator, char &multiplyOperator,
		char &divideOperator, char &squareOperator, char &powerOperator,
		char &factorialOperator, char &simplifyOperator);

#endif /* CONFIGURATION_H_ */
