#include <iostream>
using namespace std;


int productoMayor (int V[], int n, int b) {
	int r;
	if (n == 0) {
		r = 1;
	} else {
		if (V[n - 1] > b) {
			r = productoMayor(V, n - 1, b) * V[n - 1];
		} else {
			r = productoMayor(V, n - 1, b);
		}
	}

	return r;
}

int main() {

	int V[] = {1, 2, 3, 4, 3};

	cout << productoMayor(V, 5, 1);

	return 0;

}