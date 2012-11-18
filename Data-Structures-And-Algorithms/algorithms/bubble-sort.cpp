/*
 * Algorithm: bubble-sort.cpp
 * 				1.-  Compare each pair of adjacent elements from the beginning
 * 				of an array and, if they are in reversed order, swap them.
 *	 			2.-	If at least one swap has been done, repeat step 1.
 *
 *
 * Description: Bubble sort is a simple and well-known
 * 				sorting algorithm. It is used in practice once
 * 				in a blue moon and its main application is to make
 * 				an introduction to the sorting algorithms.
 * 				Bubble sort belongs to O(n2) sorting algorithms,
 * 				which makes it quite inefficient for sorting large data volumes.
 * 				Bubble sort is stable and adaptive.
 *
 *	Complexity:	Average and worst case complexity of bubble sort is O(n2).
 *				Also, it makes O(n2) swaps in the worst case.
 *				Bubble sort is adaptive. It means that for almost sorted array
 *				it gives O(n) estimation. Avoid implementations, which don't check
 *				if the array is already sorted on every step (any swaps made).
 *				This check is necessary, in order to preserve adaptive property.
 *
 */

#include <iostream>
using namespace std;

#define ELEMENTS 6

void bubbleSort(int arr[], int length) {

	bool swapped = true;
	int j = 0;
	int key;
	while (swapped) {
		swapped = false;
		j++;
		for (int i = 0; i < length - j; i++) {
			if (arr[i] > arr[i + 1]) {
				key = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = key;
				swapped = true;
			}
		}
	}
}

/*

int main() {

	int A[ELEMENTS] = { 5, 2, 4, 6, 1, 3 };
	int x;

	cout << "NON SORTED LIST:" << endl;
	for (x = 0; x < ELEMENTS; x++) {
		cout << A[x] << endl;
	}
	bubbleSort(A, ELEMENTS);
	cout << endl << "SORTED LIST" << endl;
	for (x = 0; x < ELEMENTS; x++) {
		cout << A[x] << endl;
	}
	return 0;
}
*/
