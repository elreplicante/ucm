/*
 * PilaVectorDinamico.h
 *
 *  Created on: 20/02/2013
 *      Author: repli
 */

#ifndef PILAVECTORDINAMICO_H_
#define PILAVECTORDINAMICO_H_
#include "Nodo.h"
#include <iostream>

template<class T>
class PilaVectorDinamico {

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

	T _cima;

public:
	/**
	 * Tamaño inicial del vector dinámico
	 */
	enum {
		TAM_INICIAL = 10
	};

	/**
	 * Constructor por defecto
	 */
	PilaVectorDinamico() :
			_v(new T[TAM_INICIAL]), _tam(TAM_INICIAL), _numElems(0), _cima(NULL) {
	}

	/**
	 * Constructor por defecto
	 */
	PilaVectorDinamico(T elem) :
			_v(new T[TAM_INICIAL]), _tam(TAM_INICIAL), _numElems(0), _cima(elem) {
	}

	/**
	 * Destructor
	 */
	~PilaVectorDinamico() {
		delete[] _v;
	}

	/**
	 * Coloca un elemento en la cima
	 *
	 * @param elem El elemento a apilar
	 */
	void apila(const T &elem) {

		if (_numElems == _tam) {
			amplia();
		}

		_v[_numElems] = elem;
		_numElems++;
	}

	/**
	 * Decrementa el indice de _numElems pero no borra el elemento del array
	 */
	void desapila() {
		if (_numElems == 0)
			throw EPilaVacia();

		_numElems--;
	}

	const T &cima() {
		return _cima;
	}

	bool esVacia() {
		return _numElems == 0;
	}

	void imprime() {
		for (unsigned int i = 0; i < _numElems; i++) {
			std::cout << _v[i] << '\n';
		}
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
};

#endif /* PILAVECTORDINAMICO_H_ */
