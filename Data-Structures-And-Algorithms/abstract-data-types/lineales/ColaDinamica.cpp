/*
 * ColaDinamica.cpp
 *
 *  Created on: 06/06/2012
 *      Author: repli
 */

#include <iostream>
using namespace std;

#ifndef COLADINAMICA
#define COLADINAMICA

template<class E> class ColaDinamica {
private:
	struct Nodo {
		E elemento;
		struct Nodo* siguiente; // coloca el nodo en la segunda posici—n
	}* primero;
	struct Nodo* ultimo;
	unsigned int elementos;
public:
	ColaDinamica() {
		elementos = 0;
		primero = NULL;
				ultimo = NULL;
	}

	~ColaDinamica() {
		while (elementos != 0)
			pop();
	}

	void push(const E& elem) {
		Nodo* aux = new Nodo;
		aux->elemento = elem;
		if (elementos == 0)
			primero = aux;
		else
			ultimo->siguiente = aux;
		ultimo = aux;
		++elementos;
	}

	void pop() {
		Nodo* aux = primero;
		primero = primero->siguiente;
		delete aux;
		--elementos;
	}

	E consultarPrimero() const {
		return primero->elemento;
	}

	bool vacia() const {
		return elementos == 0;
	}

	unsigned int size() const {
		return elementos;
	}

	ColaDinamica copiarCola() {

		//TODO revisar
		Nodo* r, *s, *t;
		ColaDinamica<E> copia = ColaDinamica<E>();

		if (primero == NULL) {
			copia.primero = NULL;
			copia.ultimo = NULL;
		}
		else {
			r = primero;
			t = new Nodo;
			t->elemento = r->elemento;
			s = t;
			copia.primero = t;
			while (r->siguiente != NULL) {
				r = r->siguiente;
				t = new Nodo;
				t->elemento = r->elemento;
				s->siguiente = t;
				s = t;
			}
			s->siguiente = NULL;
			copia.ultimo = s;
		}

		return copia;
	}

	void anularCola(){
		Nodo *p, *q;
		p = primero;
		while(p != NULL){
			q = p;
			p = p->siguiente;
			q->elemento = NULL;
			delete q;
		}
		primero = NULL;
		ultimo = NULL;
	}
};

#endif

//
//int main() {
//
//	ColaDinamica<int> colaDinamica = ColaDinamica<int>();
//	colaDinamica.push(1);
//	colaDinamica.push(3);
//	cout << colaDinamica.consultarPrimero() << " " << colaDinamica.size() << endl;
//
//	ColaDinamica<int> copiaCola = colaDinamica.copiarCola();
//	cout << copiaCola.consultarPrimero() << " " << copiaCola.size() << endl;
//	return 0;
//}
