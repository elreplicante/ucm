/**
  @file Cola.h

  Implementación del TAD Cola utilizando una
  lista enlazada de nodos.

  Estructura de Datos y Algoritmos
  Facultad de Informática
  Universidad Complutense de Madrid

 (c) Marco Antonio Gómez Martín, 2012
*/
#ifndef __COLA_LISTA_ENLAZADA_H
#define __COLA_LISTA_ENLAZADA_H

#include "Excepciones.h"

/**
 Implementación del TAD Cola utilizando una lista enlazada.

 Las operaciones son:

 - ColaVacia: -> Cola. Generadora implementada en el
   constructor sin parámetros.
 - PonDetras: Cola, Elem -> Cola. Generadora
 - quitaPrim: Cola - -> Cola. Modificadora parcial.
 - primero: Cola - -> Elem. Observadora parcial.
 - esVacia: Cola -> Bool. Observadora.
 - numElems: Cola -> Entero. Observadora.

 @author Marco Antonio Gómez Martín
 */
template <class T>
class Cola {
public:

	/** Constructor; operacion ColaVacia */
	Cola() : _prim(NULL), _ult(NULL), _numElems(0) {
	}

	/** Destructor; elimina la lista enlazada. */
	~Cola() {
		libera();
		_prim = _ult = NULL;
	}

	/**
	 Añade un elemento en la parte trasera de la cola.
	 Operación generadora.

	 @param elem Elemento a añadir.
	*/
	void ponDetras(const T &elem) {
		Nodo *nuevo = new Nodo(elem, NULL);

		if (_ult != NULL)
			_ult->_sig = nuevo;
		_ult = nuevo;
		// Si la cola estaba vacía, el primer elemento
		// es el que acabamos de añadir
		if (_prim == NULL)
			_prim = nuevo;
		_numElems++;
	}

	/**
	 Elimina el primer elemento de la cola.
	 Operación modificadora parcial, que falla si 
	 la cola está vacía.

	 quitaPrim(PonDetras(elem, ColaVacia)) = ColaVacia
	 quitaPrim(PonDetras(elem, xs)) = PonDetras(elem, quitaPrim(xs)) si !esVacia(xs)
	 error: quitaPrim(ColaVacia)
	*/
	void quitaPrim() {
		if (esVacia())
			throw EColaVacia();
		Nodo *aBorrar = _prim;
		_prim = _prim->_sig;
		delete aBorrar;
		--_numElems;
		// Si la cola se quedó vacía, no hay
		// último
		if (_prim == NULL)
			_ult = NULL;
	}

	/**
	 Devuelve el primer elemento de la cola. Operación
	 observadora parcial, que falla si la cola está vacía.

	 primero(PonDetras(elem, ColaVacia)) = elem
	 primero(PonDetras(elem, xs)) = primero(xs) si !esVacia(xs)
	 error: primero(ColaVacia)

	 @return El primer elemento de la cola.
	 */
	const T &primero() const {
		if (esVacia())
			throw EColaVacia();
		return _prim->_elem;
	}

	/**
	 Devuelve true si la cola no tiene ningún elemento.

	 esVacia(Cola) = true
	 esVacia(PonDetras(elem, p)) = false

	 @return true si la cola no tiene ningún elemento.
	 */
	bool esVacia() const {
		return _prim == NULL;
	}

	/**
	 Devuelve el número de elementos que hay en la
	 cola.
	 numElems(ColaVacia) = 0
	 numElems(PonDetras(elem, p)) = 1 + numElems(p)

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
	Cola(const Cola<T> &other) : _prim(NULL), _ult(NULL) {
		copia(other);
	}

	/** Operador de asignación */
	Cola<T> &operator=(const Cola<T> &other) {
		if (this != &other) {
			libera();
			copia(other);
		}
		return *this;
	}

	/** Operador de comparación. */
	bool operator==(const Cola<T> &rhs) const {
		if (_numElems != rhs._numElems)
			return false;
		Nodo *p1 = _prim;
		Nodo *p2 = rhs._prim;
		while ((p1 != NULL) && (p2 != NULL)) {
			if (p1->_elem != p2->_elem)
				return false;
			p1 = p1->_sig;
			p2 = p2->_sig;
		}

		return (p1 == NULL) && (p2 == NULL);
	}

	bool operator!=(const Cola<T> &rhs) const {
		return !(*this == rhs);
	}

protected:

	void libera() {
		libera(_prim);
	}

	void copia(const Cola &other) {

		if (other.esVacia()) {
			_prim = _ult = NULL;
			_numElems = 0;
		} else {
			Nodo *act = other._prim;
			Nodo *ant;
			_prim = new Nodo(act->_elem);
			ant = _prim;
			while (act->_sig != NULL) {
				act = act->_sig;
				ant->_sig = new Nodo(act->_elem);
				ant = ant->_sig;
			}
			_ult = ant;
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

	/** Puntero al primer elemento. */
	Nodo *_prim;

	/** Puntero al último elemento. */
	Nodo *_ult;

	/** Número de elementos */
	int _numElems;
};

#endif // __PILA_LISTA_ENLAZADA_H
