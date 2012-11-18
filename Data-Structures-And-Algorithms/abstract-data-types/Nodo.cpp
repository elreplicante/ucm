/*
 * nodo.cpp
 *
 *  Created on: 08/03/2012
 *      Author: repli
 */

#ifndef NODO
  #define NODO

#include <cstring>

template <class E>  class Nodo {

	private:
		E _dato;
		Nodo<E>* _sig;
		Nodo<E>* _ant;

		Nodo (const Nodo<E>* sig, const Nodo<E>* ant, E dato){
			_sig = sig;
			_ant = ant;
			_dato = dato;
		}

		Nodo() {
			_sig = NULL;
			_ant = NULL;
		}

		Nodo(const E dato) {
			_sig = NULL;
			_ant = NULL;
			_dato = dato;
		}

		friend class Cola;
		friend class PilaDinamica;
		friend class ListaDinamica;
};

#endif


