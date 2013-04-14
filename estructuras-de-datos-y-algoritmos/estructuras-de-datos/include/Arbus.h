/**
  @file Arbus.h

  Implementación dinámica del TAD Arbol de Búsqueda.

  Estructura de Datos y Algoritmos
  Facultad de Informática
  Universidad Complutense de Madrid

 (c) Marco Antonio Gómez Martín, 2012
*/
#ifndef __ARBUS_H
#define __ARBUS_H

#include "Excepciones.h"

#include "Lista.h" // Tipo devuelto por los recorridos

#include "Pila.h" // Usado internamente por los iteradores

/**
 Implementación dinámica del TAD Arbus utilizando 
 nodos con un puntero al hijo izquierdo y otro al
 hijo derecho.

 Las operaciones son:

 - ArbusVacio: operación generadora que construye
 un árbol de búsqueda vacío.

 - Inserta(clave, valor): generadora que añade una 
 nueva pareja (clave, valor) al árbol. Si la
 clave ya estaba se sustituye el valor.

 - borra(clave): operación modificadora. Elimina la
 clave del árbol de búsqueda.  Si la clave no está,
 la operación no tiene efecto.

 - consulta(clave): operación observadora que devuelve
 el valor asociado a una clave. Es un error preguntar
 por una clave que no existe.

 - esta(clave): operación observadora. Sirve para
 averiguar si se ha introducido una clave en el
 árbol.

 - esVacio(): operacion observadora que indica si
 el árbol de búsqueda tiene alguna clave introducida.

 @author Marco Antonio Gómez Martín
 */
template <class Clave, class Valor>
class Arbus {
private:
	/**
	 Clase nodo que almacena internamente la pareja (clave, valor)
	 y los punteros al hijo izquierdo y al hijo derecho.
	 */
	class Nodo {
	public:
		Nodo() : _iz(NULL), _dr(NULL) {}
		Nodo(const Clave &clave, const Valor &valor) 
			: _clave(clave), _valor(valor), _iz(NULL), _dr(NULL) {}
		Nodo(Nodo *iz, const Clave &clave, const Valor &valor, Nodo *dr)
			: _clave(clave), _valor(valor), _iz(iz), _dr(dr) {}

		Clave _clave;
		Valor _valor;
		Nodo *_iz;
		Nodo *_dr;
	};

public:

	/** Constructor; operacion ArbolVacio */
	Arbus() : _ra(NULL) {
	}

	/** Destructor; elimina la estructura jerárquica de nodos. */
	~Arbus() {
		libera();
		_ra = NULL;
	}

	/**
	 Operación generadora que añade una nueva clave/valor
	 a un árbol de búsqueda.
	 @param clave Clave nueva.
	 @param valor Valor asociado a esa clave. Si la clave
	 ya se había insertado previamente, sustituimos el valor
	 viejo por el nuevo.
	 */
	void inserta(const Clave &clave, const Valor &valor) {
		_ra = insertaAux(clave, valor, _ra);
	}

	/**
	 Operación modificadora que elimina una clave del árbol.
	 Si la clave no existía la operación no tiene efecto.

	   borra(elem, ArbusVacio) = ArbusVacio
	   borra(e, inserta(c, v, arbol)) = 
	                     inserta(c, v, borra(e, arbol)) si c != e
	   borra(e, inserta(c, v, arbol)) = borra(e, arbol) si c == e

	 @param clave Clave a eliminar.
	 */
	void borra(const Clave &clave) {
		_ra = borraAux(_ra, clave);
	}

	/**
	 Operación observadora que devuelve el valor asociado
	 a una clave dada.

	 consulta(e, inserta(c, v, arbol)) = v si e == c
	 consulta(e, inserta(c, v, arbol)) = consulta(e, arbol) si e != c
	 error consulta(ArbusVacio)

	 @param clave Clave por la que se pregunta.
	 */
	const Valor &consulta(const Clave &clave) {
		Nodo *p = buscaAux(_ra, clave);
		if (p == NULL)
			throw EClaveErronea();

		return p->_valor;
	}

	/**
	 Operación observadora que permite averiguar si una clave
	 determinada está o no en el árbol de búsqueda.

	 esta(e, ArbusVacio) = false
	 esta(e, inserta(c, v, arbol)) = true si e == c
	 esta(e, inserta(c, v, arbol)) = esta(e, arbol) si e != c

	 @param clave Clave por la que se pregunta.
	 */
	bool esta(const Clave &clave) {
		return buscaAux(_ra, clave) != NULL;
	}

	/**
	 Operación observadora que devuelve si el árbol
	 es vacío (no contiene elementos) o no.

	 esVacio(ArbusVacio) = true
	 esVacio(inserta(c, v, arbol)) = false
	 */
	bool esVacio() const {
		return _ra == NULL;
	}

	// //
	// OPERACIONES RELACIONADAS CON LOS ITERADORES
	// //

