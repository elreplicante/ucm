#include <iostream>
using namespace std;
#define N 5

int Laberinto[N][N] = { { 0, 0, 0, 1, 1 }, { 1, 1, 0, 1, 0 }, { 1, 0, 0, 1, 0 },
		{ 1, 0, 1, 1, 1 }, { 1, 0, 0, 0, 0 } };

struct Casilla {
	int x;
	int y;
};

Casilla nextPosition(Casilla pos, int dir) {
	switch (dir) {
	case 0:
		++pos.y;
		break;
	case 1:
		++pos.x;
		break;
	case 2:
		--pos.y;
		break;
	case 3:
		--pos.x;
		break;

	default:
		break;
	}
	return pos;
}
bool isValid(Casilla pos, bool marcas[][N]) {
	return (pos.x >= 0 && pos.x < N && pos.y >= 0 && pos.y < N
			&& Laberinto[pos.x][pos.y]) == 0 && !marcas[pos.x][pos.y];

}
bool isSolution(Casilla pos) {
	return pos.x == N - 1 && pos.y == N - 1;
}
void printSolution(Casilla sol[], int k) {
	for (int i = 0; i < k + 1; i++) {
		cout << " -> " << "(" << sol[i].x << ", " << sol[i].y << ")";
	}
	cout << endl;
}

void laberintoVA(Casilla sol[], int k, bool marcas[][N]) {
	for (int dir = 0; dir < 4; dir++) {
		sol[k] = nextPosition(sol[k - 1], dir);
		if (isValid(sol[k], marcas)) {
			if (isSolution(sol[k])) {
				printSolution(sol, k);
			} else {
				marcas[sol[k].x][sol[k].y] = true;
				laberintoVA(sol, k + 1, marcas);
			}
		}
	}
}
//
//int main() {
//	Casilla solucion[N * N];
//	bool marcas[N][N];
//
//	for (int i = 0; i < N; i++)
//		for (int j = 0; j < N; j++)
//			marcas[i][j] = false;
//
//	solucion[0].x = 0;
//	solucion[0].y = 0;
//
//	laberintoVA(solucion, 1, marcas);
//	return 0;
//}

