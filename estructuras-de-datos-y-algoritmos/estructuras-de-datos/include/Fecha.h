/*
 * Fecha.h
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#ifndef FECHA_H_
#define FECHA_H_



class Fecha {
public:

	Fecha(int dia, int mes, int anyo);
	Fecha(const Fecha &base, int delta);
	int distancia(const Fecha &otra) const;
	int dia() const;
	int diaSemana() const;
	int mes() const;
	int anyo() const;
	int diaAnyo() const;
	virtual ~Fecha();

private:
	int _dia;
	int _mes;
	int _anyo;
};


#endif /* FECHA_H_ */