	/**
	 Clase interna que implementa un iterador sobre
	 la lista que permite recorrer la lista e incluso
	 alterar el valor de sus elementos.
	 */
	class Iterador {
	public:
		void avanza() {
			if (_act == NULL) throw EAccesoInvalido();

			// Si hay hijo derecho, saltamos al primero
			// en inorden del hijo derecho
			if (_act->_dr)
				_act = primeroInOrden(_act->_dr);
			else {
				// Si no, vamos al primer ascendiente
				// no visitado. Para eso consultamos
				// la pila; si ya está vacía, no quedan
				// ascendientes por visitar
				if (_ascendientes.esVacia())
					_act = NULL;
				else {
					_act = _ascendientes.cima();
					_ascendientes.desapila();
				}
			}
		}

		const Clave &clave() const {
			if (_act == NULL) throw EAccesoInvalido();
			return _act->_clave;
		}

		const Valor &valor() const {
			if (_act == NULL) throw EAccesoInvalido();
			return _act->_valor;
		}

		bool operator==(const Iterador &other) const {
			return _act == other._act;
		}

		bool operator!=(const Iterador &other) const {
			return !(this->operator==(other));
		}
	protected:
		// Para que pueda construir objetos del
		// tipo iterador
		friend class Arbus;

		Iterador() : _act(NULL) {}
		Iterador(Nodo *act) {
			_act = primeroInOrden(act);
		}

		/**
		 Busca el primer elemento en inorden de
		 la estructura jerárquica de nodos pasada
		 como parámetro; va apilando sus ascendientes
		 para poder "ir hacia atrás" cuando sea necesario.
		 @param p Puntero a la raíz de la subestructura.
		 */
		Nodo *primeroInOrden(Nodo *p) {
			if (p == NULL)
				return NULL;

			while (p->_iz != NULL) {
				_ascendientes.apila(p);
				p = p->_iz;
			}
			return p;
		}

		// Puntero al nodo actual del recorrido
		// NULL si hemos llegado al final.
		Nodo *_act;

		// Ascendientes del nodo actual
		// aún por visitar
		Pila<Nodo*> _ascendientes;
	};
	
	/**
	 Devuelve el iterador al principio de la lista.
	 @return iterador al principio de la lista;
	 coincidirá con final() si la lista está vacía.
	 */
	Iterador principio() {
		return Iterador(_ra);
	}

	/**
	 @return Devuelve un iterador al final del recorrido
	 (fuera de éste).
	 */
	Iterador final() const {
		return Iterador(NULL);
	}


	// //
	// MÉTODOS DE "FONTANERÍA" DE C++ QUE HACEN VERSÁTIL
	// A LA CLASE
	// //

	/** Constructor copia */
	Arbus(const Arbus<Clave, Valor> &other) : _ra(NULL) {
		copia(other);
	}

	/** Operador de asignación */
	Arbus<Clave, Valor> &operator=(const Arbus<Clave, Valor> &other) {
		if (this != &other) {
			libera();
			copia(other);
		}
		return *this;
	}

protected:

	/**
	 Constructor protegido que crea un árbol
	 a partir de una estructura jerárquica de nodos
	 previamente creada.
	 Se utiliza en hijoIz e hijoDr.
	 */
	Arbus(Nodo *raiz) : _ra(raiz) {
	}

	void libera() {
		libera(_ra);
	}

	void copia(const Arbus &other) {
		_ra = copiaAux(other._ra);
	}

private:

	/**
	 Elimina todos los nodos de una estructura arbórea
	 que comienza con el puntero ra.
	 Se admite que el nodo sea NULL (no habrá nada que
	 liberar).
	 */
	static void libera(Nodo *ra) {
		if (ra != NULL) {
			libera(ra->_iz);
			libera(ra->_dr);
			delete ra;
		}
	}

	/**
	 Copia la estructura jerárquica de nodos pasada
	 como parámetro (puntero a su raiz) y devuelve un
	 puntero a una nueva estructura jerárquica, copia
	 de anterior (y que, por tanto, habrá que liberar).
	 */
	static Nodo *copiaAux(Nodo *ra) {
		if (ra == NULL)
			return NULL;

		return new Nodo(copiaAux(ra->_iz),
						ra->_clave, ra->_valor,
						copiaAux(ra->_dr));
	}

