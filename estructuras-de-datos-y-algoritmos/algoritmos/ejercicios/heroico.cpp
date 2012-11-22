#include <iostream>
using namespace std;


int heroicos(int V[], int n) {
	int i = 0;		//contador
	int h = 0;		//numero de heroicos
	int fib1 = 1;	// i + 1
	int fib2 = 1;	// i + 2
	int fib0 = 0;	// i

	while(i < n) {
		
		if(V[i] > fib0) {
			h = h + 1;
		}

		fib0 = fib1;
		fib1 = fib2;
		fib2 = fib0 + fib1;
		i++;
	}

	return h;
}

int main() {

	int V[] = {32, 1, 3, -5, 64};

	cout << heroicos(V, 5);
	

	return 0;
}