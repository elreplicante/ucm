/*
 * Pareja.h
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#ifndef PAREJA_H_
#define PAREJA_H_

template <class T=int>
	class Pareja {
public:
	Pareja(T a, T b);
	T primero() const;
	T segundo() const;
	T getMax() const;
 	~Pareja();

private:
 	T _a;
 	T _b;
};

#endif /* PAREJA_H_ */
