/*
 * Pareja.h
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#ifndef PAREJA_H_
#define PAREJA_H_

template<class A, class B>
class Pareja {

public:
	Pareja(const A &a, const B &b) : _a(a), _b(b) {}
	A primero() const {
		return _a;
	}
	B segundo() const {
		return _b;
	}

	~Pareja() {}

private:
	A _a;
	B _b;
};

#endif /* PAREJA_H_ */
