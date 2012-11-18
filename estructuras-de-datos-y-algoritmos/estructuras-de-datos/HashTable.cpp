/*
 * HashTable.cpp
 *
 *  Created on: 17/05/2012
 *      Author: repli
 */

using namespace std;

const int N = 10;

typedef struct {
	int valor;
	bool borrado[N];

} tCasilla;

typedef struct {
	int claves[N] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	tCasilla casillas[N];
} tHashTable;

int tabla[N];

void insertar(int numero, tHashTable hash) {
	int clave = numero % 10;

	while (hash.casillas[clave] != -1 && clave < N) {
		clave++;
	}
	if (clave < N) {
		hash.casillas[clave].valor = numero;
	}
	hash.casillas[clave].valor = numero;
	if (hash.casillas[clave] != -1) {

	} else {
	if (hash.casillas[clave ] != -1)

}

}

