import random

def descartar(lista, S):
	for a in lista:
		if a == S:
			lista.remove(a)
	return lista
	
	
def encuentra(lista, S):
	mitad = lista.length() / 2
	left = lista[:mitad]
	right = lista[mitad + 1:]
	
	
	
numeros = range(1000)
random.shuffle(numeros)

numeros = descartar(numeros, 10)

print numeros
	
	