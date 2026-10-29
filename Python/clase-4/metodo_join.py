
# help(str.join)
tupla_str = ('Hola', 'alumnos','Tecnicatura','Universitaria')
mensaje = ''.join(tupla_str)
print(f'Mensaje: {mensaje}')

lista_cursos = ('Java', 'JavaScrip','Python','Spring')
mensaje = ''.join(lista_cursos)
print(f'Mensaje: {mensaje}')

cadena = 'Hola mundo'
mensaje = '.'.join(cadena)
print(f'Mensaje: {mensaje}')

diccionario = {'nombre':'Juan', 'Apellido': 'Perez', 'edad': '18'}
llaves = '-'.join(diccionario.keys())
valores = '-'.join(diccionario.values())
print(f'Llaves: {llaves}, Type: {type(llaves)}')
print(f'Valores: {valores}, Type: {type(valores)}')