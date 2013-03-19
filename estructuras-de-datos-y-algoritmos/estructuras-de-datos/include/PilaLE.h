/**
  @file PilaLE.h

  Implementación del TAD Pila utilizando una
  lista enlazada de nodos.

  Estructura de Datos y Algoritmos
  Facultad de Informática
  Universidad Complutense de Madrid

 (c) Marco Antonio Gómez Martín, 2012
*/
#ifndef __PILA_LISTA_ENLAZADA_H
#define __PILA_LISTA_ENLAZADA_H

#include "Excepciones.h"

/**
 Implementación del TAD Pila utilizando una lista enlazada de nodos.

 Las operaciones son:

 - PilaVacia: -> Pila. Generadora implementada en el
   constructor sin parámetros.
 - apila: Pila, Elem -> Pila. Generadora
 - desapila: Pila - -> Pila. Modificadora parcial.
 - cima: Pila - -> Elem. Observadora parcial.
 - esVacia: Pila -> Bool. Observadora.
 - numElems: Pila -> Entero. Observadora.

 @author Marco Antonio Gómez Martín
 */
template <class T>
class PilaLE {
public:

	/** Constructor; operación PilaVacia */
	PilaLE() : _cima(NULL), _numElems(0) {
	}

	/** Destructor; elimina la lista enlazada. */
	~PilaLE() {
		libera();
		_cima = NULL;
	}

	/**
	 Apila un elemento. Operación generadora.

	 @param elem Elemento a apilar.
	*/
	void apila(const T &elem) {
		_cima = new Nodo(elem, _cima);
		_numElems++;
	}

	/**
	 Desapila un elemento. Operación modificadora parcial,
	 que falla si la pila está vacía.

	 desapila(Apila(elem, p) = p
	 error: desapila(PilaVacia)
	*/
	void desapila() {
		if (esVacia())
			throw EPilaVacia();
		Nodo *aBorrar = _cima;
		_cima = _cima->_sig;
		delete aBorrar;
		--_numElems;
	}

	/**
	 Devuelve el elemento en la cima de la pila. Operación
	 observadora parcial, que falla si la pila está vacía.

	 cima(Apila(elem, p) = elem
	 error: cima(PilaVacia)

	 @return Elemento en la cima de la pila.
	 */
	const T &cima() const {
		if (esVacia())
			throw EPilaVacia();
		return _cima->_elem;
	}

	/**
	 Devuelve true si la pila no tiene ningún elemento.

	 esVacia(PilaVacia) = true
	 esVacia(Apila(elem, p)) = false

	 @return true si la pila no tiene ningún elemento.
	 */
	bool esVacia() const {
		return _cima == NULL;
	}

	/**
	 Devuelve el número de elementos que hay en la
	 pila.
	 numElems(PilaVacia) = 0
	 numElems(Apila(elem, p)) = 1 + numElems(p)

	 @return Número de elementos.
	 */
	int numElems() const {
		return _numElems;
	}

	// //
	// MÉTODOS DE "FONTANERÍA" DE C++ QUE HACEN VERSÁTIL
	// A LA CLASE
	// //

	/** Constructor copia */
	PilaLE(const PilaLE<T> &other) : _cima(NULL) {
		copia(other);
	}

	/** Operador de asignación */
	PilaLE<T> &operator=(const PilaLE<T> &other) {
		if (this != &other) {
			libera();
			copia(other);
		}
		return *this;
	}

	/** Operador de comparación. */
	bool operator==(const PilaLE<T> &rhs) const {
		if (_numElems != rhs._numElems)
			return false;
		Nodo *cima1 = _cima;
		Nodo *cima2 = rhs._cima;
		while ((cima1 != NULL) && (cima2 != NULL)) {
			if (cima1->_elem != cima2->_elem)
				return false;
			cima1 = cima1->_sig;
			cima2 = cima2->_sig;
		}

		return (cima1 == NULL) && (cima2 == NULL);
	}

	bool operator!=(const PilaLE<T> &rhs) const {
		return !(*this == rhs);
	}

protected:

	void libera() {
		libera(_cima);
	}

	void copia(const PilaLE &other) {

		if (other.esVacia()) {
			_cima = NULL;
			_numElems = 0;
		} else {
			Nodo *act = other._cima;
			Nodo *ant;
			_cima = new Nodo(act->_elem);
			ant = _cima;
			while (act->_sig != NULL) {
				act = act->_sig;
				ant->_sig = new Nodo(act->_elem);
				ant = ant->_sig;
			}
			_numElems = other._numElems;
		}
	}

private:

	/**
	 Clase nodo que almacena internamente el elemento (de tipo T),
	 y un puntero al nodo siguiente, que podría ser NULL si
	 el nodo es el último de la lista enlazada.
	 */
	class Nodo {
	public:
		Nodo() : _sig(NULL) {}
		Nodo(const T &elem) : _elem(elem), _sig(NULL) {}
		Nodo(const T &elem, Nodo *sig) :
		    _elem(elem), _sig(sig) {}

		T _elem;
		Nodo *_sig;
	};

	/**
	 Elimina todos los nodos de la lista enlazada cuyo
	 primer nodo se pasa como parámetro.
	 Se admite que el nodo sea NULL (no habrá nada que
	 liberar).
	 */
	static void libera(Nodo *prim) {
		while (prim != NULL) {
			Nodo *aux = prim;
			prim = prim->_sig;
			delete aux;
		}
	}

	/** Puntero al primer elemento */
	Nodo *_cima;

	/** Número de elementos */
	int _numElems;
};

#endif // __PILA_LISTA_ENLAZADA_H
