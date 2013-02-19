/*
 * Pila.h
 *
 *  Created on: 19/02/2013
 *      Author: repli
 *
 *  Invariante de la representación:
 *  RPila(p) <=> def
 *  	0 <= p._numElems <= p._tam &&
 *  	ParaTodo i : 0 <= i <= p._numElems : RT(p - V[i])
 *
 *  Relación de equivalencia
 *  p1 = p2 <=>
 *  	p1._numElem = p2._numElem &&
 *  	ParaTodo i : 0 <= i <= p._numElem : p1._V[i] = p2._V[i]
 */

#ifndef PILA_H_
#define PILA_H_




#endif /* PILA_H_ */


template <class T>
class Pila {

private:
	T* _V;
	int _tam;
	int _numElem;

public:
	bool esVacia() {
		return _numElem == 0;
	}

	bool pilaLlena() {
		return _numElem == _tam;
	}




};
