#include <iostream>
#include <vector>
#include <assert.h>
using namespace std;

template<class E> class Monticulo {

	static const int MAX = 20;
private:
	E lista[MAX];
	int elementos;

public:
	Monticulo() {

		elementos = 0;
	}

	int izquierdo(int raiz) {
		assert(raiz < elementos / 2);
		return (raiz * 2);
	}

	int derecho(int raiz) {

		assert(raiz < elementos - 1/ 2);
		//tiene hijo izquierdo?
		return (raiz * 2) + 1;
	}

	int padre(int hijo) {
		assert(hijo != 0);
		//el nodo raiz no tiene padre
		return (hijo - 1) / 2;
	}

	bool lleno() {
		return (elementos == MAX);
	}
	void insertar(const E item) {

		assert(!lleno());
		lista[elementos] = item; //elementos representa la posicion del array despues del ultimo
		int indiceItem = elementos; //indice del item insertado
		elementos++;
		while ((indiceItem != 0)
				&& (lista[indiceItem] > lista[padre(indiceItem)])) {
			swap(lista[indiceItem], lista[padre(indiceItem)]);
			indiceItem = padre(indiceItem); //actualiza la posicion de los elementos
		}
	}

	void mostrar() {
		for (int i = 0; i < elementos; i++) {
			cout << lista[i] << " ";
		}
	}
/*
	void reHeap(int root) {
		int child = izquierdo(root);
		if ((lista[child] < lista[child + 1]) && (child < (elementos - 1))) //if a right child exists, and it's bigger than the left child, it will be used
			child++;
		if (lista[root] >= lista[child]) //if root is bigger than its largest child, stop.
			return;
		swap(lista[root], lista[child]); //swap root and its biggest child
		reHeap(child); //continue the process on root's new children
	}
	*/
};

/*
int main() {

	Monticulo<int> monticulo = Monticulo<int>();
	monticulo.insertar(10);
	monticulo.insertar(18);
	monticulo.insertar(6);
	monticulo.insertar(9);
	monticulo.insertar(8);
	monticulo.insertar(5);
	monticulo.insertar(10);
	monticulo.insertar(9);
	monticulo.insertar(9);
	monticulo.insertar(3);
	monticulo.insertar(4);


	monticulo.mostrar();

	return 0;

}
*/
