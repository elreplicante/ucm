/**
 * @author Sergio Revilla
 * @brief Implementaci��n de una pila est��tica
 *
 * - PilaVacia: -> Pila (Generadora)
 * - apila: Pila, Elem --> Pila (Generadora parcial)
 * - desapila: Pila --> Pila (Modificadora parcial)
 * - cima: Pila --> Elem (Observadora parcial)
 * - esVacia: Pila -> bool (Observadora)
 * - numElems: Pila -> int (Observadora)
 *
 *
 */


#ifndef PILA_H_
#define PILA_H_

#include "ExcepcionTAD.h"
using namespace std;

template<class T>
class PilaE {

public:
	static const int TAM_MAX = 100;

	/**
	 *
	 */
	PilaE() {
		_numElems = 0;
	}

	~PilaE() {

	}

	/**
	 * @brief Apila un elemento
	 * @param elem El elemento a apilar
	 *
	 * desapila(apila(p, e)) = p
	 *
	 * error: apila(p, e) cuando numElems(p) = TAM_MAX
	 */
	void apila(const T &elem) {
		if (_numElems == TAM_MAX)
			throw EPilaLlena();
		_v[_numElems] = elem;
		_numElems++;
	}

	/**
	 * @brief Desapila un elemento
	 *
	 * desapila(apila(p, e)) = p
	 * error: desapila(PilaVacia)
	 */
	void desapila() {
		if (esVacia())
			throw EPilaVacia();
		_numElems--;

	}

	/**
	 * @brief Devuelve el elemento de la cima
	 *
	 * cima(apila(p, e)) = e
	 *
	 * error: cima(PilaVacia)
	 */
	const T &cima() const{
		if (esVacia())
			throw EPilaVacia();
		return _v[_numElems - 1];
	}

	/**
	 * @brief Indica si la pila es vac��a
	 *
	 * esVacia(PilaVacia) = true
	 *
	 * esVacia(apila(p, e)) = false
	 */
	bool esVacia() const{
		return _numElems == 0;
	}

	/**
	 * @brief Devuelve el n��mero de elementos de la pila
	 *
	 * numElems(PilaVacia) =  0
	 *
	 * numElems(apila(p, e)) = 1 + numElems(p)
	 */
	int numElems() const {
		return _numElems;
	}

	/**
	 * @brief Constructor por copia
	 */
	PilaE(const PilaE<T> &p) {
		copia(p);
	}

	PilaE<T> &operator =(const PilaE<T> &p) {
		if (this != &p) {
			copia(p);
		}

		return *this;
	}

	/** Operador de comparación. */
		bool operator==(const PilaE<T> &rhs) const {
			if (_numElems != rhs._numElems)
				return false;

			for (unsigned int i = 0; i < _numElems; ++i)
				if (_v[i] != rhs._v[i])
					return false;

			return true;
		}

		bool operator!=(const PilaE<T> &rhs) const {
			return !(*this == rhs);
		}
/*
		std::ostream operator <<(const PilaE<T> &rhs) const{
			cout << "(";
			for(int i=0; i < _numElems; i++) {
				cout << i << ",";
			}

			cout << ")";
			return;
		}

*/

	protected:

		void copia(const PilaE<T> &other) {
			_numElems = other._numElems;
			for (unsigned int i = 0; i < _numElems; ++i)
				_v[i] = other._v[i];
		}

private:
	T _v[TAM_MAX];
	unsigned int _numElems;

};

#endif /* PILA_H_ */
