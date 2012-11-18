/*
 * ListaDinamica.cpp
 *
 *  Created on: 09/03/2012
 *      Author: repli
 */
#include <cstring>
#include <assert.h>
#include <iostream>

using namespace std;

#ifndef NODO
#define NODO

template<class E> class ListaDinamica;

template<class E> class Nodo {

private:
	E _dato;
	Nodo<E>* _sig;
	Nodo<E>* _ant;

	Nodo(const Nodo<E>* sig, E dato) {
		_sig = sig;
		_dato = dato;
	}

	Nodo() {
		_sig = NULL;
		_ant = NULL;
	}

	Nodo(const E& dato) {
		Nodo();
		_dato = dato;
	}

	friend class ListaDinamica<E> ;
};

#endif

#include <cstring>
#include <assert.h>

#ifndef LISTADINAMICA
#define LISTADINAMICA // Define la cola
using namespace std;

template<class E> class ListaDinamica {

private:
	Nodo<E>* _ultimo;
	Nodo<E>* _primero;
	int _numElementos;

public:

	ListaDinamica() {

		_primero = NULL;
		_ultimo = NULL;
		_numElementos = 0;
	}

	/**
	 * Insertar por la izquierda
	 */
	void addI(const E elemento) {

		Nodo<E>* aux = new Nodo<E>(elemento);

		if (_ultimo == NULL) {
			_ultimo = aux;
		} else {

			aux->_sig = _primero;
			_primero->_ant = aux;
		}

		_primero = aux;
		_numElementos++;
	}

	/**
	 * Insertar por la derecha
	 */
	void cue(const E elemento) {

		Nodo<E>* aux = new Nodo<E>(elemento);

		if (_primero == NULL) {
			_primero = aux;
		} else {

			aux->_ant = _ultimo;
			_ultimo->_siguiente = aux;
		}

		_ultimo = aux;
		_numElementos++;
	}

	/**
	 * Lista vacia
	 */
	bool isEmpty() {
		return _numElementos == 0;
	}

	/**
	 * Longitud lista
	 */
	int length() {
		return _numElementos;
	}

	/**
	 * Imprimir contenido
	 */

	void print() {
		Nodo<E>* first = _primero;

		while (first != NULL) {
			cout << first->_dato << " ";
			first = first->_sig;
		}

		cout << endl;
	}

	/**
	 * Inserta un elemento
	 */

	void insert(int i, E dato) {
		assert(i <= _numElementos && i >= 0);
		if (i == 0) {
			addI(dato);
			return;
		}
		if (i == _numElementos) {
			cue(dato);
			return;
		}
		Nodo<E>* first = _primero;
		while (i > 0) {
			first = first->_sig;
			i--;
		}
		Nodo<E>* aux = new Nodo<E>(dato);
		aux->_sig = first->_sig;
		aux->_ant = first;
		first->_sig->_ant = aux;
		first->_sig = aux;
		_numElementos++;
	}


	void remove() {
		assert(_numElementos > 0);
		Nodo<E>* aux = _primero;
		_primero = _primero -> _sig;

	}

	/**
	 * Devuelve la misma lista
	 */
	void resto() {

		assert(_numElementos > 0);
		Nodo<E>* aux;

		do {

			aux = _primero->_sig;

		} while (true); // insertar condicion
	}

	E elementoIesimo(int posicion) {

		int contador = 0;
		Nodo<E>* aux;

		assert(_numElementos != 0);

		aux = _primero;

		do {

			aux = aux->_sig;
			contador++;
		} while (contador < posicion);

		return aux->_dato;
	}

};

#endif
/*
int main() {

	ListaDinamica<int> lista =  ListaDinamica<int>();
	lista.addI(1);
	lista.addI(3);
	lista.addI(-5);
	lista.addI(8);
	lista.addI(9);
	lista.addI(0);

	lista.print();

	return 0;
}

*/
