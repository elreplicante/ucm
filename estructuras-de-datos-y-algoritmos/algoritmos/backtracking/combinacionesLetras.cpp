/*
 * Dadas n letras, todas ellas diferentes y m <= n, disenar un algoritmo que calcule todas las palabras con m letras diferentes escogidas entre las dadas
 *
 * Lo que pide el ejercicio es encontrar las variaciones de n elementos, tomadas de m en m
 *
 *
 *
 *  Created on: 03/05/2012
 *      Author: repli
 */

#include <iostream>
using namespace std;

const int M = 2;  // Combinaciones de letras
const int N = 50; // Numero de letras

typedef struct {
	char a, b;

}tDupla;

bool noRepetida(int solucion[M], int k){
	for (int i = 0; i < k; i++) {
		if (solucion[i] == solucion[k])
			return false;
	}
	return true;
}

void imprimirLetras(int solucion[M]) {
	cout << "(";
	for (int i = 0; i < M; ++i) {
		cout << solucion[i] << ",";
	}
	cout << ")" << endl;
}
void variaciones(int solucion[M], int k, int n, int m) {
	for (int letra = 0; letra < n; letra++) {
		solucion[k] = letra;
		if (noRepetida(solucion, k)) {
			if (k == m - 1) {
				imprimirLetras(solucion);
			}

			else
				variaciones(solucion, k + 1, n, m);
		}

	}
}



//int main() {
//
//
//	int solucion[M];
//
//	variaciones(solucion, 0, N, M);
//	return 0;
//}
