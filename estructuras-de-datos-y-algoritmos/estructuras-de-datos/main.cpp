/*
 * main.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */
#include "include/EDivisionPorCero.h"
#include "include/Pareja.h"
#include <iostream>
using namespace std;

int division(int a, int b) {
	if (b == 0) {
		throw EDivisionPorCero();
	}

	return a / b;
}

int main() {

//	Pareja<int> pareja = Pareja<int>(5, 8);
	try {
		cout << division(5, 5) << endl;
	// 	cout << division(5, 0);
		cout << division(25, 5) << endl;



	} catch (const EDivisionPorCero &e) {
		cout << "Division por cero" << endl;
	}

	return 0;
}