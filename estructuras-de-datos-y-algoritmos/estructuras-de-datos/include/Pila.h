/**
 * @author Sergio Revilla
 * @brief Implementación de una pila estática
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

template<class T>
class Pila {

public:
	static const int TAM_MAX = 100;

	/**
	 *
	 */
	Pila() {
		_numElems = 0;
	}

	~Pila() {

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
			throw EPilaLLena();
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
	 * @brief Indica si la pila es vacía
	 *
	 * esVacia(PilaVacia) = true
	 *
	 * esVacia(apila(p, e)) = false
	 */
	bool esVacia() const{
		return _numElems == 0;
	}

	/**
	 * @brief Devuelve el número de elementos de la pila
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
	Pila(const Pila<T> &p) {
		copia(p);
	}

	Pila<T> &operator =(const Pila<T> &p) {
		if (this != &p) {
			copia(p);
		}

		return *this;
	}

private:
	T _v[TAM_MAX];
	unsigned int _numElems;

	void copia(const Pila<T> &p) {
		_numElems = p._numElems;
		for (int i = 0; i < _numElems; ++i) {
			_v[i] = p._v[i];
		}
	}

};

#endif /* PILA_H_ */
