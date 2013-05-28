/*
 * domino.cpp
 *
 * Desarrollar un algoritmo que imprima sin repeticiones todas las cadenas circulares correctas de 28 fichas de domino
 *
 * Solucion: tupla de 28 elementos (k =[2..28])
 *
 *  Created on: 03/05/2012
 *      Author: repli
 */

#include <iostream>
using namespace std;

const int PROFUNDIDAD = 29;
const int N = 7;

void imprimir(int solucion[PROFUNDIDAD]) {
	cout << "(";
	for (int i = 0; i < PROFUNDIDAD; i++) {
		cout << solucion[i] << ", ";
	}
	cout << ")";
}

void domino_va(int solucion[], int k, bool usada[][N], bool &exito) {

	for (int j = 0; j < N && !exito; j++) {
		if (!usada[solucion[k - 1]][j]) {
			solucion[k] = j;
			//Marcamos
			usada[solucion[k - 1]][j] = true;
			usada[j][solucion[k - 1]] = true;
			if (k == PROFUNDIDAD - 1) {
				if (solucion[PROFUNDIDAD - 1] == solucion[0]) {
					imprimir(solucion);
					exito = true;
				}

			} else {
				domino_va(solucion, k + 1, usada, exito);
			}
			//Desmarcar
			usada[solucion[k - 1]][j] = false;
			usada[j][solucion[k - 1]] = false;
		}
	}
}

int main() {
	bool usada[N][N];
	int solucion[PROFUNDIDAD];

	for (int i = 0; i < PROFUNDIDAD; i++) {

			solucion[i] = 0;

		}

	solucion[0] = 6;
	solucion[1] = 6;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			usada[i][j] = false;
		}

	}

	usada[6][6] = true;
	bool exito = false;
	domino_va(solucion, 2, usada, exito);
	cout << "Terminado";

	return 0;

}

