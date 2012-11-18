/*
Algorithm: Insertion Sort
Description: Insertion sort belongs to the O(n2) sorting algorithms.
			Unlike many sorting algorithms with quadratic complexity,
			it is actually applied in practice for sorting small arrays of data.
			For instance, it is used to improve quicksort routine.
			Some sources notice, that people use same algorithm ordering items,
			for example, hand of cards.

Complexity: Insertion sort's overall complexity is O(n2) on average,
			regardless of the method of insertion.
			On the almost sorted arrays insertion sort shows better performance,
			up to O(n) in case of applying insertion sort to a sorted array.
			Number of writes is O(n2) on average, but number of comparisons
			may vary depending on the insertion algorithm. It is O(n2) when
			shifting or swapping methods are used and O(n log n) for binary
			insertion sort.

			From the point of view of practical application, an average complexity
			of the insertion sort is not so important. As it was mentioned above,
			insertion sort is applied to quite small data sets (from 8 to 12 elements).
			Therefore, first of all, a "practical performance" should be considered.
			In practice insertion sort outperforms most of the quadratic sorting
			algorithms, like selection sort or bubble sort.

 */

#include <iostream>
using namespace std;

#define ELEMENTS 6

void insertionSort(int array[], int length) {

	int i, j, index;
	for (i = 1; i < length; i++) {
		j = i;
		while (j > 0 && array[j - 1] > array[j]) {
			index = array[j];
			array[j] = array[j - 1];
			array[j - 1] = index;

			j--;
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
	insertionSort(A, ELEMENTS);
	cout << endl << "SORTED LIST" << endl;
	for (x = 0; x < ELEMENTS; x++) {
		cout << A[x] << endl;
	}
	return 0;
}

*/
