#include <iostream>
using namespace std;





bool gaspariforme(int V[], int n) {

	int i = 0;
	int s = 0;
	bool b;

	while (i < n) {
		s = s + V[i];

		if (s < 0) {
			b = false;
		}

		i++;
	}

	if (s == 0)
		b = true;
	else
		b =  false;

	return b;
} 

int main() {

	int V[] = {1, -1};

	cout << gaspariforme(V, 2);


	return 0;
}
