/*
 * Excepcion.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#include "../include/Excepcion.h"


Excepcion::Excepcion() {
	// TODO Auto-generated constructor stub

}

Excepcion::Excepcion(const std::string& msj) : _msj(msj) {
}

std::string Excepcion::getMsj() const {
	return _msj;
}

Excepcion::~Excepcion() {
	// TODO Auto-generated destructor stub
}

