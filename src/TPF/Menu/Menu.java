package TPF.Menu;

import TPF.Modelo.*;
import TPF.Persistencia.*;

import java.time.DateTimeException;
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
                            if (!usuario.getVuelos().isEmpty() && !aerotaxi.getVuelos().isEmpty()) {
                                menuIdVuelo();
                                cancelarVueloPorId();
                            } else
                                System.out.println("El usuario no tiene vuelos contratados");
                            Utilidades.pausar();
                            break;
                        case 3:
                            imprimirTitulo();
                            System.out.println("\n");
                            if (!usuario.getVuelos().isEmpty() && !aerotaxi.getVuelos().isEmpty()) {
                                menuIdVuelo();
                                agregarPasajeros();
                            } else
                                System.out.println("El usuario no tiene vuelos contratados");
                            Utilidades.pausar();
                            break;
                        case 4:
                            imprimirTitulo();
                            System.out.println("\n");
                            if (!usuario.getVuelos().isEmpty() && !aerotaxi.getVuelos().isEmpty()) {
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
            int[] recorrido = seleccionarRecorrido();  //Elige origen y destino;
            if (Recorrido.getDistancia(recorrido[0], recorrido[1]) != null) {
                System.out.println("\nVuelos " + Recorrido.recorridoToString(recorrido));
                System.out.println("Fecha: " + fechaBuscada);
                System.out.println("\nIngrese la cantidad de acompañantes:");
                int cantAcompañantes = scan.nextInt();           //Ingresa cantidad de acompañantes
                if (cantAcompañantes >= 0) {
                    TreeSet<Avion> avionesDisponiles = aerotaxi.buscarAvionesDisponibles(fechaBuscada, cantAcompañantes + 1);  //Busco los aviones disponibles para esa fecha y cantidad de pasajeros
                    Avion avionElegido = elegirAvion(avionesDisponiles);    //Elige el avion

                    if (avionElegido != null) {
                        Vuelo nuevoVuelo = new Vuelo(fechaBuscada, recorrido, avionElegido.getId(), usuario, cantAcompañantes + 1);  //Instancio el vuelo con los datos
                        Utilidades.clearScreen();
                        imprimirTitulo();
                        System.out.println("\n******************************* VUELO A CONTRATAR *******************************");
                        System.out.println(nuevoVuelo.mostrarVuelo(avionElegido));                       //Muestro el vuelo a contratar
                        System.out.println("Para confirmar el vuelo presione 'c': ");
                        scan.nextLine();
                        String rta = scan.nextLine();
                        if (rta.equals("c")) {
                            aerotaxi.addVuelo(nuevoVuelo);  //Agrego el nuevo vuelo a la lista de vuelos de la empresa
                            usuario.setTotalGastado(usuario.getTotalGastado() + nuevoVuelo.costoTotal(aerotaxi.buscarAvion(nuevoVuelo.getAvion()), recorrido)); //Sumo el importe del vuelo al total gastado por el usuario
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
            } else {
                System.out.println("No hay vuelos disponibles con el recorrido seleccionado");
            }
        }
    }

    public Avion elegirAvion(TreeSet<Avion> avionesDisponibles) {
        Avion avionElegido = null;
        int id = 0;
        boolean flag = false;
        if (avionesDisponibles.size() > 0) {    //Si la lista de aviones disponibles no esta vacía..
            do {
                if (flag) {
                    System.out.println("Ingrese un numero valido");
                    Utilidades.pausar();
                }
                Utilidades.clearScreen();
                imprimirTitulo();
                System.out.println("\n******************************* AVIONES DISPONIBLES ******************************");
                for (Avion avion : avionesDisponibles)
                    System.out.println(avion.toString());  //Muestro los aviones disponiles
                System.out.println("Ingrese el id del avión que desea contratar: ");
                id = scan.nextInt();
                for (Avion aux : avionesDisponibles) {
                    if (aux.getId() == id)
                        avionElegido = aux;
                }
                flag = true;
            } while (id > avionesDisponibles.size() || id < 1);
        } else
            System.out.println("No hay aviones disponibles");

        return avionElegido;
    }


    public LocalDate datosFechaDelVuelo(boolean fechasPasadas) {
        LocalDate elegida = null;
        LocalDate fechaActual = LocalDate.now();
        System.out.println("\nFECHA ACTUAL: " + fechaActual);
        System.out.println("\nIngrese la fecha del vuelo: ");

        try {
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
        } catch (DateTimeException e) {
            System.err.println("Fecha inválida");
            scan.nextLine();  //limpiar buffer
        }

        return elegida;  //Retorna la fecha elegida o null si se ingresó una fecha inválida
    }

    public int[] seleccionarRecorrido() {  //Metodo para elegir origen y destino
        int[] rta = new int[2];
        int origen = 0;
        int destino = 0;

        do {
            Utilidades.clearScreen();
            imprimirTitulo();
            System.out.println("\n***************************** ORIGEN *****************************");
            imprimirRutas();
            origen = scan.nextInt();
            if (origen < 1 || origen > 4) {
                System.out.println("Opcion incorrecta. Por favor elija una de las opciones");
                Utilidades.pausar();
            } else
                rta[0] = origen;
        } while (origen < 1 || origen > 4);
        do {
            Utilidades.clearScreen();
            imprimirTitulo();
            System.out.println("\n***************************** DESTINO *****************************");
            imprimirRutas();
            destino = scan.nextInt();
            if (destino < 1 || destino > 4) {
                System.out.println("Opcion incorrecta. Por favor elija una de las opciones");
                Utilidades.pausar();
            } else {
                rta[1] = destino;
            }
        } while (destino < 1 || destino > 4);

        return rta;
    }

    public void menuIdVuelo() {    //preguntar si tiene el id del vuelo, si no se muestran todos sus vuelos
        Utilidades.clearScreen();
        imprimirTitulo();
        boolean sn = true;
        String op = "";
        try {
            while (sn) {
                System.out.println("Tiene el numero del vuelo a editar? s/n");
                op = scan.next();
                if (op.equals("s") || op.equals("n")) {
                    sn = false;
                    break;
                } else
                    scan = new Scanner(System.in); //limpiar buffer
            }
            if (op.equals("n")) {     //Si no tiene el id le muestro todos los vuelos del usuario para que elija
                Utilidades.clearScreen();
                imprimirTitulo();
                if (!usuario.getVuelos().isEmpty() && !aerotaxi.getVuelos().isEmpty()) {
                    System.out.println("\n************** Vuelos de " + usuario.getNombre() + " " + usuario.getApellido() + " ***************");
                    aerotaxi.listarVuelosUser(usuario);      //Muestro la lista de vuelos del usuario
                }else
                    System.out.println("\nEl usuario no tiene vuelos contratados");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nIngrese un numero valido");
            scan = new Scanner(System.in); //limpiar buffer
            Utilidades.pausar();
        }
    }


    public void cancelarVueloPorId() {
            System.out.println("Ingrese el número del vuelo a cancelar: ");
            long id = scan.nextLong();
            Vuelo buscado = aerotaxi.buscarVuelo(id);
            if (buscado != null && usuario.equals(buscado.getCliente())) {
                if (buscado.getFechaVuelo().isAfter(LocalDate.now().plusDays(1))) {//cancelar vuelo con mas de un dia de anticipacion
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
                        usuario.setTotalGastado(usuario.getTotalGastado() - buscado.costoTotal(aerotaxi.buscarAvion(buscado.getAvion()), buscado.getRecorrido()));  //Resto el costo del vuelo cancelado en el total gastado por el usuario
                        PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                        PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes());
                        System.out.println("Vuelo cancelado");
                    } else {
                        System.out.println("Vuelo no cancelado");
                    }
                } else
                    System.out.println("No se puede cancelar un vuelo con menos de 24 horas de anticipación");
            } else {
                System.out.println("No tiene vuelos con ese numero");
            }
        }


    public void agregarPasajeros() {
        System.out.println("Ingrese el número del vuelo al que desea agregar pasajeros: ");
        long id = scan.nextLong();
        Vuelo buscado = aerotaxi.buscarVuelo(id);
        if (buscado != null && usuario.equals(buscado.getCliente())) {
            Utilidades.clearScreen();
            imprimirTitulo();
            int buscar = buscado.getAvion();
            int asientosDisponibles = aerotaxi.buscarAvion(buscar).getCapacidadMaxPasajeros() - buscado.getCantPasajeros();
            System.out.println("\n******************************* AGREGAR PASAJEROS *******************************");
            System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));    //Muestro el vuelo
            System.out.println("\nIngrese la cantidad de pasajeros a agregar: [Asientos disponibles: " + asientosDisponibles + "]");
            int cantAagregar = scan.nextInt();
            if (asientosDisponibles >= cantAagregar) {
                if (cantAagregar > 0) {
                    double costoAnterior = buscado.costoTotal(aerotaxi.buscarAvion(buscar), buscado.getRecorrido());
                    double costoNuevo = buscado.costoConPasajerosNuevos(cantAagregar, aerotaxi.buscarAvion(buscar), buscado.getRecorrido());
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
                }
            } else
                System.out.println("No hay suficientes asientos disponibles");
        } else
            System.out.println("No tiene un vuelo con ese número");
    }

    public void quitarPasajeros() {
        System.out.println("Ingrese el número del vuelo al que desea quitar pasajeros: ");
        long id = scan.nextLong();
        Vuelo buscado = aerotaxi.buscarVuelo(id);
        if (buscado != null && usuario.equals(buscado.getCliente())) {
            int totalPasajeros = buscado.getCantPasajeros();
            System.out.println("\n******************************* QUITAR PASAJEROS *******************************");
            System.out.println(buscado.mostrarVuelo(aerotaxi.buscarAvion(buscado.getAvion())));   //Muestro el vuelo
            System.out.println("\nIngrese la cantidad de pasajeros a quitar: [Total pasajeros: " + buscado.getCantPasajeros() + "]");
            int cantAquitar = scan.nextInt();
            if (totalPasajeros == cantAquitar) {                //quitar todos los pasajeros=cancelar vuelo
                System.out.println("Ha elegido quitar todos los pasajeros\nPresione 'c' para cancelar el vuelo");
                scan.nextLine(); //limpiar buffer
                String op = scan.nextLine();
                if (op.equals("c")) {
                    aerotaxi.borrarVuelo(buscado);  //Elimino el vuelo de la lista de vuelos de AEROTAXI
                    usuario.darDeBajaVuelo(buscado); //Elimino el vuelo(id) de la lista de vuelos del usuario
                    usuario.mejorAvionContratado(aerotaxi); //actualizar lista de mejor avion
                    usuario.setTotalGastado(usuario.getTotalGastado() - buscado.costoTotal(aerotaxi.buscarAvion(buscado.getAvion()), buscado.getRecorrido()));  //Resto el costo del vuelo cancelado en el total gastado por el usuario
                    System.out.println("Vuelo cancelado");
                    PersistenciaVuelos.persistirVuelos(aerotaxi.getVuelos());  //Persisto los vuelos
                    PersistenciaUsuarios.persistirUsuarios(aerotaxi.getClientes()); //Persisto los usuarios
                }
            } else if (totalPasajeros > cantAquitar) {          //quitar algunos pasajeros
                int buscar = buscado.getAvion();
                double costoAnterior = buscado.costoTotal(aerotaxi.buscarAvion(buscar), buscado.getRecorrido());
                double costoNuevo = buscado.costoConPasajerosNuevos(cantAquitar * (-1), aerotaxi.buscarAvion(buscar), buscado.getRecorrido());
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
            } else                                    //no quitar, numero imposible
                System.out.println("No se puede quitar la cantidad de pasajeros elegida");
        } else
            System.out.println("No tiene vuelos con ese numero");
    }
    public void menuVerVuelos() {
        imprimirOpcVerVuelos();
        LocalDate fecha;
        try {
            int e = scan.nextInt();
            Utilidades.clearScreen();
            imprimirTitulo();
            switch (e) {
                case 1:                                             //todos los vuelos
                    if (!aerotaxi.getVuelos().isEmpty()) {
                        aerotaxi.listarVuelos();
                    } else
                        System.out.println("La empresa no tiene vuelos contratados");
                    Utilidades.pausar();
                    break;
                case 2:                                             //vuelos de un dia en particular
                    if (!aerotaxi.getVuelos().isEmpty()) {
                        fecha = datosFechaDelVuelo(true);
                        aerotaxi.listarVuelosPorFecha(fecha);
                        System.out.println("\n");
                    } else
                        System.out.println("La empresa no tiene vuelos contratados");
                    Utilidades.pausar();
                    break;
                case 3:                                            //vuelos desde..
                    if (!aerotaxi.getVuelos().isEmpty()) {
                        System.out.println("\nSeleccione el origen: \n");
                        imprimirRutas();
                        int i = scan.nextInt();
                        Utilidades.clearScreen();
                        aerotaxi.listarVuelosDesde(i);
                    } else
                        System.out.println("La empresa no tiene vuelos contratados");
                    Utilidades.pausar();
                    break;
                case 4:                                            //vuelos hacia..
                    if (!aerotaxi.getVuelos().isEmpty()) {
                        System.out.println("\nSeleccione el destino: \n");
                        imprimirRutas();
                        int j = scan.nextInt();
                        Utilidades.clearScreen();
                        aerotaxi.listarVuelosHacia(j);
                    } else
                        System.out.println("La empresa no tiene vuelos contratados");
                    Utilidades.pausar();
                    break;
                case 5:
                    System.out.println("\n******************************* Vuelos de " + usuario.getNombre() + " " + usuario.getApellido() + " ******************************");
                    if (!usuario.getVuelos().isEmpty() && !aerotaxi.getVuelos().isEmpty()) {
                        aerotaxi.listarVuelosUser(usuario);
                    } else
                        System.out.println("El usuario no tiene vuelos contratados");
                    Utilidades.pausar();
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("\nIngrese un número válido");
            scan = new Scanner(System.in); //limpiar buffer
            Utilidades.pausar();
        } finally {
            scan = new Scanner(System.in);
        }
    }

    /*************************************************************** IMPRESIONES ***************************************************************/
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
        System.out.println("3. Ver vuelos desde un origen");
        System.out.println("4. Ver vuelos hacia un destino");
        System.out.println("5. Ver vuelos del cliente");
        System.out.println("\nElija una opción:");
    }

    public void imprimirRutas() {
        System.out.println("1. Buenos Aires");
        System.out.println("2. Córdoba");
        System.out.println("3. Santiago");
        System.out.println("4. Montevideo");
        System.out.println("\nIngrese una opcion: ");
    }
}