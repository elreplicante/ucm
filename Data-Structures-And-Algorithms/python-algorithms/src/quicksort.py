import random

def partir(lista,pivote):
	left = []
	right = []
	equals = [pivote]
	for a in lista:
		if a < pivote:
			left.append(a)
		elif a > pivote:
			right.append(a)
		else:
			equals.append(a)
	return left, equals, right

def quicksort(lista):
	if lista == []:
		return lista
	else:
		pivote = lista[0]
		left, equal, right = partir(lista[1:], pivote)
		return quicksort(left) + equal + quicksort(right)
	

numeros = range(1000)
print numeros
random.shuffle(numeros)
print numeros

numeros = quicksort(numeros)
print numeros