/**
 * Algoritmo que verifique si en un vector ordenado existe un elemento i : V[i] = i
 */

#include <iostream>
using namespace std;

bool coincide(int lista[], int inicio, int final) {
	bool existe;
	if (inicio > final) {
		existe = false;
	} else if (inicio == final) {
		existe = (lista[inicio] == inicio);
	} else if (inicio < final) {
		int mitad = (inicio + final / 2);
		if (mitad < lista[mitad]) {
			existe = coincide(lista, inicio, mitad - 1);
		} else if (mitad == lista[mitad]) {
			existe = true;
		} else if (mitad > lista[mitad]) {
			existe = coincide(lista, mitad + 1, final);
		}

	}

	return existe;
}

int main() {

	int malo[] = { 1, 1, 3 };
	int bueno[] = { 0, 5, 6 };

	bool no = coincide(malo, 0, 2);
	bool si = coincide(bueno, 0, 2);

	cout << no << endl;
	cout << si;

}

