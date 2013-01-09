/*
1.
n = lon(u) >= 0 & m = lon(v) >= 0 & creciente(u, n) & creciente(v, m)
creciente(a, n) = ParaTodo i : 0 <= i < n - 1 : a[i] < a[i + 1]

fun comunes(int u[], int n, int v[], int m) return int r

r = #i,j : o <= i < n & 0 <= j < m : u[i] = v[j]

2. 

comunes(u, a, n, v, b, m) =
	1 + comunes(u, a + 1, n, v, b + 1, m)	si u[a] = v[b]
	comunes(u, a + 1, n, v, b, m)			si u[a] < v[b]
	comunes(u, a, n, v, b + 1, m)			si u[a] > v[b]

3. Caso base
a = n -> r = 0
b = m -> r = 0

Caso recursivo
(a < n) & (b < m)

4. Sucesor
s1(u, a, n, v, b, m) = (u, a + 1, n, v, b + 1, m)
s2(u, a, n, v, b, m) = (u, a + 1, n, v, b, m)
s3(u, a, n, v, b, m) = (u, a, n, v, b + 1, m)

Cota:
t1(x) = n - a 
t2(x) = n - a
t3(x) = m - b
*/



#include <iostream>
using namespace std;

int comunes(int u[], int a, int n, int v[], int m) {
	int r;

	if ((a == n) || (b == m)) {
		r = 0;
	} else {
		if (u[a] == v[b]) {
			r = 1 + comunes(u, a + 1, n, v, b + 1, m)
		} else if(u[a] < v[b]) {
			r = comunes(u, a + 1, n, v, b, m);
		} else {
			r = comunes(u, a, n, v, b + 1, m);
		}
	}

	return r;

}

int comunes(int u[], int n, int v[], int m) {
	return comunes(u, 0, n, v, 0, m);
}
