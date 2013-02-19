/*
 * VectorDinamico.h
 *
 *  Created on: 19/02/2013
 *      Author: repli
 */

#ifndef VECTORDINAMICO_H_
#define VECTORDINAMICO_H_
#include <assert.h>

template<class T>
class VectorDinamico {

private:
	/**
	 * Puntero al array que contiene los datos
	 */

	T *_v;
	/**
	 * Tamaño del vector _v.
	 */

	unsigned int _tam;

	/**
	 * Número de elementos reales guardados.
	 */
	unsigned int _numElems;

public:
	/**
	 * Tamaño inicial del vector dinámico
	 */
	enum {
		TAM_INICIAL = 10
	};

	/**
	 * Constructor
	 */
	VectorDinamico() :
			_v(new T[TAM_INICIAL]), _tam(TAM_INICIAL), _numElems(0) {
	}

	/**
	 * Destructor
	 */
	~VectorDinamico() {
		delete[] _v;
	}

protected:
	/**
	 * Duplica el tamaño del vector
	 */

	void amplia() {
		T *viejo = _v;
		_tam *= 2;
		_v = new T[_tam];
		for (unsigned int i = 0; i < _numElems; ++i)
			_v[i] = viejo[i];
		delete[] viejo;
	}
	/**
	 * Elimina un elemento del vector; compacta los elementos al principio del vector .
	 * @param pos En el intervalo 0..numElems−1.
	 */

	void quitaElem(int pos) {
		assert((0 <= pos) && (pos < _numElems));
		_numElems--;
		for (int i = pos; i < _numElems; ++i)
		_v[i] = _v[i+1];
	}
};



#endif /* VECTORDINAMICO_H_ */
