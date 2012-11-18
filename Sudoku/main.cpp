/*
 * Este programa permite jugar una partida de Sudoku por consola dados un archivo de juego con los datos iniciales y un archivo con una de las posibles soluciones del Sudoku
 *
 *  Created on: 22/02/2012
 *      Author: Sergio Revilla Velasco
 */

#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

const int FILAS = 9; ///< Numero de filas que tiene un sudoku
const int COLUMNAS = 9; ///< Numero de columnas que tiene un sudoku
const int CASILLAS = 81; ///< Numero de casillas que tiene un sudoku
const int CANDIDATOS = 9; ///< Numero maximo de candidatos
const string PEDIR_COORDENADAS = "Introduce fila y columna [1..9]:"; ///< Mensaje que pide las coordenadas de una casilla
const string ERROR_COORDENADAS = "Debes introducir valores entre 1 y 9.\n"; ///< Mensaje de error si se introducen valores erroneos de fila/columna
const string ERROR_ARCHIVO =
		"No se ha encontrado el archivo o no ha sido posible abrirlo.\n"; ///< Mensaje de error por fallo de lectura de archivo
const string JUEGO_COMPLETADO =
		"Has conseguido completar el sudoku\nÁENHORABUENA!"; ///< Mensaje de juego completado
const string SALIR_MENU =
		"Vaya, te has rendido muy pronto...\nÁHasta la proxima!"; ///< Mensaje de salida del programa via menu
const string CASILLA_OCUPADA = "Casilla no vacia\n"; ///<Mensaje de casilla no vacia
const string CASILLA_VACIA = "Casilla vacia, imposible borrar.\n"; ///<Mensaje de casilla vacia
const string CASILLA_INICIAL = "Casilla inicial no modificable.\n"; ///<Mensaje de casilla inicial
const string JUEGO_INICIALIZADO = "ÁJuego inicializado con exito!"; ///<Mensaje de juego inicializado
const string CARGA_CORRECTA = "ÁArchivo de juego cargado con exito!"; ///<Mensaje de juego cargado
const string ARCHIVO_INCORRECTO = "El archivo de los datos es incorrecto\n"; ///<Mensaje de datos incorrectos
const string PIDE_VALOR = "Introduce un valor en el intervalo [1..9]:"; ///<Mensaje que pide valores
const string DATO_INTRODUCIDO = "Dato introducido con exito\n"; ///<Mensaje de dato introducido correctamente
const string DATO_INCORRECTO = "Valor incorrecto\n"; ///<Mensaje de valor incorrecto
const string DATO_FILA = "El dato ya existe en la fila.\n"; ///<Mensaje de dato existente en fila
const string DATO_COLUMNA = "El dato ya existe en la columna.\n"; ///<Mensaje de dato existente en columna
const string DATO_REGION = "El dato ya existe en la region.\n"; ///<Mensaje de dato existente en region


typedef int tMatrizEnteros[FILAS][COLUMNAS]; ///< Una matriz 9x9 de enteros
typedef bool tMatrizBooleanos[FILAS][COLUMNAS]; ///< Una matriz 9x9 de booleanos

/**
 * @struct tTablero
 * @brief Estructura del tablero.
 */
typedef struct {

	int contadorVacias; ///< Contador de casillas vacias
	tMatrizEnteros matrizJuego; ///<La matriz del juego
	tMatrizBooleanos matrizBool; ///<Matriz bool para saber cuales son las casillas que se cargaron inicialmente

} tTablero;

/**
 * @struct tJuego
 * @brief Estructura del juego
 */
typedef struct {

	tMatrizEnteros matrizSolucion; ///<La matriz con la solucion del sudoku
	tTablero tablero; ///< La estructura del juego

} tJuego;

/**
 * @struct tCandidatos
 * @brief Estructura que contiene los posibles candidatos para una casilla
 */

typedef struct {
	int posCandidato[CANDIDATOS]; ///<Array de candidatos
	int contCandidatos; ///< Contador de candidatos
} tCandidato;

/**
 * @brief Muestra el menu del juego y pide al usuario la opcion
 * @return la opcion elegida por el usuario
 */
char menu();

/**
 * @brief Inicializa las casillas de una matriz de booleanos a false.
 * @param matrizBool La matriz de booleanos del juego
 */
void inicializaMatrizBool(tMatrizBooleanos &matrizBool);

/**
 * @brief Inicializa las casillas de una matriz de enteros a 0.
 * @param matrizEnteros Una matriz de enteros
 */
void inicializaMatrizEnteros(tMatrizEnteros &matrizEnteros);

