INICIO

// --- Declarar variables ---
NOMBRE_TEATRO <- "Teatro Moro"
CAPACIDAD <- 10
asientos[CAPACIDAD] <- crear objetos Asiento con estado "libre"

tarifaGeneral <- Tarifa("General", 5000)
tarifaVIP <- Tarifa("VIP", 8000)
tarifaPalco <- Tarifa("Palco", 10000)

reservados <- 0
vendidos <- 0
totalEntradasVendidas <- 0
ingresosTotales <- 0
ultimasVentas <- lista vac�a

// --- Men� principal ---
REPETIR
    mostrar mapa de asientos (libre/reservado/vendido)
mostrar opciones de men�:
	1. Reservar asiento
	2. Modificar reserva
	3. Comprar reservas
	4. Imprimir boleta
	5. Salir
	
    LEER opcion
	
    SEGUN opcion HACER
		CASO 1:
			PEDIR numeroAsiento
			SI asiento est� "libre" ENTONCES
				marcar asiento como "reservado"
				incrementa reservados
			SINO
				mostrar "asiento no disponible"
			FIN SI
			
		CASO 2:
			PEDIR numeroAsientoActual
			SI asientoActual est� "reservado" ENTONCES
				PEDIR nuevoNumeroAsiento
				SI nuevoAsiento est� "libre" ENTONCES
					cambiar reserva a nuevo asiento
				SINO
					mostrar "nuevo asiento no disponible"
				FIN SI
			SINO
				mostrar "asiento no reservado"
			FIN SI
			
		CASO 3:
			SI no hay asientos reservados ENTONCES
				mostrar "No hay reservas"
			SINO
				PEDIR n�meros de asientos a comprar
				PARA cada asientoSeleccionado HACER
					// Selecci�n de tarifa con validaci�n estricta 1-3
					PEDIR opcionTarifa
					SELECCIONAR tarifa seg�n opcion
					
					// Tipo de cliente con validaci�n estricta 0,1,2
					PEDIR tipoCliente
				CALCULAR descuento:
					0 ? 0%
					1 ? 10%
					2 ? 15%
					
					precioFinal <- tarifa.precio - (tarifa.precio * descuento)
					
					marcar asiento como "vendido"
				actualizar contadores: vendidos++, reservados--
					crear objeto Venta con asiento, tarifa, descuento, precioFinal
					agregar a ultimasVentas
					totalEntradasVendidas++
					ingresosTotales += precioFinal
				FIN PARA
			FIN SI
			
		CASO 4:
			SI ultimasVentas est� vac�a ENTONCES
				mostrar "No se han realizado compras"
			SINO
			imprimir boleta:
				mostrar encabezado Teatro Moro
				mostrar cantidad de asientos comprados
				mostrar ubicaciones (tipo de tarifa)
				mostrar costos base
				mostrar descuentos aplicados
				mostrar costo final total
				mostrar mensaje "Gracias por su visita"
			FIN SI
			
		CASO 5:
			mostrar "Saliendo del sistema"
    FIN SEGUN
	
HASTA que opcion = 5

FIN
