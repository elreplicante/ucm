/*
 * Nodo.h
 *
 *  Created on: 20/02/2013
 *      Author: repli
 */

#ifndef NODO_H_
#define NODO_H_
#include <cstring>

template<class T>
class Nodo {

public:
	/**
	 * Elemento del nodo
	 */
	T _elem;

	/**
	 * Puntero al siguiente nodo
	 */
	Nodo *_sig;

	/**
	 * Constructor por defecto
	 *
	 * El nodo al siguiente elemento apunta a NULL
	 */
	Nodo() :
			_sig(NULL) {
	}

	/**
	 * Constructor con el elemento
	 *
	 * @param elem El elemento que formara el nodo creado
	 */
	Nodo(const T &elem) :
			_elem(elem), _sig(NULL) {
	}

	/**
		 * Constructor con el elemento y el puntero al siguiento nodo
		 *
		 * @param elem El elemento que formara el nodo creado
		 * @param sig El puntero al siguiente nodo
		 */
	Nodo(const T &elem, Nodo *sig) :
			_elem(elem), _sig(sig) {
	}

};

#endif /* NODO_H_ */
