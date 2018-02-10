#-*- coding: latin1 -*-
import os
# Definimos una función para imprimir un texto por pantalla
def saludo():
   print 'hola mundo'

def sl2():
    print '\n\n'
# Llamamos a la función
saludo()

# os.system('pause')
sl2()
print 'Listas y Tuplas\n'
# declaramos una tupla (son estáticas o inmutables, como una constante )
tupla = (u'¡','Hola ', 'mundo','!',3,2)

# declaramos una lista (son dinámicas, pueden modificarse en ejecución)
lista = [u'¡','Hola ','mundo','!']
print lista[0] + lista[1] + lista[2] + lista[3]
print tupla[0] + tupla[1] + tupla[2] + tupla[3]

sl2()
# añadimos un elemento a la lista con el método append del objeto lista
lista.append('otro elemento')
print 'Cuarto elemento de la lista:' + lista[3]

# diccionarios (las claves deben ser strings)
sl2()
print 'Diccionarios\n'
diccionario = {'nombre' : 'Carlos', 'edad' : 22, 'cursos' : ['Python','Django','JavaScript'] }
print 'Nombre..............:' + diccionario['nombre']
print 'Edad................:' + str(diccionario['edad']) 
print 'Curso[2]............:' +  diccionario['cursos'][2]
print 'Todo el diccionario.:',diccionario

# estructura for
sl2()
print 'Recorremos con un bucle for el diccionario\n'
for key in diccionario:
    print key,':',diccionario[key]

sl2()
# 1. crear una tupla que contenga como elemento otra tupla y datos de diferente tipo (caracter 
# y número). 
# 2. mostrar un elemento de la tupla principal
# 3. mostrar un elemento de la tupla interna

tupla2 = (('a',14),'segundo elemento')
print 'Toda la tupla.....................:',tupla2
print 'Un elemento de la tupla principal.:',tupla2[0]
print 'Un elemento de la tupla interna...:',tupla2[0][1]


# sentencias if
sl2()
print "Sentencias if"
entrada = raw_input('Dime tu edad:')
edad = int(entrada)
if edad > 18:
    print "Usted es mayor de edad."
elif edad <= 18 and edad > 0:
    print "Usted es menor de edad."
elif edad <= 0:
    print u"¡Usted no ha nacido todavía!"

# la estructura while

sl2()
print "Sentencia while\n"

i = 0
while i < 5:
    print "i=",i
    i = i + 1
print "Se termino el bucle."

# la sentencia for
sl2()
print "Sentencia for\n"

# imprimir tres veces la cadena 'hola' con un bucle for
tresveces = ["Hola","Hola","Hola"]
for x in tresveces:
    print x

# Ejercicio final sáb, 10/02/2018
# Almacenar los datos de una persona (nombre, apellidos, edad, dni, peso y altura). Validar su dni y si 
# es valido, calcular su IMC y mostrar la información de la persona
sl2()