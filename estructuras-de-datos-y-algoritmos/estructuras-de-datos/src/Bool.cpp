#include "../include/Bool.h"
#include <iostream>
using namespace std;
Bool Bool::Falso(0);
Bool Bool::Cierto(1);
Bool Bool::bNot() const {
	return _b ? Falso : Cierto;
}
Bool Bool::bAnd(Bool &o) const {
	return _b ? o : Falso;
}
Bool Bool::bOr(Bool &o) const {
	return _b ? Cierto : o;
}
// op. externo sobrecargado (nuevos args para mismo nombre) // declarado amigo en la clase (tiene acceso a _b)
// ’cout << b;’ se traduce a ’operator<<(cout, b)’
std::ostream &operator<<(std::ostream &out, const Bool b) {
out << (b._b ? "1" : "0");
return out;
}
