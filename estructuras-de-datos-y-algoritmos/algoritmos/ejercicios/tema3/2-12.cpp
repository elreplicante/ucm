#include <iostream>
#include <cmath>

using namespace std;


int log(int b, int n) {

	int r = 0;
	int p = b;

	// {b^r <= n and p=b^(r-1)}

	while (p <= n) {
		
		p = p * b;
		r = r + 1;
	}

	return r;
}

int main() {

	cout << log(10, 100) << endl;

	return 0;
}