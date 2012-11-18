#include "operations.h"
#include "interaction.h"

#ifndef CHECKERS_H_
#define CHECKERS_H_


/**
 * @brief checks the operator1 for the add operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param addOperator the add operator
 * @param number1 the first number
 * @param number2 the second number
 */
void checkForAddOperation(char operator1, char addOperator, double &number2,
		double &number1);

/**
 * @brief checks the operator1 for the subtract operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param subtractOperator the subtract operator
 * @param number1 the first number
 * @param number2 the second number
 */
void checkForSubtractOperation(char operator1, char subtractOperator,
		double &number2, double &number1);

/**
 * @brief checks the operator1 for the multiply operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param multiplyOperator the multiply operator
 * @param number1 the first number
 * @param number2 the second number
 */
void checkForMultiplyOperation(char operator1, char multiplyOperator,
		double &number2, double &number1);

/**
 * @brief checks the operator1 for the divide operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param divideOperator the divide operator
 * @param number1 the first number
 * @param number2 the second number
 */
void checkForDivideOperation(char operator1, char divideOperator,
		double &number2, double &number1);

/**
 * @brief checks the operator1 for the square root operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param squareOperator the square root operator
 * @param number1 the first number
 */
void checkForSquareOperation(char operator1, char squareOperator,
		double &number1);
/**
 * @brief checks the operator1 for the factorial operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param factorialOperator the factorial operator
 * @param number1 the first number
 */
void checkForFactorialOperation(char operator1, char factorialOperator,
		double &number1);

/**
 * @brief checks the operator1 for the power operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param powerOperator the multiply operator
 * @param number1 the base
 * @param exponent the exponent
 * @param powerResult the operation result
 */
void checkForPowerOperation(char operator1, char powerOperator, int exponent,
		double &powerResult, double &number1);

/**
 * @brief checks the operator1 for the cancel or exit operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param cancelOperator the cancel operator
 * @param exitOperator the exit operator
 * @param number1 the fist number
 * @param operator2 the second operator
 */
void checkForCancelOrExit(char &operator1, char exitOperator, double number1,
		char &operator2, char cancelOperator);

/**
 * @brief checks the operator1 for the simplify operation.If found, performs the operation
 * @param operator1 the operator to check
 * @param simplifyOperator the simplify operator
 * @param numerator1 the fraction to simplify numerator
 * @param denominator1 the fraction to simplify denominator
 */
void checkSimplifyOperation(char operator1, char simplifyOperator,
		int &numerator1, int &denominator1);


/**
 * @brief checks the kind of operation and performs it
 * @param all the operations allowed
 */
void checkOperation(char &operator1, char addOperator, double &number2,
		double &number1, char subtractOperator, char multiplyOperator,
		char divideOperator, char squareOperator, char factorialOperator,
		char powerOperator, int exponent, double &powerResult, char exitOperator,
		char &operator2, char cancelOperator);

/**
 * @brief Checks the user's first operator
 * @param operator1 the fist operator
 * @param exitOperator the exit operator
 */
void checkOperator(char &character, char &operator1, char exitOperator);

/**
 * @brief Checks the first operator for an operation or a number with cin.peek()
 * @param character the character entered by the user
 * @param number1 if it is a number, puts it in number1
 */
void checkFirstCharacter(char &character, double &number1);

#endif /* CHECKERS_H_ */
