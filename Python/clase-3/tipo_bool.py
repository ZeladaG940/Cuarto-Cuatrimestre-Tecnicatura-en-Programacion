
#Bool contiene los valores true y false
#Los tipos numericos, es false para el 0 (cero), true para los demas valores

valor = 0
resultado = bool(valor)
print(f'valor: {valor}, resultado: {resultado}')

valor = 0.1
resultado = bool(valor)
print(f'valor: {valor}, resultado: {resultado}')

#Tipo string -> false '', True demas valores
valor = ''
resultado = bool(valor)
print(f'valor: {valor}, resultado: {resultado}')

valor = 'Hola'
resultado = bool(valor)
print(f'valor: {valor}, resultado: {resultado}')

#Tipo de colecciones -> False para conlecciones vacias
#Tipo de colecciones -> True para topas las demas
#Lista
valor = []
resultado = bool(valor)
print(f'valor de una lista vacia: {valor}, resultado: {resultado}')

valor = [2,3,4]
resultado = bool(valor)
print(f'valor de una lista con elementos: {valor}, resultado: {resultado}')

#Tupla
valor = ()
resultado = bool(valor)
print(f'valor de una tupla vacia: {valor}, resultado: {resultado}')

valor = (5,)
resultado = bool(valor)
print(f'valor una tupla con elementos: {valor}, resultado: {resultado}')

#Diccionario
valor = {}
resultado = bool(valor)
print(f'Valor de un diccionario vacia: {valor}, resultado: {resultado}')

valor = {'Orland','Alizon','Matias'}
resultado = bool(valor)
print(f'Valor de un diccionario con elemntos: {valor}, resultado: {resultado}')

#Sentencias de control con bool
if (1,):
    print("Regresa verdadero")
else:
    print("Regresa falso")

#Ciclos
variable = 17
while variable:
    print('Regresa verdadero')
else:
    print('Regresa falso')