	/**
	 Inserta una pareja (clave, valor) en la estructura
	 jerárquica que comienza en el puntero pasado como parámetro.
	 Ese puntero se admite que sea NULL, por lo que se creará
	 un nuevo nodo que pasará a ser la nueva raíz de esa
	 estructura jerárquica. El método devuelve un puntero a la
	 raíz de la estructura modificada. En condiciones normales
	 coincidirá con el parámetro recibido; sólo cambiará si
	 la estructura era vacía.
	 @param clave Clave a insertar. Si ya aparecía en la
	 estructura de nodos, se sobreescribe el valor.
	 @param valor Valor a insertar.
	 @param p Puntero al nodo raíz donde insertar la pareja.
	 @return Nueva raíz (o p si no cambia).
	 */
	static Nodo *insertaAux(const Clave &clave, const Valor &valor, Nodo *p) {

		if (p == NULL) {
			return new Nodo(clave, valor);
		} else if (p->_clave == clave) {
			p->_valor = valor;
			return p;
		} else if (clave < p->_clave) {
			p->_iz = insertaAux(clave, valor, p->_iz);
			return p;
		} else { // (clave > p->_clave)
			p->_dr = insertaAux(clave, valor, p->_dr);
			return p;
		}
	}

	/**
	 Busca una clave en la estructura jerárquica de
	 nodos cuya raíz se pasa como parámetro, y devuelve
	 el nodo en la que se encuentra (o NULL si no está).
	 @param p Puntero a la raíz de la estructura de nodos
	 @param clave Clave a buscar
	 */
	static Nodo *buscaAux(Nodo *p, const Clave &clave) {
		if (p == NULL)
			return NULL;

		if (p->_clave == clave)
			return p;

		if (clave < p->_clave)
			return buscaAux(p->_iz, clave);
		else
			return buscaAux(p->_dr, clave);
	}

	/**
	 Elimina (si existe) la clave/valor de la estructura jerárquica
	 de nodos apuntada por p. Si la clave aparecía en la propia raíz,
	 ésta cambiará, por lo que se devuelve la nueva raíz. Si no cambia
	 se devuelve p.

	 @param p Raíz de la estructura jerárquica donde borrar la clave.
	 @param clave Clave a borrar.
	 @return Nueva raíz de la estructura, tras el borrado. Si la raíz
	 no cambia, se devuelve el propio p.
	*/
	static Nodo *borraAux(Nodo *p, const Clave &clave) {

		if (p == NULL)
			return NULL;

		if (clave == p->_clave) {
			return borraRaiz(p);
		} else if (clave < p->_clave) {
			p->_iz = borraAux(p->_iz, clave);
			return p;
		} else { // clave > p->_clave
			p->_dr = borraAux(p->_dr, clave);
			return p;
		}
	}

	/**
	 Borra la raíz de la estructura jerárquica de nodos
	 y devuelve el puntero a la nueva raíz que garantiza
	 que la estructura sigue siendo válida para un árbol de
	 búsqueda (claves ordenadas).
	 */
	static Nodo *borraRaiz(Nodo *p) {

		Nodo *aux;

		// Si no hay hijo izquierdo, la raíz pasa a ser
		// el hijo derecho
		if (p->_iz == NULL) {
			aux = p->_dr;
			delete p;
			return aux;
		} else
		// Si no hay hijo derecho, la raíz pasa a ser
		// el hijo izquierdo
		if (p->_dr == NULL) {
			aux = p->_iz;
			delete p;
			return aux;
		} else {
		// Convertimos el elemento más pequeño del hijo derecho
		// en la raíz.
			return mueveMinYBorra(p);
		}
	}

	/**
	 Método auxiliar para el borrado; recibe un puntero a la
	 raíz a borrar. Busca el elemento más pequeño del hijo derecho
	 que se convertirá en la raíz (que devolverá), borra la antigua
	 raíz (p) y "cose" todos los punteros, de forma que ahora:

	   - El mínimo pasa a ser la raíz, cuyo hijo izquierdo y
	     derecho eran los hijos izquierdo y derecho de la raíz
	     antigua.
	   - El hijo izquierdo del padre del elemento más pequeño
	     pasa a ser el antiguo hijo derecho de ese mínimo.
	 */
	static Nodo *mueveMinYBorra(Nodo *p) {

		// Vamos bajando hasta que encontramos el elemento
		// más pequeño (aquel que no tiene hijo izquierdo).
		// Vamos guardando también el padre (que será null
		// si el hijo derecho es directamente el elemento
		// más pequeño).
		Nodo *padre = NULL;
		Nodo *aux = p->_dr;
		while (aux->_iz != NULL) {
			padre = aux;
			aux = aux->_iz;
		}

		// aux apunta al elemento más pequeño.
		// padre apunta a su padre (si el nodo es hijo izquierdo)

		// Dos casos dependiendo de si el padre del nodo con 
		// el mínimo es o no la raíz a eliminar
		// (=> padre != NULL)
		if (padre != NULL) {
			padre->_iz = aux->_dr;
			aux->_iz = p->_iz;
			aux->_dr = p->_dr;
		} else {
			aux->_iz = p->_iz;
		}

		delete p;
		return aux;
	}

	/** 
	 Puntero a la raíz de la estructura jerárquica
	 de nodos.
	 */
	Nodo *_ra;
};

#endif // __Arbus_H
