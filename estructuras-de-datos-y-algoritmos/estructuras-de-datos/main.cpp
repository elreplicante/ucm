/*
 * main.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */
#include "include/Excepciones.h"
#include "include/PilaVectorDinamico.h"
#include <iostream>
#include <string>
using namespace std;




int main() {

	PilaVectorDinamico<char> pila = PilaVectorDinamico<char>();

	string input;
	getline(cin, input);


	for (int i = 0; i < input.length(); i++) {
		pila.apila(input.at(i));
	}




	while (!pila.esVacia()) {
		char c =  pila.cima();
		cout << c;
		pila.desapila();
	}

	return 0;
}
