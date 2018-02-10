#!/usr/bin/python
# -*- coding: UTF-8 -*-
def validaDni(dni):
    tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
    dig_ext = "XYZ"
    reemp_dig_ext = {'X':'0', 'Y':'1', 'Z':'2'}
    numeros = "1234567890"
    dni = dni.upper()
    if len(dni) == 9:
        dig_control = dni[8]
        dni = dni[:8]
        if dni[0] in dig_ext:
            dni = dni.replace(dni[0], reemp_dig_ext[dni[0]])
        return len(dni) == len([n for n in dni if n in numeros]) \
            and tabla[int(dni)%23] == dig_control
    return False

print "Ejercicio final\n"
valido = False
esNombreValido = False
esApellidoValido = False
esDNIValido = False
esEdadValida = False
esPesoValido = False
esAlturaValida = False
persona = {'nombre':'','apellidos':'','edad':0,'dni':'','peso':0,'altura':0}


# Recogida de datos
while not valido:
    while not esNombreValido:
        entrada = raw_input('Nombre:')
        if len(entrada) > 0:
            persona['nombre'] = entrada
            esNombreValido = True
        else:
            print "El nombre es obligatorio"
    while not esApellidoValido:
        entrada = raw_input('Apellidos:')
        if len(entrada) > 0:
            persona['apellidos'] = entrada
            esApellidoValido = True
        else:
            print "El apellido es obligatorio"
    while not esEdadValida:
        entrada = raw_input('Edad:')
        numero = int(entrada)
        if numero > 0 and numero < 100:
            persona['edad'] = int(entrada)
            esEdadValida = True
        else:
            print "La edad debe estar entre 0 y 100"
    while not esDNIValido:
        entrada = raw_input('DNI:')
        esDNIValido = validaDni(entrada)
        if esDNIValido:
            persona['dni'] = entrada
        else:
            print "El DNI no es válido"
    while not esPesoValido:
        entrada = raw_input('Peso:')
        numero = int(entrada)
        if numero > 35 and numero < 125:
            persona['peso'] = int(entrada)
            esPesoValido = True
        else:
            print "El peso debe estar entre 35 y 125"
    while not esAlturaValida:
        entrada = raw_input('Altura:')
        numero = int(entrada)
        if numero > 50 and numero < 250:
            persona['altura'] = int(entrada)
            esAlturaValida = True
            valido = True
        else:
            print "La altura debe estar entre 50 y 250 cm"

print persona