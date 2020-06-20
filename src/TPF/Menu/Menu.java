package TPF.Menu;

import TPF.Modelo.*;
import TPF.Persistencia.*;

import java.time.LocalDate;
import java.util.*;


public class Menu {
    Scanner scan;
    Aerotaxi aerotaxi;
    Usuario usuario;

    public Menu(Aerotaxi aerotaxi) {
        this.scan = new Scanner(System.in);
        this.aerotaxi = aerotaxi;
        this.usuario = null;
    }

    public void menuPrincipal() {
        int op = 0;

        //PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes());
        //PersistenciaAviones.persistirAviones(aerotaxi.getFlota());
        //     Utilidades.pausar();

        //Leo los datos de los archivos
        boolean validar = false;
        aerotaxi.setClientes(PersistenciaUsuarios.leerUsuarios());
        HashSet<Gold> avionesGold = PersistenciaAvionesGold.leerAvionesGold();
        HashSet<Silver> avionesSilver = PersistenciaAvionesSilver.leerAvionesSilver();
        HashSet<Bronze> avionesBronze = PersistenciaAvionesBronze.leerAvionesBronze();
        HashSet<Avion> flota = new HashSet<Avion>();

        flota.addAll(avionesGold);
        flota.addAll(avionesSilver);
        flota.addAll(avionesBronze);
        aerotaxi.setFlota(flota);

        if (aerotaxi.getClientes() != null && aerotaxi.getFlota() != null) {
            validar = true;
            if (validar)
                aerotaxi.setVuelos(PersistenciaVuelos.leerVuelos());
                ingreso();

            do {
                Utilidades.clearScreen();
                imprimirTitulo();
                System.out.println("\n**************** Cliente: " + usuario.getNombre() + " " + usuario.getApellido() + " ****************");
                imprimirOpcMenuPrincipal();
                try {
                    op = scan.nextInt();
                    Utilidades.clearScreen();
                    switch (op) {
                        case 1:
                            Utilidades.clearScreen();
                            imprimirTitulo();
                            System.out.println("\n");
                            contratarVuelo();
                            Utilidades.pausar();
                            break;
                        case 2:
                            imprimirTitulo();
                            System.out.println("\n");
                            if (usuario.getVuelos().size() > 0) {
                                menuIdVuelo();
                                cancelarVueloPorId();
                            } else
                                System.out.println("El usuario no tiene vuelos contratados");
                            Utilidades.pausar();
                            break;
                        case 3:
                            imprimirTitulo();
                            System.out.println("\n");
                            if (usuario.getVuelos().size() > 0) {
                                menuIdVuelo();
                                agregarPasajeros();
                            } else
                                System.out.println("El usuario no tiene vuelos contratados");
                            Utilidades.pausar();
                            break;
                        case 4:
                            imprimirTitulo();
                            System.out.println("\n");
                            if (usuario.getVuelos().size() > 0) {
                                menuIdVuelo();
                                quitarPasajeros();
                            } else
                                System.out.println("El usuario no tiene vuelos registrados");
                            Utilidades.pausar();
                            break;
                        case 5:
                            menuVerVuelos();
                            break;
                        case 6:
                            imprimirTitulo();
                            aerotaxi.listarClientes();
                            System.out.println("\n");
                            Utilidades.pausar();
                            break;
                        case 7:
                            imprimirTitulo();
                            aerotaxi.listarAviones();
                            System.out.println("\n");
                            Utilidades.pausar();
                            break;
                        case 8:
                            ingreso(); //cambiar de cliente
                        case 0:
                            Utilidades.clearScreen();
                            imprimirTitulo();
                            System.out.println("\nSaliendo del programa..");
                            break;
                        default:
                            System.out.println("\nOpcion incorrecta. Intente nuevamente");
                            Utilidades.pausar();
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("\nIngrese un numero valido");
                    scan = new Scanner(System.in);  //limpiar buffer
                    Utilidades.pausar();
                }

            } while (op != 0);

        } else {
            System.out.println("No se pudieron leer los archivos. Cerrando el programa...");
        }
    }

    public void ingreso() {      // ingresar o registrarse para acceder al menu
        String dni = "";
        do {
            Utilidades.clearScreen();
            imprimirTitulo();
            System.out.println("\nDNI del cliente:");
            try {
                scan = new Scanner(System.in);
                dni = scan.nextLine();
                usuario = this.aerotaxi.buscarUsuario(dni);
                if (usuario == null) {
                    System.out.println("\nEL CLIENTE NO EXISTE");
                    Utilidades.pausar();
                }
            } catch (InputMismatchException e) {
                System.out.println("\nIngrese un numero valido");
                scan.nextLine(); //limpiar buffer
                Utilidades.pausar();
            }
        } while (usuario == null);
    }


    public void contratarVuelo() {
        LocalDate fechaBuscada = datosFechaDelVuelo(false);  //Elige fecha para viajar
        if (fechaBuscada != null) {
            TipoVuelo tipoElegido = seleccionarTipoVuelo();  //Elige origen y destino;
            System.out.println("\nVuelos " + tipoElegido.getOrigen() + " - " + tipoElegido.getDestino());
            System.out.println("Fecha: " + fechaBuscada);
            System.out.println("\nIngrese la cantidad de acompañantes:");
            int cantAcompañantes = scan.nextInt();           //Ingresa cantidad de acompañantes
            HashSet<Avion> avionesDisponiles = aerotaxi.buscarAvionesDisponibles(fechaBuscada, cantAcompañantes + 1);  //Busco los aviones disponibles para esa fecha y cantidad de pasajeros
            Utilidades.clearScreen();
            imprimirTitulo();

            Avion avionElegido = elegirAvion(avionesDisponiles);    //Elige el avion

            if (avionElegido != null) {
                Vuelo nuevoVuelo = new Vuelo(fechaBuscada, tipoElegido, avionElegido.getId(), usuario, cantAcompañantes + 1);  //Instancio el vuelo con los datos
                Utilidades.clearScreen();
                imprimirTitulo();
                System.out.println("\n******************************* VUELO A CONTRATAR *******************************");
                System.out.println(nuevoVuelo.mostrarVuelo(avionElegido));                       //Muestro el vuelo a contratar
                System.out.println("Para confirmar el vuelo presione 'c': ");
                scan.nextLine();
                String rta = scan.nextLine();
                if (rta.equals("c")) {
                    aerotaxi.addVuelo(nuevoVuelo);  //Agrego el nuevo vuelo a la lista de vuelos de la empresa
                    usuario.setTotalGastado(usuario.getTotalGastado() + nuevoVuelo.costoTotal(aerotaxi.buscarAvion(nuevoVuelo.getAvion()))); //Sumo el importe del vuelo al total gastado por el usuario
                    usuario.agregarVuelo(nuevoVuelo);    //Agrego el vuelo a la lista de vuelos(id) del usuario
                    usuario.mejorAvionContratado(aerotaxi);      //buscar mejor avion contratado
                    System.out.println("Vuelo contratado. Imprimiendo ticket...");
                    System.out.println("El número de vuelo del cliente es: <<<< " + nuevoVuelo.getId() + " >>>>. No lo pierda por favor");
                    PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                    PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes()); //Persisto los usuarios
                } else {
                    System.out.println("Vuelo cancelado");
                }
            }
        }
    }


