'''
Created on 31/05/2012

@author: repli
'''
from nodo import Nodo


class ColaMejorada:
	def __init__(self):
		self.longitud = 0
		self.cabeza = None
		self.ultimo = None

	def estaVacio(self):
		return (self.longitud == 0) 

	def insertar(self, dato):
		nodo = Nodo(dato)
		nodo.siguiente = None
		if self.cabeza == 0:
	# si esta lista esta vacia, el nuevo nodo esta a la cabeza y es el ultimo
			self.cabeza = self.ultimo = nodo
		else:
	# busca el ultimo nodo
			ultimo = self.ultimo
	# agregar el nuevo nodo
			ultimo.siguiente = nodo
			self.ultimo = nodo
			self.longitud = self.longitud + 1
		
	def eliminar(self):
		dato = self.cabeza.dato
		self.cabeza = self.cabeza.siguiente
		self.longitud = self.longitud - 1
		if self.longitud == 0:
			self.ultimo = None
		return dato


colamejorada = ColaMejorada
colamejorada().__init__()
colamejorada().insertar(10)

print colamejorada()