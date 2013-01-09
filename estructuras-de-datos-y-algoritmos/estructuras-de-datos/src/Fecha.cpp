/*
 * Fecha.cpp
 *
 *  Created on: 09/01/2013
 *      Author: repli
 */

#include "../include/Fecha.h"
using namespace std;



Fecha::Fecha(int dia, int mes, int anyo) {
	_dia = dia;
	_mes = mes;
	_anyo = anyo;
}

Fecha::Fecha(const Fecha& base, int delta) {
	_dia = base.dia();
	_mes = base.mes();
	_anyo = base.anyo();
}

int Fecha::distancia(const Fecha& otra) const {

	int distancia = 0;

	return distancia;
}

int Fecha::dia() const {
	return _dia;
}

int Fecha::diaSemana() const {

}

int Fecha::mes() const {
	return _mes;
}

int Fecha::anyo() const {
	return _anyo;
}

int Fecha::diaAnyo() const {

}

Fecha::~Fecha() {

}