    public Avion elegirAvion(HashSet<Avion> avionesDisponibles) {
        System.out.println("\n******************************* AVIONES DISPONIBLES ******************************");
        Avion avionElegido = null;
        if (avionesDisponibles.size() > 0) {    //Si la lista de aviones disponibles no esta vacía..
            for (Avion avion : avionesDisponibles)
                System.out.println(avion.toString());  //Muestro los aviones disponiles
            System.out.println("Ingrese el id del avión que desea contratar: ");
            int id = scan.nextInt();
            for (Avion aux : avionesDisponibles) {
                if (aux.getId() == id)
                    avionElegido = aux;
            }
        } else
            System.out.println("No hay aviones disponibles");

        return avionElegido;
    }


    public LocalDate datosFechaDelVuelo(boolean fechasPasadas) {
        LocalDate elegida = null;
        LocalDate fechaActual = LocalDate.now();
        System.out.println("\nFECHA ACTUAL: " + fechaActual);
        System.out.println("\nIngrese la fecha del vuelo: ");
        System.out.println("Año: ");
        int año = scan.nextInt();
        if (año > 2019 && año < 2100) {
            System.out.println("Mes: ");
            int mes = scan.nextInt();
            if (mes > 0 && mes < 13) {
                System.out.println("Día: ");
                int dia = scan.nextInt();
                if ((dia > 0) && (dia < 32)) {
                    LocalDate aux = LocalDate.of(año, mes, dia);
                    if (!fechasPasadas) {                               //solo fechas posteriores al dia actual
                        if (aux.isAfter(fechaActual))
                            elegida = aux;
                        else
                            System.out.println("Eligió una fecha vieja");
                    } else {                                            //cualquier fecha valida pasada o futura
                        elegida = aux;
                    }
                } else
                    System.out.println("Día inválido");
            } else
                System.out.println("Mes inválido");
        } else
            System.out.println("Año inválido");


        return elegida;  //Retorna la fecha elegida o null si se ingresó una fecha inválida
    }


