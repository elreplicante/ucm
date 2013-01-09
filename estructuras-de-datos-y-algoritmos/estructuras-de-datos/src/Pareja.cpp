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
	if (_a > _b) {
		return _a;
	} else {
		return _b;
	}
}

template<class T>
Pareja<T>::~Pareja() {
}

