/*
 * pila.cpp
 *
 *  Created on: 06/06/2012
 *      Author: repli
 */

#include <iostream>
#include <stack>
using namespace std;

#ifndef PILAESTATICA
#define PILAESTATICA

const int N = 10;

template<class E>
class PilaEstatica {

private:

	E _contenido[N];
	int _cima;

public:
	PilaEstatica() {
		_cima = 0;
	}

	PilaEstatica &apilar(E elemento) {
		if (_cima == N)
			throw("Pila llena");
		else {
			++_cima;
			_contenido[_cima] = elemento;

		}
		return *this;
	}

	PilaEstatica &desapilar() {
		if (_cima == 0)
			throw("Pila vacia");
		else {
			_cima--;
		}
		return *this;
	}

	E cima() {
		if (_cima == 0)
			throw("Pila vacia");
		else {
			return _contenido[_cima];
		}
	}

	bool pilaVacia() {
		return _cima == 0;
	}

	void mostrarPila() {
		cout << "(";
		for (int i = 1; i <= _cima; i++) {
			cout << _contenido[i] << ", ";
		}
		cout << ")";
	}
};

#endif

/*


int main() {

	PilaEstatica<int> miPilaEstatica = PilaEstatica<int>();
	miPilaEstatica.apilar(2).apilar(6);
	miPilaEstatica.apilar(4).desapilar();
	miPilaEstatica.mostrarPila();
	int cima = miPilaEstatica.cima();
	cout << cima << endl;
	cout << miPilaEstatica.pilaVacia() << endl;
	miPilaEstatica.desapilar();
	cout << miPilaEstatica.pilaVacia() << endl;


	return 0;
}
*/
