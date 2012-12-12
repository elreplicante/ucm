#include <iostream>
using namespace std;


int logaritmoEntero(int b, int n, int &p) {
	int m;

	if(n < b) {
		m = 0;
		p = 1;
	} else {
		m = logaritmoEntero(b * b, n, p);
		p = m;
		m = 2 * m;
		if (n >= p)
		{
			m = m + 1;
			p = p * b;
		}
	}

	return m;
}

int main() {
	int p;
	cout << logaritmoEntero(10, 100, p);


	return 0;
}