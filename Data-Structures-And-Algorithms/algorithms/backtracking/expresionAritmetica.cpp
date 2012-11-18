#include <iostream>
using namespace std;

const int n = 5;

const int solucion = 23;

char operaciones[3] = {'+', '-', '*'};

int numeros[n] = {23, 0, 0, 0, 0};

int operar(int i, int sig, int operacion) {

	int nuevaOp = 0;
	switch(i) {
		case 0:
		nuevaOp = operacion + sig;
		break;

		case 1:
		nuevaOp = operacion - sig;

		break;

		case 2:
		nuevaOp = operacion * sig;

		break;

		default:
		break;
	}
	return nuevaOp;

}

void desoperar(int i, int ant, int operacion) {

	switch(i) {
		case 0:
		operacion = operacion - ant;
		break;

		case 1:
		operacion = operacion + ant;

		break;

		case 2:
		operacion = operacion / ant;

		break;

		default:
		break;
	}


}

void back23(int solucionFinal[], int k, bool &correcto, int operacion) {

	int nuevaOp;

	for (int i = 0; i < 3 && !correcto; i++)	{
		
		nuevaOp = operar(i, numeros[k], operacion);

		solucionFinal[k-1] = i;

		if(k == 4){

			if (nuevaOp == solucion){
				correcto = true;
			}
		}else{
			back23(solucionFinal, k+1, correcto, nuevaOp);
		}
	}
}


int main() {

	bool correcto = false;
	int operacion = numeros[0];
	int solucionFinal[4];

	back23(solucionFinal, 1, correcto, operacion);

	cout << correcto;

	for (int i = 0; i < 4; ++i)
	{
		cout << solucionFinal[i] << ',';
	}

	return 0;
}