/**
 * @brief Inicializa todas las estructuras del juego
 * @param juego El juego
 */
void inicializaJuego(tJuego &juego);

/**
 * @brief Muestra el tablero de juego por consola
 * @param juego El juego
 */
void muestraTablero(tMatrizEnteros tablero);

/**
 * @brief Chequea si un entero esta entre 0 y 9
 * @param dato Un numero entero
 * @return true si el dato esta entre 0 y 9 (ambos incluidos)
 */
bool chequeaDato(int dato);

/**
 * @brief Chequea los datos de fila y columna
 * @param fila la fila a chequear
 * @param columna la columna a chequear
 * @return true si ambos datos estan entre 0 y 9 (ambos incluidos)
 */
bool chequeaDatos(int fila, int columna);

/**
 * @brief Carga el archivo del juego
 * @param juego el juego
 * @param nombreFichero el nombre del fichero a cargar
 * @return true si el archivo se ha abierto correctamente y todos los datos son correctos
 */
bool cargaSudoku(tJuego &juego, string nombreFichero);

/**
 * @brief Inserta un dato en el juego
 * @param juego El juego
 * @param fila la coordenada "fila"
 * @param columna la coordenada "columna"
 * @param dato el numero a insertar
 * @return true si el numero se ha introducido correctamente
 */

bool insertaDato(tJuego &juego, int fila, int columna, int dato);

/**
 * @brief Reinicia el juego a sus valores iniciales
 * @param juego El juego
 */
void reiniciarTablero(tJuego &juego);

/**
 * @brief Pide al usuario los nombres de los archivos de juego y solucion
 * @param nombreFicheroJuego El nombre del fichero que contiene el sudoku inicial
 * @param nombreFicheroSolucion El nombre del fichero que contiene la solucion
 */
void pedirFicheros(string &nombreFicheroJuego, string &nombreFicheroSolucion);

/**
 * @brief Carga el archivo con la solucion del sudoku.  El archivo tiene el formato adecuado y no se chequean errores
 * @param juego La estructura del juego
 * @param nombreFicheroSolucion El nombre del archivo con el fichero solucion
 * @return true si la carga se ha realizado correctamente
 */
bool cargaSolucion(tJuego &juego, string nombreFicheroSolucion);

/**
 * @brief Chequea si una casilla esta vacia (su valor es 0)
 * @param fila La fila
 * @param columna La columna
 * @param juego La estructura del juego
 * @return true si el valor de la casilla es distinto de cero
 */
bool casillaVacia(int fila, int columna, tJuego juego);

/**
 * @brief Pide una fila y una columna al usuario. Este metodo no chequea los datos, solo los pide y los devuelve por referencia
 * @param fila La fila
 * @param columna La columna
 */
void pideCoordenadas(int &fila, int &columna);

/**
 * @brief Chequea los posibles valores de una casilla
 * @param juego La estructura del juego
 */

void posiblesValores(const tJuego &juego);

/**
 * @brief Borra el valor de una casilla.  Chequea si la casilla esta ocupada o es una casilla inicial
 * @param juego La estructura del juego
 */
void borrarValor(tJuego &juego);

/**
 * @brief Chequea si un dato esta en una fila del tablero
 * @param dato El dato a comprobar
 * @param fila La fila a comprobar
 * @param tablero Una matriz de enteros
 * @return true si el dato esta en la fila
 */
bool esEnFila(const tMatrizEnteros &tablero, int fila, int dato);

/**
 * @brief Chequea si un dato esta en una columna del tablero
 * @param dato El dato a comprobar
 * @param fila La columna a comprobar
 * @param tablero Una matriz de enteros
 * @return true si el dato esta en la columna
 */
bool esEnColumna(const tMatrizEnteros &tablero, int columna, int dato);

/**
 * @brief Comprueba los valores del tablero que no coinciden con la solucion.  Si existe alguno, lo pinta por pantalla
 * @param juego La estructura del juego
 */

void comprobarErrores(const tJuego &juego);

/**
 * @brief Chequea si un dato ya esta en la region a la que pertenecen la fila y la columna
 * @param tablero Una matriz de enteros
 * @param fila La fila a comprobar
 * @param columna La columna a comprobar
 * @param dato El dato a comprobar
 */

bool esEnRegion(const tMatrizEnteros &tablero, int fila, int columna, int dato);

/**
 * @brief Chequea si una casilla tiene un solo canditato.  Si es asi lo rellena
 * @param juego La estructura del juego
 * @param candidatos Estructura que contiene los candidatos
 */
void rellenarCasillasSimples(tJuego &juego, int fila, int columna);


