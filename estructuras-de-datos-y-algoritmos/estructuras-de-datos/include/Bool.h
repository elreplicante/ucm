/*
 * boolean.h
 *
 *  Created on: 07/02/2013
 *      Author: repli
 */

#include <ostream>
#ifndef _BOOLEANO_H_
#define _BOOLEANO_H_
class Bool {
public:
// Generadores
static Bool Falso, Cierto;

// Falso .bNot() = Cierto ; Cierto .bNot() = Falso
Bool bNot() const;

// Cierto .bAnd(x) = x; Falso .and(x) = Falso
Bool bAnd(Bool &otro) const;

// Cierto.bOr(x) = Cierto; Falso.or(x) = x
Bool bOr(Bool &otro) const;

private:
// tipo interno
char _b;

// constructor por defecto : privado
Bool(char b): _b(b) {}

// amigos: permiten a una funcion externa acceder a privados
friend std::ostream &operator<<(std::ostream &out, const Bool b);

};
#endif
