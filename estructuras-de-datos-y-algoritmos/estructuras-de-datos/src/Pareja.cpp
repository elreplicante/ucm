/*
 * Pareja.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#include "../include/Pareja.h"


template<class T>
Pareja<T>::Pareja(T a, T b) {
}

template<class T>
T Pareja<T>::primero() const {
}

template<class T>
T Pareja<T>::segundo() const {
}

template<class T>
T Pareja<T>::getMax() const{
	T result;
	result = (_a > _b) ? _a : _b;
	return result;
}

template<class T>
Pareja<T>::~Pareja() {
}

