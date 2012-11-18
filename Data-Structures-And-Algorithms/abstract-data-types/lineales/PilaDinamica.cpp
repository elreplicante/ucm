/**
 * Todas las operaciones tienen un coste constante
 */

#include <iostream>

using namespace std;



#ifndef PILADINAMICA
#define PILADINAMICA

#include <iostream>
using namespace std;

#ifndef NODO
#define NODO

template<class E> class Nodo {
public:
	E _valor;
	Nodo<E>* _sig;

	Nodo() {
		_sig = NULL;
		_valor = NULL;
	}

	Nodo(const E &data) {
		_sig = NULL;
		_valor = data;
	}

	Nodo(const E &data, Nodo<E>* sig) {
		_sig = sig;
		_valor = data;
	}
};

#endif

template<class E> class PilaDinamica {
private:
	Nodo<E>* _cima; ///> Tope de la pila, elemento directamente accesible

public:
	PilaDinamica() {
		_cima = NULL; ///> Pila vacia, la cima no apunta a nada
	}

	/**
	 * En las funciones apilar y desapilar se utiliza un enlace auxiliar
	 * para hacer la reserva o liberar memoria.
	 */

	PilaDinamica &apilar(E elemento) {
		Nodo<E> *enlace = new Nodo<E>(elemento);
		enlace->_valor = elemento;
		enlace->_sig = _cima;
		_cima = enlace;

		return *this;
	}

	PilaDinamica &desapilar() {
		Nodo<E> *enlace;
		if (_cima == NULL)
			throw("Error, pila vacia");
		else {
			enlace = _cima;
			_cima = _cima->_sig;

		}
		delete enlace;
		return *this;
	}

	E cima() {
		if (_cima == NULL)
			throw("Error, pila vacia");
		else {
			return _cima->_valor;
		}
	}
	/**
	 * Corresponde al caso en el que la cima no apunta a nada
	 */
	bool pilaVacia() {
		return _cima == NULL;
	}


	friend class Nodo<E>;
};

#endif
/*

int main() {

	PilaDinamica<int> miPila = PilaDinamica<int>();
	miPila.apilar(1).apilar(2).apilar(3);
	miPila.desapilar();
	cout << miPila.pilaVacia();
	return 0;
}
*/
