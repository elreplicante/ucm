#include <iostream>
#include <algorithm>
using namespace std;

const int n = 10;

void transponerIguales(int vector[], int c1, int c2, int t) {
	int i = 0;
	while (i < t - 1) {
		swap(vector[c1 + i], vector[c2 + i]);
		i++;
	}
}

void transponer(int vector[], int k) {
	int inicio = 1;
	int mitad = k + 1;
	int f = n;
	int i = mitad - inicio;
	int d = f - mitad + 1;

	while (i != d) {
		if (i < d) {
			transponerIguales(vector, inicio, f - i + 1, i);
			f = f - i;
			d = d - i;
		} else {
			transponerIguales(vector, inicio, mitad, d);
			inicio = inicio + d;
			i = i - d;
		}
	}

	transponerIguales(vector, inicio, mitad, i);
}

int main() {

	int vector[n] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	for (int i = 0; i < n; ++i) {
		cout << vector[i] << ' ';
	}

	cout << endl;

	transponer(vector, 3);

	for (int i = 0; i < n; ++i) {
		cout << vector[i] << ' ';
	}

	return 0;
}

