'''
Created on 31/05/2012

@author: repli
'''

class Nodo:
  def __init__(self, dato=None, siguiente=None):
    self.dato = dato
    self.siguiente  = siguiente

  def __str__(self):
    return str(self.dato)