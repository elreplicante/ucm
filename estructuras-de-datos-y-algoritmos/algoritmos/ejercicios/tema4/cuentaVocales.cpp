#include <iostream>
using namespace std;

/*

Pre: n = lon(V) >= 0

Post: numVocales = #i : 0 <= i < n : esVocal(V[i])
		esVocal(x) = {x = a || x = e || x = i || x = o || x = u}


	Coste:

		c0				n = 0
		T(n - 1) + c1	n > 0	
*/

int esVocal(char c) {
	int n = 0;
	if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' )
		n = 1;
	return n;
}

int cuentaVocales(char V[], int n) {
	int numVocales;
	if (n == 0) {
		numVocales = 0;
	} else {
		numVocales = cuentaVocales(V, n - 1) + esVocal(V[n - 1]);
	}

	return numVocales;
}

int main() {

	char V[] = {'c', 'a', 's', 'a', 'a', 'z', 'f'};

	cout << cuentaVocales(V, 7);
	return 0;
}