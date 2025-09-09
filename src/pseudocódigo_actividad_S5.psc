Algoritmo TeatroMoroBasico
	
	// Variables
	Definir tipoEntrada, tipoTarifa, asiento Como Cadena
	Definir edad, precio Como Entero
	
	// Solicitar datos
	Escribir "Ingrese tipo de entrada (vip, platea baja, platea alta, palcos):"
	Leer tipoEntrada
	Escribir "Ingrese asiento:"
	Leer asiento
	Escribir "Ingrese tipo de tarifa (estudiante o general):"
	Leer tipoTarifa
	Escribir "Ingrese edad:"
	Leer edad
	
	// Calcular precio base según tipo de entrada
	Si tipoEntrada = "vip" Entonces
		precio <- 35000
	SiNo
		Si tipoEntrada = "platea baja" Entonces
			precio <- 25000
		SiNo
			Si tipoEntrada = "platea alta" Entonces
				precio <- 15000
			SiNo
				Si tipoEntrada = "palcos" Entonces
					precio <- 11000
				SiNo
					precio <- 0
				FinSi
			FinSi
		FinSi
	FinSi
	
	// Aplicar descuentos
	Si tipoTarifa = "estudiante" Entonces
		precio <- precio - Trunc(precio * 0.10)
	SiNo
		Si edad >= 65 Entonces
			precio <- precio - Trunc(precio * 0.15)
		FinSi
	FinSi
	
	// Mostrar resumen de la entrada
	Escribir "Entrada generada:"
	Escribir "Tipo de entrada: ", tipoEntrada
	Escribir "Asiento: ", asiento
	Escribir "Tarifa: ", tipoTarifa
	Escribir "Edad: ", edad
	Escribir "Precio final: $", precio
	
FinAlgoritmo
