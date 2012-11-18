/*
 * nQueens.cpp
 *
 *  Created on: 27/04/2012
 *      Author: repli
 */


#include <iostream>
#include <cmath>

using namespace std;

bool esSolucion(int n, int k) {
	return (k == n -1);
}

bool safe(int sol[], int k){
	for (int i = 0; i < k; i++) {
		if(sol[k] == sol[i] || abs(sol[k] - sol[i]) == abs(k - i))
				return false;
	}
	return true;
}

void tratarSolucion(int sol[], int n) {

	cout << "Solucion: " << endl;
	for (int i = 0; i < n; i++) {
		cout << sol[i] << " ";
		cout << endl;
	}


}

void nQueens(int n, int solution[], int k){

	for (int i = 0; i < n; i++) {
		solution[k] = i;
		if (safe(solution, k)) {
			if (esSolucion(n, k)) {
				if (esSolucion(n, k))
					tratarSolucion(solution, k);
			}
			else {
				nQueens(n, solution, k + 1);
			}

		}
	}
}

void nQueens (int n) {
	int sol[n];
	nQueens(n, sol, 0);
}


//int main() {
//	nQueens(9);
//	return 0;
//}