int main() {

	tJuego juego;
	string nombreFicheroJuego = "", nombreFicheroSolucion = "";
	bool cargaCorrecta = false;

	inicializaJuego(juego);
	cout << JUEGO_INICIALIZADO << endl << endl;

	/*Carga de los archivos de juego */

	do {

		pedirFicheros(nombreFicheroJuego, nombreFicheroSolucion);

		cargaCorrecta = cargaSudoku(juego, nombreFicheroJuego)
				&& cargaSolucion(juego, nombreFicheroSolucion);

		if (cargaCorrecta) {
			cout << CARGA_CORRECTA << endl << endl;
		} else {
			cout << ARCHIVO_INCORRECTO;

		}

	} while (!cargaCorrecta);

	/* MENU */

	bool salirMenu = false;
	bool juegoCompleto = false;

	do {
		muestraTablero(juego.tablero.matrizJuego);
		int opcion = menu();
		switch (opcion) {

		/* Salir del menu*/
		case '0':
			salirMenu = true;
			break;

			/* Ver posibles valores*/
		case '1': {

			posiblesValores(juego);
			break;
		}
		case '2': {

			int fila = 0, columna = 0, dato = 0;

			do {

				pideCoordenadas(fila, columna);
				cout << PIDE_VALOR;
				cin >> dato;
				if (!chequeaDato(dato)) {
					cout << DATO_INCORRECTO;
				} else {
					if (insertaDato(juego, fila, columna, dato)) {
						cout << DATO_INTRODUCIDO;
						juego.tablero.contadorVacias--;
						if (juego.tablero.contadorVacias == 0) {
							juegoCompleto = true;
						}
					}
				}
			} while (!chequeaDato(dato));

			if (juego.tablero.contadorVacias == 0)
							juegoCompleto = true;

			break;
		}

		case '3': {
			borrarValor(juego);
			break;
		}

		case '4': {
			comprobarErrores(juego);
			break;
		}

		case '5': {
			reiniciarTablero(juego);
			break;
		}

		case '6': {

			for (int fila = 0; fila < FILAS; fila++) {
				for (int columna = 0; columna < COLUMNAS; columna++) {
					rellenarCasillasSimples(juego, fila, columna);
				}

			}

			if (juego.tablero.contadorVacias == 0)
				juegoCompleto = true;

			break;
		}

		case 's': {

			muestraTablero(juego.matrizSolucion);
			break;
		}

		default:
			break;
		}
	} while (!salirMenu && !juegoCompleto);

	if (salirMenu) {

		cout << SALIR_MENU;
	}

	else if (juegoCompleto) {

		cout << JUEGO_COMPLETADO;
		muestraTablero(juego.tablero.matrizJuego);
	}

	return 0;
}

/*********************************************/

char menu() {

	char opcion;

	cout << "\n0.- Salir\n";
	cout << "1.- Ver posibles valores de una casilla\n";
	cout << "2.- Colocar un valor en una casilla\n";
	cout << "3.- Borrar el valor de una casilla\n";
	cout << "4.- Mostrar valores incorrectos\n";
	cout << "5.- Reiniciar tablero\n";
	cout << "6.- Rellenar casillas simples.\n";
	cout << "s.- Mostrar solucion\n";
	cout << "\nOPCION: ";

	cin >> opcion;
	return opcion;
}

/* 	INICIALIZACION DE LAS ESTRUCTURAS */

void inicializaMatrizBool(tMatrizBooleanos &matrizBool) {

	for (int i = 0; i < FILAS; i++) {
		for (int j = 0; j < COLUMNAS; ++j) {
			matrizBool[i][j] = false;
		}
	}
}

void inicializaMatrizEnteros(tMatrizEnteros &matrizEnteros) {

	for (int i = 0; i < FILAS; i++) {
		for (int j = 0; j < COLUMNAS; ++j) {
			matrizEnteros[i][j] = 0;
		}
	}
}

void inicializaJuego(tJuego &juego) {

	juego.tablero.contadorVacias = CASILLAS;
	inicializaMatrizEnteros(juego.tablero.matrizJuego);
	inicializaMatrizBool(juego.tablero.matrizBool);
	inicializaMatrizEnteros(juego.matrizSolucion);
}

/* PINTA EL TABLERO */

