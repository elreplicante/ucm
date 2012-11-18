/*
 * laberintoExamen.cpp
 *
 *  Created on: 06/09/2012
 *      Author: repli
 */


#include <iostream>
#include <climits>
#include <list>

using namespace std;

const int N = 5;
const int M = 8;

char palabraChar[] = {'E', 'D', 'A'};
char laberinto[N][M] = {	{'M', 'D', 'A', 'A', 'E', 'E', 'D', 'A'},
							{'A', 'E', 'E', 'D', 'D', 'A', 'N', 'D'},
							{'D', 'B', 'D', 'X', 'E', 'D', 'A', 'E'},
							{'E', 'A', 'E', 'D', 'A', 'R', 'T', 'D'},
							{'E', 'D', 'M', 'P', 'L', 'E', 'D', 'A'}};

list<char> palabra;

void palabras(){
	palabra.push_back('E');
	palabra.push_back('D');
	palabra.push_back('A');
}



struct Casilla {
	int x;
	int y;
};

Casilla nextPosition(Casilla pos, int dir) {
	switch (dir) {
	case 0:
		pos.y--;
		break;
	case 1:
		pos.x++;
		break;
	case 2:
		pos.y++;
		break;
	case 3:
		pos.x--;
		break;

	default:
		break;
	}
	return pos;
}
 void tratarSol(char sol[], int i, int k){

	 switch (i) {
		case 0:
			sol[k] = 'N';
			break;
		case 1:
			sol[k] = 'E';
			break;
		case 2:
			sol[k] = 'S';
			break;
		case 3:
			sol[k] = 'O';
			break;
		default:
			break;
	}
 }

void putoLaberinto(char laberinto[N][M], Casilla posicion, char solucion[],
		char solucionMejor[], int numeroMov, int numeroMovMejor,
		list<char>::iterator iterador, int k) {
	// Nos movemos en las posible posiciones (N, S E W)

	Casilla nuevaPos;

	for (int i = 0; i <= 3; i++) {
		nuevaPos = nextPosition(posicion, i);
		//cout << *iterador << ", ";
		cout << laberinto[nuevaPos.x][nuevaPos.y];
		if (nuevaPos.x >= 0 && nuevaPos.x < N && nuevaPos.y >= 0 &&
				nuevaPos.y < M && *iterador == laberinto[nuevaPos.x][nuevaPos.y]){

			if(*iterador == palabra.back()){
				iterador = palabra.begin();
			}
			else{
				iterador++;
			}

			tratarSol(solucion, i, k);

			numeroMov++;

			if(nuevaPos.x==N-1 && nuevaPos.y==0){

				if(numeroMov<numeroMovMejor){
					numeroMovMejor = numeroMov;
					for (int x = 0; x < numeroMovMejor; x++) {
						solucionMejor[x] = solucion[x];
					}
				}
			}else{
				putoLaberinto(laberinto, nuevaPos, solucion, solucionMejor, numeroMov, numeroMovMejor, iterador, k+1);
			}
			numeroMov--;
			if(*iterador == palabra.front()){
				iterador = palabra.end();
			}
			else{
				iterador--;
			}
		}
	}
}
int main() {

	palabras();
	Casilla posicion;
	posicion.x = 0;
	posicion.y = 4;

	char solucion[N*M];
	char solucionMejor[N*M];
	int numeroMov=0;
	int numeroMovMejor=N*M;
	int k=0;
	list<char>::iterator iterador;
	iterador = palabra.begin();
	iterador++;

	for (int i = 0; i < numeroMovMejor; i++) {
		solucionMejor[i] = 'H';
	}
		cout << "\n";
	for (int i = 0; i < numeroMov; i++) {
		solucionMejor[i] = 'H';
	}

	putoLaberinto(laberinto,posicion, solucion,
			solucionMejor, numeroMov, numeroMovMejor, iterador, k);

	for (int i = 0; i < numeroMovMejor; i++) {
		cout << solucionMejor[i] << ",";
	}
	cout << "\n";
	for (int i = 0; i < numeroMov; i++) {
		cout << solucion[i] << ",";
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << laberinto[i][j];
		}
			cout << endl;
		}
	return 0;

}

