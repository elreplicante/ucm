//============================================================================
// Name        : practica2.cpp
// Author      : Sergio Revilla
//============================================================================

#include <iostream>
#include "Pila.h"
using namespace std;

int main() {
	int wagons = -1, expected[10000];
	int i;

	while (wagons != 0) {
		cin >> wagons;

		for (i = 0; i < wagons; i++) {
				cin >> expected[i];
			}

			int currentWagon = 1;
			int expectedIndex = 0;
			Pila<int> station;
			while (currentWagon <= wagons) {
				station.apila(currentWagon);
				while (!station.esVacia()
						&& station.cima() == expected[expectedIndex] &&
						expectedIndex < wagons) {
					station.desapila();
					expectedIndex++;
				}
				currentWagon++;
			}

			if (station.esVacia())
				cout << "POSIBLE" << endl;
			else
				cout << "IMPOSIBLE" << endl;
		}

	return 0;
}
