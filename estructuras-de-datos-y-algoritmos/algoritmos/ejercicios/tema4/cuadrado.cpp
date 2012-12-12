#include <iostream>
using namespace std;

int cuadrado(int n) {
	int c;
	if (n == 0) {
		c = 0;
	} else {
		c = cuadrado(n - 1) + 2 * (n - 1) +  1;
	}

	return c;
}

int main() {

	cout << cuadrado(1);
	return 0;
}