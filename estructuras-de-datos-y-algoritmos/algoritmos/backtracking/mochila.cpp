/*
 * mochila.cpp
 *
 *  Created on: 09/05/2012
 *      Author: repli
 */

#include <iostream>

using namespace std;

int estimacion(int k) {
	int estimacion;
	for (int i = k; i < N; ++i) {
		if (valores[k]/pesos[k] )

	}
}

void mochila(bool sol[N], int valor, int peso, int k, bool solMejor[N],
		int &valorMejor) {
	//Cogemos el k-esimo
	sol[k] = true;
	valor += valores[k];
	peso += pesos[k];
	if (peso <= M) {
		if (k == N -1) {
			if (valor > valorMejor) {
				copiar(solMejor, sol);
				valorMejor = mejor;
			} else {
				mochila(sol, valor, peso, k + 1, solMejor, valorMejor);
			}
		}

	}

	sol[k] = false;
	peso -= pesos[k];
	valor -= valores[k];
	if (k == N - 1) {
		if (valor > valorMejor) {
			copiar(solMejor, sol);
			valorMejor = valor;

		}
	}
	else {
		mochila(sol, valor, peso, k + 1, solMejor, valorMejor);
	}
}

