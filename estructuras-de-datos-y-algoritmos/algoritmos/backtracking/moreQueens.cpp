/*
 * moreQueens.cpp
 *
 *  Created on: 11/06/2012
 *      Author: repli
 */


#include<iostream>
#include<curses.h>
#include<math.h>
#include <ncurses.h>


using namespace std;
class queen
{	int n,x[100];
public:
	queen();
	void nqueen(int,int);
	int place(int,int);
};
queen::queen() {
	int k = 1;

	cout << ("Enter the number of queens:");
	cin >> n;
	nqueen(k, n);
}
void queen::nqueen(int k, int n) {
	for (int i = 1; i <= n; i++) {
		if (place(k, i)) {
			x[k] = i;
		//	clear();
			if (k == n) {
				for (int j = 1; j <= n; j++) {
				//	color_set (x[j]);
			//		gotoxy(1, j);
					cout <<	("queen %d : %d",j,x[j]);
					}
				//	getch();

					break;
				}
				else
				nqueen(k+1,n);
			}
		}
		return;
	}
int queen::place(int k, int i) {
	int j;
	for (j = 1; j <= k; j++) {
		if ((x[j] == i) || (abs(x[j] - i) == abs(j - k)))
			return 0;
	}
	return 1;
}
//int main() {
//
//	queen q;
//
//	return 0;
//}

