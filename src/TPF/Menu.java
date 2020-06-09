package TPF;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    Scanner scan;
    Aerotaxi aerotaxi;
    Usuario usuario;

    public Menu(Aerotaxi aerotaxi) {
        this.scan = new Scanner(System.in);
        this.aerotaxi = aerotaxi;
        this.usuario=null;
    }

    public void menuPrincipal() {
        int op = 0;
        String dni;

        do {
            System.out.println("********************* AEROTAXI *********************");
            while (usuario == null) {
                System.out.println("Ingrese su DNI:");
                dni = scan.nextLine();
                usuario = this.aerotaxi.buscarUsuario(dni);
                if (usuario == null)
                    System.out.println("Usuario no registrado. Intente nuevamente");
            }
            //    if(usuario != null){
            imprimirOpcMenuPrincipal();
            System.out.println("Elija una opción:");
            op = scan.nextInt();

            switch (op) {
                case 1:
                    submenuContratarVuelo();
                    break;
                case 2:
                    //cancelarVuelo
                    break;
                case 3: verListaDeVuelosUser(usuario);
                break;
                case 0:
                    System.out.println("Gracias por elegir AEROTAXI");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Intente nuevamente");
            }
            //    }
            //    else
            //        System.out.println("El usuario no existe");
        } while (op != 0);
    }


    public int submenuContratarVuelo() {
        imprimirOpcMenuContratar();
        int opcion = scan.nextInt();
        switch (opcion) {
            case 1:
            case 2:
                ingresoDatosVuelo(opcion);
                break;
            case 0:
                break;
            default:
                break;
        }
        return opcion;
    }

    public int ingresoDatosVuelo(int opcion) {
        LocalDate fechaBuscada;
        TipoVuelo tipoElegido;
        int numVuelo = 0;
        switch (opcion) {
            case 1: //filtro x origen
                tipoElegido = seleccionarTipoVuelo();
                System.out.println("Vuelos " + tipoElegido.getOrigen() + " - " + tipoElegido.getDestino());
                System.out.println("Seleccione el numero de vuelo:");
                aerotaxi.listarAvionesPorRecorrido(tipoElegido);
                numVuelo = scan.nextInt();
                break;
            case 2: //filtro x fecha
                fechaBuscada = datosFechaDelVuelo();
                System.out.println("Fecha: " + fechaBuscada);
                System.out.println("Seleccione el numero de vuelo:");
                aerotaxi.listarAvionesPorFecha(fechaBuscada);
                numVuelo = scan.nextInt();

                /*tipoElegido = seleccionarTipoVuelo();
                System.out.println("Aviones disponibles para esa fecha: ");
                System.out.println("\nIngrese el numero del avion que desea seleccionar");
                int indice = scan.nextInt();
                Avion avionElegido = aerotaxi.getFlota().get(indice - 1);
                System.out.println("Ha escogido el siguiente avion: ");
                System.out.println(avionElegido.toString());
                System.out.println("Los vuelos disponibles son: ");
                aerotaxi.listarVuelosPorDatos(fechaBuscada, tipoElegido, avionElegido);
                scan.next();*/
                break;
            default:
                break;
        }
        System.out.println("Cantidad de acompañantes:");
        int cantAcompañantes = scan.nextInt();

        //chequear asientos disponibles
        Vuelo aux=aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(numVuelo));
        if((aux.getCantPasajeros()+cantAcompañantes+1) <= aux.getAvion().getCapacidadMaxPasajeros()) {
            aerotaxi.getVuelos().get(aerotaxi.getIndexVuelo(numVuelo)).agregarPasajeros(cantAcompañantes + 1); //agregar pasajeros
            usuario.agregarVuelo(aux);
        }else{
            System.out.println("No hay asientos disponibles");
        }

        //metodo buscar en todos los vuelos 'x' pasajero, o lista vuelos a pasajero
        return numVuelo;
    }

    public LocalDate datosFechaDelVuelo() {
        System.out.println("Ingrese los datos de la fecha del vuelo: ");
        System.out.println("Año: ");
        int año = scan.nextInt();
        System.out.println("Mes: ");
        int mes = scan.nextInt();
        System.out.println("Día: ");
        int dia = scan.nextInt();
        return LocalDate.of(año, mes, dia);
    }

    public TipoVuelo seleccionarTipoVuelo() {
        TipoVuelo rta = null;
        do {
            imprimirCiudades(true);
            int elegido = scan.nextInt();
            String origen = elegirCiudad(elegido);
            imprimirCiudades(false);
            elegido = scan.nextInt();
            String destino = elegirCiudad(elegido);
            for (TipoVuelo tipo : TipoVuelo.values()) {
                if (origen.equals(tipo.getOrigen()) && (destino.equals(tipo.getDestino())))
                    rta = tipo;
            }
            if (rta == null) {
                System.out.println("No hay vuelos disponibles para el recorrido seleccionado");
                System.out.println("Intente nuevamente");
            }
        } while (rta == null);

        return rta; //Retorna el tipo de vuelo elegido o null si no existe un vuelo con ese origen y destino
    }

    public void imprimirOpcMenuPrincipal() {
        System.out.println("************* MENU USUARIO *************");
        System.out.println("1. Contratar vuelo");
        System.out.println("2. Cancelar vuelo");
        System.out.println("3. Ver mi lista de vuelos");
        System.out.println("0. Salir");
    }

    public void imprimirOpcMenuContratar() {
        System.out.println("************* MENU USUARIO *************");
        System.out.println("1. Contratar vuelo por origen/destino");
        System.out.println("2. Contratar vuelo por fecha");
        System.out.println("0. Salir");
    }

    public void imprimirCiudades(boolean origen) {
        if (origen) {
            System.out.println("************* ORIGENES DISPONIBLES *************");
        } else {
            System.out.println("************* DESTINOS DISPONIBLES *************");
        }
        System.out.println(" Ingrese una opcion: \n");
        System.out.println("1. Buenos Aires");
        System.out.println("2. Córdoba");
        System.out.println("3. Santiago");
        System.out.println("4. Montevideo");
    }

    public String elegirCiudad(int elegido) {
        String ciudad = "";
        switch (elegido) {
            case 1:
                ciudad = "Buenos Aires";
                break;
            case 2:
                ciudad = "Córdoba";
                break;
            case 3:
                ciudad = "Santiago";
                break;
            case 4:
                ciudad = "Montevideo";
                break;
            default:
                break;
        }
        return ciudad;
    }

    public void verListaDeVuelosUser(Usuario usuario){
        for(Vuelo v:usuario.getVuelos()){
            System.out.println(v.toString());
        }
    }

}