void muestraTablero(tMatrizEnteros tablero) {

	int i, j, k;
	cout << endl << endl << "     ";

	for (i = 0; i < COLUMNAS; i++) {
		if (i == 3 || i == 6) {
			cout << "   ";
		}
		cout << " " << i + 1 << " ";
	}
	cout << endl;

	for (i = 0; i < FILAS; i++) {
		if (i == 0 || i == 3 || i == 6) {
			cout << "  ";
			for (k = 0; k < 39; k++)
				cout << "-";
		}

		k = i;
		cout << endl << " " << k + 1 << " | ";

		for (j = 0; j < COLUMNAS; j++) {
			if (tablero[i][j] != 0) {
				cout << " " << tablero[i][j] << " ";
			} else {
				cout << " " << " " << " ";
			}

			if (j == 2 || j == 5 || j == 8) {
				cout << " | ";
			}
		}
		cout << endl;
	}
	cout << "  ";
	for (k = 0; k < 39; k++) {
		cout << "-";
	}

}
/* CHEQUEO DE DATOS*/

bool chequeaDato(int dato) {

	return (dato > 0 && dato <= 9);

}

bool chequeaDatos(int fila, int columna) {

	return (chequeaDato(fila) && chequeaDato(columna));
}

bool casillaVacia(int fila, int columna, const tMatrizEnteros &tablero) {

	return (tablero[fila][columna] == 0);
}

/* CARGA DE LOS ARCHIVOS */

bool cargaSudoku(tJuego &juego, string nombreFichero) {

	ifstream archivo;
	int fila, columna, numero;
	juego.tablero.contadorVacias = CASILLAS;
	bool cargaOk = true;

	archivo.open(nombreFichero.c_str());

	if (archivo.is_open()) {

		while ((archivo >> fila >> columna >> numero) && fila != -1 && cargaOk) {

			cargaOk = (chequeaDatos(fila, columna) && chequeaDato(numero));

			if (cargaOk) {
				juego.tablero.matrizJuego[fila - 1][columna - 1] = numero;
				juego.tablero.matrizBool[fila - 1][columna - 1] = true;
				juego.tablero.contadorVacias--;
			}

			else {
				cout << "El archivo tiene algun dato incorrecto" << endl;
			}
		}

		archivo.close();
	}

	else {
		cout
				<< "No se ha encontrado el archivo o no ha sido posible abrirlo.\n";
	}

	return cargaOk;
}

bool cargaSolucion(tJuego &juego, string nombreFicheroSolucion) {

	bool cargaOk = false;
	ifstream archivo;
	archivo.open(nombreFicheroSolucion.c_str());
	if (archivo.is_open()) {
		for (int i = 0; i < FILAS; ++i) {
			for (int j = 0; j < COLUMNAS; ++j) {
				archivo >> juego.matrizSolucion[i][j];
			}
		}
		cargaOk = true;
		archivo.close();
	}

	return cargaOk;

}

bool insertaDato(tJuego &juego, int fila, int columna, int dato) {

	bool datoInsertado = false;

	if (juego.tablero.matrizJuego[fila][columna] == 0
			&& !esEnFila(juego.tablero.matrizJuego, fila, dato)
			&& !esEnColumna(juego.tablero.matrizJuego, columna, dato)
			&& !esEnRegion(juego.tablero.matrizJuego, fila, columna, dato)) {
		juego.tablero.matrizJuego[fila][columna] = dato;
		juego.tablero.contadorVacias--;
		datoInsertado = true;
	}

	else {

		if (esEnFila(juego.tablero.matrizJuego, fila, dato))
			cout << DATO_FILA;
		if (esEnColumna(juego.tablero.matrizJuego, columna, dato))
			cout << DATO_COLUMNA;
		if (esEnRegion(juego.tablero.matrizJuego, fila, columna, dato))
			cout << DATO_REGION;
		if (juego.tablero.matrizJuego[fila][columna] != 0)
			cout << CASILLA_OCUPADA;
	}

	return datoInsertado;
}

bool esEnFila(const tMatrizEnteros &tablero, int fila, int dato) {

	bool encontrado = false;

	int j = 0;

	while (j <= 8 && !encontrado) {
		if (tablero[fila][j] == dato) {
			encontrado = true;
		} else
			j++;
	}

	return (encontrado);
}

bool esEnColumna(const tMatrizEnteros &tablero, int columna, int dato) {

	bool encontrado = false;

	int j = 0;

	while (j <= 8 && !encontrado) {
		if (tablero[j][columna] == dato) {
			encontrado = true;
		} else
			j++;
	}

	return (encontrado);
}

bool esEnRegion(const tMatrizEnteros &tablero, int fila, int columna,
		int dato) {

	bool encontrado = false;
	int i = (fila / 3) * 3;
	int j = (columna / 3) * 3;
	fila = i;
	columna = j;

	for (i = (fila / 3) * 3; i < fila + 3; i++) {

		for (j = (columna / 3) * 3; j < columna + 3 && !encontrado; j++) {
			if (tablero[i][j] == dato) {
				encontrado = true;
			}

		}

	}

	return (encontrado);

}

