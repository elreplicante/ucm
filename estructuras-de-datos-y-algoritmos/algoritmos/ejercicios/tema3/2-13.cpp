#include <iostream>

using namespace std;

int producto(int V[], int n) {

	int i = 0;
	int p = 1;


	while (i < n) {
		p = p * V[i];
		i++;
	}

	return p;

}

int main() {

	int V[] = {1, 2, 40};

	cout << producto(V, 3);
	
	return 0;
}