/*	Algorithm:	Quick Sort
 *				The divide-and-conquer strategy is used in quicksort.
 *				Below the recursion step is described:
 *
 *				1.- Choose a pivot value. We take the value of the middle element
 *					as pivot value, but it can be any value, which is in range of
 *					sorted values, even if it doesn't present in the array.
 *				2.- Partition. Rearrange elements in such a way, that all elements 					which are lesser than the pivot go to the left part of the array
 *					and all elements greater than the pivot, go to the right part of
 *					the array. Values equal to the pivot can stay in any part of the array.
 *					Notice, that array may be divided in non-equal parts.
 *				3.- Sort both parts. Apply quicksort algorithm recursively to the left
 *					and the right parts.
 *
 *	Description:Quicksort is a fast sorting algorithm, which is used not only
 *				for educational purposes, but widely applied in practice.
 *				On the average, it has O(n log (n)) complexity, making quicksort
 *				suitable for sorting big data volumes.
 *				The idea of the algorithm is quite simple and once you realize it,
 *				you can write quicksort as fast as bubble sort.
 *
 *	Details:	There are two indices i and j and at the very beginning of the
 *				partition algorithm i points to the first element in the array
 *				and j points to the last one. Then algorithm moves i forward,
 *				until an element with value greater or equal to the pivot is found.
 *				Index j is moved backward, until an element with value lesser or equal
 *				to the pivot is found. If i ² j then they are swapped and i steps to the
 *				next position (i + 1), j steps to the previous one (j - 1).
 *				Algorithm stops, when i becomes greater than j.
 *
 */

#include <iostream>
using namespace std;

#define ELEMENTS 6

void quickSort(int arr[], int left, int right) {

	int i = left, j = right;
	int tmp;
	int pivot = arr[(left + right) / 2];

	/* partition */
	while (i <= j) {
		while (arr[i] < pivot)
			i++;
		while (arr[j] > pivot)
			j--;
		if (i <= j) {
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;
			j--;
		}
	};

	/* recursion */
	if (left < j)
		quickSort(arr, left, j);
	if (i < right)
		quickSort(arr, i, right);
}
/*
 int main (){

 int A[ELEMENTS] = { 5, 2, 4, 6, 1, 3 };
 int x;

 cout << "NON SORTED LIST:" << endl;
 for (x = 0; x < ELEMENTS; x++) {
 cout << A[x] << endl;
 }
 quickSort(int A[ELEMENTS] = { 5, 2, 4, 6, 1, 3 };
 int x;

 cout << "NON SORTED LIST:" << endl;
 for (x = 0; x < ELEMENTS; x++) {
 cout << A[x] << endl;
 }
 bubbleSort(A, 0, 5);
 cout << endl << "SORTED LIST" << endl;
 for (x = 0; x < ELEMENTS; x++) {
 cout << A[x] << endl;
 }
 return 0;(A, ELEMENTS);
 cout << endl << "SORTED LIST" << endl;
 for (x = 0; x < ELEMENTS; x++) {
 cout << A[x] << endl;
 }
 return 0;
 }
 */