void reiniciarTablero(tJuego &juego) {

	for (int i = 0; i < FILAS; i++) {
		for (int j = 0; j < COLUMNAS; j++) {
			if (!juego.tablero.matrizBool[i][j]) {
				juego.tablero.matrizJuego[i][j] = 0;
				juego.tablero.contadorVacias++;
			}
		}
	}
}

void pedirFicheros(string &nombreFicheroJuego, string &nombreFicheroSolucion) {

	cout
			<< "Escribe el nombre del fichero de entrada (por defecto 'sudoku1.txt'): ";
	getline(cin, nombreFicheroJuego);

	if (nombreFicheroJuego == "")
		nombreFicheroJuego = "sudoku1.txt";
	cout
			<< "Escribe el nombre del fichero de salida (por defecto 'solsdk1.txt'): "; // Hacemos lo mismo para el fichero solución.

	getline(cin, nombreFicheroSolucion);
	if (nombreFicheroSolucion == "")
		nombreFicheroSolucion = "solsdk1.txt";
}

void pideCoordenadas(int &fila, int &columna) {

	do {
		cout << PEDIR_COORDENADAS;
		cin >> fila;
		cin >> columna;

		if (!chequeaDatos(fila, columna)) {
			cout << ERROR_COORDENADAS;
		}

	} while (!chequeaDatos(fila, columna));

	fila = fila - 1;
	columna = columna - 1;
}

void borrarValor(tJuego &juego) {
	int fila, columna;
	pideCoordenadas(fila, columna);

	if (casillaVacia(fila, columna, juego.tablero.matrizJuego)) {
		cout << CASILLA_VACIA;
	} else if (juego.tablero.matrizBool[fila][columna] == true) {
		cout << CASILLA_INICIAL;

	} else {
		juego.tablero.matrizJuego[fila][columna] = 0;
		juego.tablero.contadorVacias++;
	}
}

void comprobarErrores(const tJuego &juego) {
	int contadorErrores = 0;
//	bool hayErrores = false;

	for (int i = 0; i < FILAS; i++) {
		for (int j = 0; j < COLUMNAS; j++) {
			if (juego.tablero.matrizJuego[i][j] != 0
					&& juego.tablero.matrizJuego[i][j]
							!= juego.matrizSolucion[i][j]) {
				cout << "[" << i + 1 << "," << j + 1 << "] ("
						<< juego.tablero.matrizJuego[i][j] << ")" << endl;
				contadorErrores++;
			}
		}
	}

	if (contadorErrores == 0)
		cout << "No hay errores" << endl;
}

void posiblesValores(const tJuego &juego) {
	int fila, columna;

	pideCoordenadas(fila, columna);

	if (juego.tablero.matrizBool[fila][columna]) {
		cout << "Casilla no modificable.\n";
	} else if (juego.tablero.matrizJuego[fila][columna] != 0) {
		cout << "Casilla no vacia.\n";

	} else {
		cout << "Candidatos: ";
		for (int candidato = 1; candidato <= 9; candidato++) {

			if (!esEnFila(juego.tablero.matrizJuego, fila, candidato)
					&& !esEnColumna(juego.tablero.matrizJuego, columna,
							candidato)
					&& !esEnRegion(juego.tablero.matrizJuego, fila, columna,
							candidato)) {
				cout << candidato << " ";

			}
		}
		cout << endl;
	}
}

void rellenarCasillasSimples(tJuego &juego, int fila, int columna) {

	tCandidato candidatos;
	candidatos.contCandidatos = 0;
	int indiceCandidatos = 0;

	if (!juego.tablero.matrizBool[fila][columna]
			&& juego.tablero.matrizJuego[fila][columna] == 0) {

		for (int candidato = 1; candidato <= 9; candidato++) {

			if (!esEnFila(juego.tablero.matrizJuego, fila, candidato)
					&& !esEnColumna(juego.tablero.matrizJuego, columna,
							candidato)
					&& !esEnRegion(juego.tablero.matrizJuego, fila, columna,
							candidato)) {
				candidatos.posCandidato[indiceCandidatos] = candidato;
				candidatos.contCandidatos++;
				indiceCandidatos++;
			}

		}
		if (candidatos.contCandidatos == 1) {
			juego.tablero.matrizJuego[fila][columna] =
					candidatos.posCandidato[0];
			juego.tablero.contadorVacias--;
		}
	}
}

