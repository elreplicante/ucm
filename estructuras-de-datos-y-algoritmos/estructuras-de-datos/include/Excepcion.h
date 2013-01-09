/*
 * Excepcion.h
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#ifndef EXCEPCION_H_
#define EXCEPCION_H_

#include <string>
class Excepcion {
public:
	Excepcion();
	Excepcion(const std::string &msj);
	std::string getMsj() const;
	virtual ~Excepcion();

private:
	std::string _msj;
};

#endif /* EXCEPCION_H_ */
