/*
 * ArbolBinario.cpp
 *
 *  Created on: 15/03/2012
 *      Author: repli
 */

#include <cstring>
#include <assert.h>
#include <iostream>

using namespace std;

#ifndef NODO
#define NODO

template<class E> class ArbolBinario;

template<class E> class Nodo {

private:
	E _dato;
	ArbolBinario<E>* _izq;
	ArbolBinario<E>* _dcha;

	Nodo(const Nodo<E>* izq, const Nodo<E>* dcha, E dato) {
		_izq = izq;
		_dcha = dcha;
	}

	Nodo() {
		_izq = NULL;
		_dcha = NULL;
	}

	Nodo(const E& dato) {
		Nodo();
		_dato = dato;
	}

	friend class ArbolBinario<E> ;
};

#endif

#ifndef ARBOLBINARIO
#define ARBOLBINARIO

template<class E> class ArbolBinario {

private:

	Nodo<E>* _raiz;

public:
	ArbolBinario() {
		_raiz = NULL;
	}

	void plantar(ArbolBinario a, const E elemento, ArbolBinario b) {
		Nodo<E>* nodo = new Nodo<E>();
		nodo->_izq = a->_raiz;
		nodo->_dato = elemento;
		nodo->_dcha = b->_raiz;

	}

	ArbolBinario hijoIzquierdo() {
		assert(_raiz != NULL);
		return _raiz->_izq;
	}

	E raiz() {
			assert(_raiz != NULL);
			return _raiz->_dato;
		}

	E izquierda() {

		return _raiz->_izq->_dato;
	}

	E derecha() {

		return _raiz->_dcha->_dato;
	}

	bool vacio() {
		return _raiz == NULL;
	}

	int altura() {
		int altura;
		if (_raiz == NULL)
			altura = 0;
		else {
			altura = 1;
			altura  += altura(_raiz->_izq);
			altura +=altura(_raiz->_izq);
		}
		return altura;
	}

	void preorderPrint(_raiz) {
	           // Print all the items in the tree to which root points.
	           // The item in the root is printed first, followed by the
	           // items in the left subtree and then the items in the
	           // right subtree.
	        if ( _raiz != NULL ) {  // (Otherwise, there's nothing to print.)
	           cout << _raiz->_dato << " ";      // Print the root item.
	           preorderPrint( _raiz->_izq );    // Print items in left subtree.
	           preorderPrint(  _raiz->_dcha );   // Print items in right subtree.
	        }
	     } // end preorderPrint()

	void postorderPrint(const  ArbolBinario<E> arbol) {
	           // Print all the items in the tree to which root points.
	           // The items in the left subtree are printed first, followed
	           // by the items in the right subtree and then the item in the
	           // root node.
	        if ( _raiz != NULL ) {  // (Otherwise, there's nothing to print.)
	           postorderPrint( _raiz->_izq );    // Print items in left subtree.
	           postorderPrint( _raiz->_dcha );   // Print items in right subtree.
	           cout << _raiz->_dato << " ";       // Print the root item.
	        }
	     } // end postorderPrint()


	     void inorderPrint(const  ArbolBinario<E> arbol) {
	           // Print all the items in the tree to which root points.
	           // The items in the left subtree are printed first, followed
	           // by the item in the root node, followed by the items in
	           // the right subtree.
	        if ( arbol._raiz != NULL ) {  // (Otherwise, there's nothing to print.)
	           inorderPrint(arbol. _raiz->_izq );    // Print items in left subtree.
	           cout << arbol._raiz->_dato << " ";     // Print the root item.
	           inorderPrint( arbol._raiz->_dcha );   // Print items in right subtree.
	        }
	     } // end inorderPrint()

};

#endif

//
//int main() {
//
//
//	ArbolBinario<int> arbol = ArbolBinario<int>();
//	arbol.plantar(arbol, 1, arbol);
//	arbol.inorderPrint(arbol);
//
//	return 0;
//}
