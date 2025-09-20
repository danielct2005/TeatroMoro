Proceso GestionEntradasTeatro
	
    Definir opcion Como Entero
    
    // Listas de almacenamiento
    Definir listaClientes Como Vector Dinámico
    Definir listaObras Como Vector Dinámico
    Definir listaEntradas Como Vector Dinámico
    
    Repetir
        Escribir "========= SISTEMA DE GESTION DE ENTRADAS ========="
        Escribir "1. Registrar Cliente"
        Escribir "2. Registrar Obra de Teatro"
        Escribir "3. Vender Entrada"
        Escribir "4. Mostrar Entradas Vendidas"
        Escribir "5. Salir"
        Escribir "Seleccione una opción: "
        Leer opcion
		
        Segun opcion Hacer
			
            1:
                Llamar RegistrarCliente(listaClientes)
				
            2:
                Llamar RegistrarObra(listaObras)
				
            3:
                Llamar VenderEntrada(listaClientes, listaObras, listaEntradas)
				
            4:
                Llamar MostrarEntradas(listaEntradas)
				
            5:
                Escribir "Saliendo del sistema..."
				
            De Otro Modo:
                Escribir "Opción inválida."
				
        FinSegun
    Hasta Que opcion = 5
	
FinProceso


// ------------------------------------------------------
// Procedimiento para registrar un cliente
// ------------------------------------------------------
SubProceso RegistrarCliente(listaClientes Por Referencia)
    Definir nombre, rut Como Cadena
    Definir edad Como Entero
    Definir telefono Como Cadena
    
    Escribir "Ingrese nombre del cliente: "
    Leer nombre
    Escribir "Ingrese RUT: "
    Leer rut
    Escribir "Ingrese edad: "
    Leer edad
    Escribir "Ingrese teléfono: "
    Leer telefono
    
    Definir cliente Como Diccionario
    cliente["nombre"] <- nombre
    cliente["rut"] <- rut
    cliente["edad"] <- edad
    cliente["telefono"] <- telefono
    
    Agregar cliente a listaClientes
    Escribir "Cliente registrado correctamente."
FinSubProceso


// ------------------------------------------------------
// Procedimiento para registrar una obra
// ------------------------------------------------------
SubProceso RegistrarObra(listaObras Por Referencia)
    Definir titulo, director, fecha Como Cadena
    Definir duracion Como Entero
    
    Escribir "Ingrese título de la obra: "
    Leer titulo
    Escribir "Ingrese director: "
    Leer director
    Escribir "Ingrese fecha (dd/mm/aaaa): "
    Leer fecha
    Escribir "Ingrese duración en minutos: "
    Leer duracion
    
    Definir obra Como Diccionario
    obra["titulo"] <- titulo
    obra["director"] <- director
    obra["fecha"] <- fecha
    obra["duracion"] <- duracion
    
    Agregar obra a listaObras
    Escribir "Obra registrada correctamente."
FinSubProceso


// ------------------------------------------------------
// Procedimiento para vender una entrada
// ------------------------------------------------------
SubProceso VenderEntrada(listaClientes, listaObras, listaEntradas Por Referencia)
    Definir rutCliente, tituloObra Como Cadena
    Definir precioBase, descuento, precioFinal Como Real
    
    Escribir "Ingrese RUT del cliente: "
    Leer rutCliente
    
    Definir clienteEncontrado Como Logico <- Falso
    Para cada cliente en listaClientes Hacer
        Si cliente["rut"] = rutCliente Entonces
            clienteEncontrado <- Verdadero
            clienteSeleccionado <- cliente
        FinSi
    FinPara
    
    Si clienteEncontrado = Falso Entonces
        Escribir "Cliente no encontrado. Debe registrarlo primero."
        SalirSubProceso
    FinSi
    
    Escribir "Ingrese título de la obra: "
    Leer tituloObra
    
    Definir obraEncontrada Como Logico <- Falso
    Para cada obra en listaObras Hacer
        Si obra["titulo"] = tituloObra Entonces
            obraEncontrada <- Verdadero
            obraSeleccionada <- obra
        FinSi
    FinPara
    
    Si obraEncontrada = Falso Entonces
        Escribir "Obra no encontrada. Debe registrarla primero."
        SalirSubProceso
    FinSi
    
    // Precio y descuento
    Escribir "Ingrese precio base de la entrada: "
    Leer precioBase
    
    Si clienteSeleccionado["edad"] >= 60 Entonces
        descuento <- precioBase * 0.20  // 20% de descuento
    SiNo
        descuento <- 0
    FinSi
    
    precioFinal <- precioBase - descuento
    
    Definir entrada Como Diccionario
    entrada["cliente"] <- clienteSeleccionado["nombre"]
    entrada["obra"] <- obraSeleccionada["titulo"]
    entrada["precioFinal"] <- precioFinal
    
    Agregar entrada a listaEntradas
    Escribir "Entrada vendida correctamente. Precio final: $", precioFinal
FinSubProceso


// ------------------------------------------------------
// Procedimiento para mostrar todas las entradas vendidas
// ------------------------------------------------------
SubProceso MostrarEntradas(listaEntradas)
    Escribir "========= ENTRADAS VENDIDAS ========="
    Para cada entrada en listaEntradas Hacer
        Escribir "Cliente: ", entrada["cliente"]
        Escribir "Obra: ", entrada["obra"]
        Escribir "Precio: $", entrada["precioFinal"]
        Escribir "-----------------------------------"
    FinPara
FinSubProceso
