/*
 * DobleCola.cpp
 *
 *  Created on: 09/06/2012
 *      Author: repli
 */

/*
 * ColaDinamica.cpp
 *
 *  Created on: 06/06/2012
 *      Author: repli
 */

#include <iostream>
#include <assert.h>
using namespace std;

#ifndef DOBLECOLA
#define COLADINAMICA

template<class E> class DobleCola {
private:
	struct Nodo {
		E valor;
		struct Nodo* siguiente;
		struct Nodo* anterior; // coloca el nodo en la segunda posici—n
	}* primero;
	struct Nodo* ultimo;
	unsigned int elementos;
public:
	DobleCola() {
		elementos = 0;

	}

	~DobleCola() {
		while (elementos != 0) {
		}
		//pop();
	}

	void push_back(const E& e) {
		Nodo* aux = new Nodo;
		aux->valor = e;
		aux->siguiente = NULL;

		if (ultimo == NULL) {
			aux->anterior = NULL;
			primero = aux;
		} else {
			aux->anterior = ultimo;
			ultimo->siguiente = aux;
		}

		ultimo = aux;
		++elementos;
	}

	void push_front(const E& e) {
		Nodo* aux = new Nodo;
		aux->valor = e;
		aux->anterior = NULL;

		if (ultimo == NULL) {
			aux->siguiente = NULL;
			ultimo = aux;
		} else {
			aux->siguiente = primero;
			primero->anterior = aux;
		}

		primero = aux;
		++elementos;
	}

	void pop_back() {
		Nodo* aux;
		assert(ultimo != NULL);
		aux = ultimo;
		ultimo = aux->anterior;
		if (ultimo == NULL) {
			primero == NULL;
		} else {
			ultimo->siguiente = NULL;
		}
		delete aux;
		--elementos;
	}

	void pop_front() {
		Nodo* aux;
		assert(primero != NULL);
		aux = primero;
		primero = aux->siguiente;
		if (primero == NULL) {
			ultimo == NULL;
		} else {
			primero->anterior = NULL;
		}
		delete aux;
		--elementos;
	}

	E front() const {
		return primero->valor;
	}

	E back() const {
		return ultimo->valor;
	}

	bool empty() const {
		return elementos == 0;
	}

	unsigned int size() const {
		return elementos;
	}

};

#endif
//
//int main() {
//
//	DobleCola<int> dobleCola = DobleCola<int>();
//	dobleCola.push_back(1);
//	dobleCola.push_back(2);
//	dobleCola.push_back(3);
//	dobleCola.push_back(5);
//	dobleCola.push_back(2);
//	//cout << dobleCola.size();
//	return 0;
//}
