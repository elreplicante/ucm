/*
 * EDivisionPorCero.h
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#ifndef EDIVISIONPORCERO_H_
#define EDIVISIONPORCERO_H_

#include "Excepcion.h"


class EDivisionPorCero: public Excepcion {
public:
	EDivisionPorCero();
	virtual ~EDivisionPorCero();
};


#endif /* EDIVISIONPORCERO_H_ */