    public TipoVuelo seleccionarTipoVuelo() {  //Metodo para elegir origen y destino
        TipoVuelo rta = null;
        int origenYdestino = 0;

        do {
            Utilidades.clearScreen();
            imprimirTitulo();
            System.out.println("\n***************************** ORIGEN - DESTINO *****************************");
            imprimirRutas();
            origenYdestino = scan.nextInt();
            if (origenYdestino < 1 || origenYdestino > 6) {
                System.out.println("Opcion incorrecta. Por favor elija una de las opciones");
                Utilidades.pausar();
            }
        } while (origenYdestino < 1 || origenYdestino > 6);

        String eleccion = elegirRuta(origenYdestino);
        for (TipoVuelo tipo : TipoVuelo.values()) {
            if (eleccion.equals(tipo.toString()))
                rta = tipo;           //Convierto los datos en un TipoVuelo (enum)
        }

        return rta; //Retorna el tipo de vuelo elegido(enum)
    }

    public String elegirRuta(int elegido) {
        String ruta = "";
        switch (elegido) {
            case 1:
                ruta = "Origen: Buenos Aires - Destino: Córdoba - Distancia: 695 km";
                break;
            case 2:
                ruta = "Origen: Buenos Aires - Destino: Santiago - Distancia: 1400 km";
                break;
            case 3:
                ruta = "Origen: Buenos Aires - Destino: Montevideo - Distancia: 950 km";
                break;
            case 4:
                ruta = "Origen: Córdoba - Destino: Montevideo - Distancia: 1190 km";
                break;
            case 5:
                ruta = "Origen: Córdoba - Destino: Santiago - Distancia: 1050 km";
                break;
            case 6:
                ruta = "Origen: Montevideo - Destino: Santiago - Distancia: 2100 km";
                break;
            default:
                ruta = null;
                break;
        }
        return ruta;
    }


    public void menuIdVuelo() {
        Utilidades.clearScreen();
        imprimirTitulo();
        try {
            boolean sn = true;
            String op = "";
            while (sn) {
                System.out.println("Tiene el numero del vuelo a editar? s/n");
                op = scan.next();
                if (op.equals("s") || op.equals("n")) {
                    sn = false;
                    break;
                } else {
                    scan = new Scanner(System.in); //limpiar buffer
                }
            }
            if (op.equals("n")) {     //Si no tiene el id le muestro todos los vuelos del usuario para que elija
                Utilidades.clearScreen();
                imprimirTitulo();
                System.out.println("\n************** Vuelos de " + usuario.getNombre() + " " + usuario.getApellido() + " ***************");
                aerotaxi.listarVuelosUser(usuario);      //Muestro la lista de vuelos del usuario
            }
        } catch (InputMismatchException e) {
            System.out.println("\nIngrese un numero valido");
            scan.nextLine(); //limpiar buffer
            Utilidades.pausar();
        }
    }

