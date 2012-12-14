#include <iostream>
using namespace std;


int sumaSubVector(int V[], int a, int b) {
	int s;
	int m = (a + b) / 2;

	if (a >= b) {
		s = V[a];
	} else {
		
		s = sumaSubVector(V, a, (a + b) / 2) + sumaSubVector(V, m + 1, b);
	}

	return s;
}

int main() {


	int V[] = {10, 21, 2, 8, -7, 42, 58};

	cout << sumaSubVector(V, 0, 0);


	return 0;
}