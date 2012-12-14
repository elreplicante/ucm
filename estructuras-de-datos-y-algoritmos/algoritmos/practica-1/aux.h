/*
 * aux.h
 *
 *  Created on: 14/12/2012
 *      Author: repli
 */

#include <time.h>
#include <cstdlib>
#include <iostream>
#include <time.h>
#include "ordenacion.h"
#include <fstream>
#include <iomanip>
using namespace std;

#ifndef AUX_H_
#define AUX_H_


void createRandomArray(int V[], int n);

void copyArray(int V[], int W[], int n);

bool checkArrayEquality(int V[], int W[], int n);

bool checkIsOrdered(int V[], int n);

void printArrayContents(int V[], int n);

void generateInsertionSortTime(ofstream &file, int V[], int n);

#endif /* AUX_H_ */