    public void cancelarVueloPorId() {
        System.out.println("Ingrese el número del vuelo a cancelar: ");
        long id = scan.nextLong();
        Vuelo buscado = aerotaxi.buscarVuelo(id);
        if (buscado != null) {
            Utilidades.clearScreen();
            imprimirTitulo();
            System.out.println("\n******************************* CANCELAR VUELO *******************************");
            System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));    //Muestro el vuelo a cancelar
            System.out.println("Para confirmar la cancelación del vuelo presione 'c'");
            scan.nextLine(); //por esto no andaba... limpiar buffer
            String op = scan.nextLine();
            if (op.equals("c")) {
                aerotaxi.borrarVuelo(buscado);  //Elimino el vuelo de la lista de vuelos de AEROTAXI
                usuario.darDeBajaVuelo(buscado); //Elimino el vuelo(id) de la lista de vuelos del usuario
                usuario.mejorAvionContratado(aerotaxi); //actualizar lista de mejor avion
                usuario.setTotalGastado(usuario.getTotalGastado() - buscado.costoTotal(aerotaxi.buscarAvion(buscado.getAvion())));  //Resto el costo del vuelo cancelado en el total gastado por el usuario
                PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes());
                System.out.println("Vuelo cancelado");
            } else {
                System.out.println("Vuelo no cancelado");
            }
        } else {
            System.out.println("No hay vuelos reservados con ese numero");
        }
    }


    public void agregarPasajeros() {
        System.out.println("Ingrese el número del vuelo al que desea agregar pasajeros: ");
        long id = scan.nextLong();
        Vuelo buscado = aerotaxi.buscarVuelo(id);
        if (buscado != null) {
            Utilidades.clearScreen();
            imprimirTitulo();
            int buscar = buscado.getAvion();
            int asientosDisponibles = aerotaxi.buscarAvion(buscar).getCapacidadMaxPasajeros() - buscado.getCantPasajeros();
            System.out.println("\n******************************* AGREGAR PASAJEROS *******************************");
            System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));    //Muestro el vuelo
            System.out.println("\nIngrese la cantidad de pasajeros a agregar: [Asientos disponibles: " + asientosDisponibles + "]");
            int cantAagregar = scan.nextInt();
            if (asientosDisponibles >= cantAagregar) {
                double costoAnterior = buscado.costoTotal(aerotaxi.buscarAvion(buscar));
                double costoNuevo = buscado.costoConPasajerosNuevos(cantAagregar, aerotaxi.buscarAvion(buscar));
                System.out.println("El costo se modificará de $ " + costoAnterior + " a $ " + costoNuevo + "\nPresione 'c' para confirmar");
                scan.nextLine(); // limpiar buffer
                String op = scan.nextLine();
                if (op.equals("c")) {
                    buscado.setCantPasajeros(buscado.getCantPasajeros() + cantAagregar);  //agregar pasajeros nuevos
                    usuario.setTotalGastado(usuario.getTotalGastado() - costoAnterior + costoNuevo);  //ajustar costo del vuelo
                    Utilidades.clearScreen();
                    imprimirTitulo();
                    System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));
                    PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                    PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes()); //Persisto los usuarios
                    System.out.println("\nPasajeros agregados");
                }
            } else {
                System.out.println("No hay suficientes asientos disponibles");
            }
        } else {
            System.out.println("No hay vuelos reservados con ese numero");
        }
    }

    public void quitarPasajeros() {
        System.out.println("Ingrese el número del vuelo al que desea quitar pasajeros: ");
        long id = scan.nextLong();
        Vuelo buscado = aerotaxi.buscarVuelo(id);
        if (buscado != null) {
            int totalPasajeros = buscado.getCantPasajeros();
            System.out.println("\n******************************* QUITAR PASAJEROS *******************************");
            System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));   //Muestro el vuelo
            System.out.println("\nIngrese la cantidad de pasajeros a quitar: [Total pasajeros: " + buscado.getCantPasajeros() + "]");
            int cantAquitar = scan.nextInt();
            if (totalPasajeros == cantAquitar) {
                System.out.println("Ha elegido quitar todos los pasajeros\nPresione 'c' para cancelar el vuelo");
                scan.nextLine(); //limpiar buffer
                String op = scan.nextLine();
                if (op.equals("c")) {
                    aerotaxi.borrarVuelo(buscado);  //Elimino el vuelo de la lista de vuelos de AEROTAXI
                    usuario.darDeBajaVuelo(buscado); //Elimino el vuelo(id) de la lista de vuelos del usuario
                    usuario.mejorAvionContratado(aerotaxi); //actualizar lista de mejor avion
                    usuario.setTotalGastado(usuario.getTotalGastado() - buscado.costoTotal(aerotaxi.buscarAvion(buscado.getAvion())));  //Resto el costo del vuelo cancelado en el total gastado por el usuario
                    System.out.println("Vuelo cancelado");
                    PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                    PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes()); //Persisto los usuarios
                }
            } else if (totalPasajeros > cantAquitar) {
                int buscar = buscado.getAvion();
                double costoAnterior = buscado.costoTotal(aerotaxi.buscarAvion(buscar));
                double costoNuevo = buscado.costoConPasajerosNuevos(cantAquitar * (-1), aerotaxi.buscarAvion(buscar));
                System.out.println("El costo se modificará de $ " + costoAnterior + " a $ " + costoNuevo + "\nPresione 'c' para confirmar");
                scan.nextLine(); // limpiar buffer
                String op = scan.nextLine();
                if (op.equals("c")) {
                    buscado.setCantPasajeros(buscado.getCantPasajeros() - cantAquitar);  //agregar pasajeros nuevos
                    usuario.setTotalGastado(usuario.getTotalGastado() - costoAnterior + costoNuevo);  //ajustar costo del vuelo
                    Utilidades.clearScreen();
                    imprimirTitulo();
                    System.out.println(buscado.toString());
                    System.out.println("\nPasajeros quitados");
                    PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                    PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes()); //Persisto los usuarios
                }
            } else {
                System.out.println("No puede quitar mas pasajeros de los que existen");
                //pausar();
            }
        } else {
            System.out.println("No hay vuelos reservados con ese numero");
            //pausar();
        }
    }


    public void menuVerVuelos() {
        imprimirOpcVerVuelos();
        LocalDate fecha;
        try {
            int e = scan.nextInt();
            Utilidades.clearScreen();
            switch (e) {
                case 2:                                             //vuelos de un dia en particular
                    fecha = datosFechaDelVuelo(true);
                    aerotaxi.listarVuelosPorFecha(fecha);
                    System.out.println("\n");
                    Utilidades.pausar();
                    break;
                case 1:                                             //todos los vuelos
                    imprimirTitulo();
                    aerotaxi.listarVuelos();
                    Utilidades.pausar();
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("\nIngrese un número válido");
            scan.nextLine(); //limpiar buffer
            Utilidades.pausar();
        } catch (NullPointerException e2) {
            System.err.println("\nEl archivo está vacío o no existe");
            scan.nextLine(); //limpiar buffer
            Utilidades.pausar();
        }
    }


    /************************************************ IMPRESIONES ************************************************/
    public void imprimirTitulo() {
        System.out.println("      _       ________  _______      ___    _________     _       ____  ____  _____  ");
        System.out.println("     / \\     |_   __  ||_   __ \\   .'   `. |  _   _  |   / \\     |_  _||_  _||_   _| ");
        System.out.println("    / _ \\      | |_ \\_|  | |__) | /  .-.  \\|_/ | | \\_|  / _ \\      \\ \\  / /    | |   ");
        System.out.println("   / ___ \\     |  _| _   |  __ /  | |   | |    | |     / ___ \\      > `' <     | |   ");
        System.out.println(" _/ /   \\ \\_  _| |__/ | _| |  \\ \\_\\  `-'  /   _| |_  _/ /   \\ \\_  _/ /'`\\ \\_  _| |_  ");
        System.out.println("|____| |____||________||____| |___|`.___.'   |_____||____| |____||____||____||_____| ");
    }

    public void imprimirOpcMenuPrincipal() {
        System.out.println("\n1. Contratar vuelo");
        System.out.println("2. Cancelar vuelo");
        System.out.println("3. Agregar pasajeros a un vuelo");
        System.out.println("4. Dar de baja pasajeros de un vuelo");
        System.out.println("5. Ver vuelos ");
        System.out.println("6. Ver clientes");
        System.out.println("7. Ver aviones");
        System.out.println("8. Gestionar otro cliente");
        System.out.println("0. Salir");
        System.out.println("\nElija una opción:");
    }

    public void imprimirOpcVerVuelos() {
        imprimirTitulo();
        System.out.println("\n1. Ver todos los vuelos reservados");
        System.out.println("2. Ver vuelos reservados por fecha");
        System.out.println("\nElija una opción:");
    }

    public void imprimirRutas() {
        System.out.println("1. Buenos Aires - Córdoba (695 km)");
        System.out.println("2. Buenos Aires - Santiago (1400 km)");
        System.out.println("3. Buenos Aires - Montevideo (950 km)");
        System.out.println("4. Córdoba - Montevideo (1190 km)");
        System.out.println("5. Córdoba - Santiago - (1050 km)");
        System.out.println("6. Montevideo - Santiago - (2100 km)");
        System.out.println("\nIngrese una opcion: ");
    }

    /************************************************ UTILIDADES ************************************************/
/*
    public void clearScreen() {
        for (int i = 0; i < 80 * 300; i++)
            System.out.println("\b");
    }

    public static void pausar() {
        System.out.println("Presione ENTER para continuar..");
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }

 */

}











