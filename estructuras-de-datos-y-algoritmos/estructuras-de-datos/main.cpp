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

	Pareja<int, int> pareja = Pareja<int, int>(5, 8);
	int primero = pareja.primero();
	int segundo = pareja.segundo();
	cout << "(" << primero << "," << segundo << ")" << endl;
	try {
		cout << division(5, 5) << endl;
	// 	cout << division(5, 0);
		cout << division(25, 5) << endl;



	} catch (const EDivisionPorCero &e) {
		cout << "Division por cero" << endl;
	}

	return 0;
}
