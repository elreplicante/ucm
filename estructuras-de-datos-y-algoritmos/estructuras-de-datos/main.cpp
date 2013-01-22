/*
 * main.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */
#include "include/ExcepcionTAD.h"
#include "include/Pareja.h"
#include "include/PilaE.h"
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
	PilaE<int> pila = PilaE<int>();


	cout << "(" << primero << "," << segundo << ")" << endl;
	try {
		cout << division(5, 5) << endl;
	// 	cout << division(5, 0);
		cout << division(25, 5) << endl;
	//	pila.desapila();
		for (int i = 0; i < 100; i++) {
			pila.apila(i);
		}
		cout << endl << pila.numElems();

		pila.apila(1);
	} catch (const EDivisionPorCero &e) {
		cout << "Division por cero" << endl;
	} catch (const EPilaVacia &vacia) {
		cout << "Pila vacia" << endl;

	} catch (const EPilaLlena &llena) {
		cout << "Pila llena" << endl;

	}

	return 0;
}