/*
        public void menuContratarVuelo() {
        int opcion = scan.nextInt();
        LocalDate fechaBuscada;
        TipoVuelo tipoElegido;
        int numVuelo = 0;
        int cantAcompañantes = 0;
        ArrayList<Usuario> acompañantesDelUsuario = new ArrayList<Usuario>();
        clearScreen();
        do{
            switch (opcion) {
                case 1: //filtro x origen y destino
                    tipoElegido = seleccionarTipoVuelo();  //Elige origen y destino
                    if (tipoElegido != null) {
                        clearScreen();
                        imprimirTitulo();
                        System.out.println("\nVuelos " + tipoElegido.getOrigen() + " - " + tipoElegido.getDestino());
                        System.out.println("\nIngrese la cantidad de acompañantes:");
                        cantAcompañantes = scan.nextInt();
                        if (aerotaxi.existenVuelos(tipoElegido, cantAcompañantes + 1)) { //validar q existan vuelos con esas caract
                            if (cantAcompañantes != 0) {
                                clearScreen();
                                imprimirTitulo();
                                System.out.println("\nIngrese los datos de sus acompañantes: ");
                                acompañantesDelUsuario = registrarAcompañantes(cantAcompañantes);
                            }
                            clearScreen();
                            imprimirTitulo();
                            System.out.println("\n");
                            aerotaxi.listarAvionesPorRecorrido(tipoElegido, cantAcompañantes + 1);
                            System.out.println("\nSeleccione el numero de vuelo:");
                            numVuelo = scan.nextInt();
                        } else {
                            System.out.println("No hay vuelos para esa ruta");
                            pausar();
                        }
                    }
                    break;
                case 2: //filtro x fecha
                    fechaBuscada = datosFechaDelVuelo();
                    if (fechaBuscada != null) {
                        System.out.println("Fecha: " + fechaBuscada);
                        System.out.println("Cantidad de acompañantes:");
                        cantAcompañantes = scan.nextInt();
                        if (aerotaxi.existenVuelos(fechaBuscada, cantAcompañantes + 1)) { //validar q existan vuelos
                            if (cantAcompañantes != 0) {
                                System.out.println("Ingrese los datos de sus acompañantes: ");
                                acompañantesDelUsuario = registrarAcompañantes(cantAcompañantes);
                            }
                            clearScreen();
                            imprimirTitulo();
                            System.out.println("\n");
                            aerotaxi.listarAvionesPorFecha(fechaBuscada, cantAcompañantes + 1);
                            System.out.println("Seleccione el numero de vuelo:");
                            numVuelo = scan.nextInt();
                        } else {
                            System.out.println("No hay vuelos para esa ruta");
                            pausar();
                        }
                    }
                    break;
                default:
                    System.out.println("Opcion incorrecta. Intente nuevamente");
                    break;
            }
        }while(opcion != 0);

        //HABRÍA QUE MODULARIZAR ESTO EN METODOS
        //chequear asientos disponibles
        if (numVuelo != 0) {
            Vuelo aux = aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(numVuelo)); //Busco el vuelo elegido
            if ((aux.getCantPasajeros() + cantAcompañantes + 1) <= aux.getAvion().getCapacidadMaxPasajeros()) { //Si hay lugar para el usuario y sus acompañantes en el vuelo
                for (Usuario aAgregar : acompañantesDelUsuario) {
                    if (!aerotaxi.isPasajero(aux.getPasajeros(), aAgregar.getDni())) {    //valido que el acompañante ya no exista como pasajero del vuelo
                        aux.agregarPasajero(aAgregar); //agrega el acompañante al vuelo
                        aAgregar.agregarVuelo(aux);       //Agrega el vuelo a la lista de vuelos del acompañante
                        aAgregar.setTotalGastado(aAgregar.getTotalGastado() + aux.costoIndividual()); //Sumo el costo del vuelo al total gastado del acompañante
                        aAgregar.mejorAvionContratado(aux.getAvion());  //Compara el atributo mejorAvion del acompañante con el avion del nuevo vuelo contratado
                    } else
                        System.out.println("Su acompañante ya está registrado en ese vuelo");
                }
                if (!aerotaxi.isPasajero(aux.getPasajeros(), usuario.getDni())) {   //Valido que el usuario ya no exista como pasajeros del vuelo
                    aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(numVuelo)).agregarPasajero(usuario); //agrega el usuario al vuelo
                    usuario.agregarVuelo(aux);       //Agrega el vuelo a la lista de vuelos del usuario
                    usuario.setTotalGastado(usuario.getTotalGastado() + aux.costoIndividual()); //Sumo el costo del vuelo al total gastado del usuario
                    usuario.mejorAvionContratado(aux.getAvion()); //Compara el atributo mejorAvion del usuario con el avion del nuevo vuelo contratado
                } else
                System.out.println("Ud ya está registrado en ese vuelo");
            } else
                System.out.println("No hay esos asientos disponibles");
            pausar();
        }
    }

        aerotaxi.listarVuelosUser(usuario);     //Muestro los vuelos del usuario
        if (usuario.getVuelos().size() > 0) {     //Valido que haya vuelos para cancelar
            System.out.println("\n-- No se cancelaran los pasajes de sus acompañantes --");
            System.out.println("Ingrese el numero de vuelo a cancelar: ");
            int cancelar = scan.nextInt();
            if ((usuario.getIndexVuelo(cancelar) <= usuario.getVuelos().size()) && (aerotaxi.getIndexVuelo(cancelar) >= 0)) {  //Valido que el numero elegido coincida con un vuelo
                usuario.darDeBajaVuelo(cancelar);          //Se elimina el vuelo de la lista de vuelos del pasajero
                aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(cancelar)).quitarPasajero(dni); //Se quita el pasajero de la lista de pasajeros del vuelo
                usuario.setTotalGastado(usuario.getTotalGastado() - aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(cancelar)).costoIndividual()); //Resto el costo del vuelo al total gastado del usuario
            }
        } else
            pausar();
    }



    public Usuario registrarUsuario() {
        scan.nextLine(); //limpiar buffer
        System.out.println("Nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Apellido: ");
        String apellido = scan.nextLine();
        System.out.println("DNI: ");
        int dni = scan.nextInt();
        System.out.println("Edad: ");
        int edad = scan.nextInt();

        Usuario nuevo = new Usuario(nombre, apellido, dni, edad); //Instancion un nuevo usuario
        aerotaxi.addUsuario(nuevo);           //Lo agrego a la lista de clientes de AeroTaxi
        return nuevo;
    }

    public ArrayList<Usuario> registrarAcompañantes(int cantAcompañantes) { //Registra todos los acompañantes
        ArrayList<Usuario> acompañantes = new ArrayList<Usuario>();
        int aux = 1;
        while (cantAcompañantes != 0) {
            System.out.println("Acompañante " + aux);
            Usuario nuevo = registrarUsuario();
            acompañantes.add(nuevo);
            clearScreen();
            cantAcompañantes--;
            aux++;
        }
        return acompañantes;   //Registra como usuario a cada acompañante y retorna la lista de los acompañantes del usuario
    }

 */

