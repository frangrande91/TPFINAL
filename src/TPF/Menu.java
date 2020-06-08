package TPF;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    Scanner scan;
    Aerotaxi aerotaxi;

    public Menu(Aerotaxi aerotaxi){
        this.scan = new Scanner(System.in);
        this.aerotaxi = aerotaxi;
    }

    public void menuPrincipal(){
        int op = 0;
        String dni;
        Usuario usuario;

        do{
            System.out.println("********************* AEROTAXY *********************");
            System.out.println("Ingrese su DNI:");
            dni = scan.nextLine();
            usuario = this.aerotaxi.buscarUsuario(dni);
            if(usuario != null){
                imprimirOpcMenuPrincipal();
                System.out.println("Elija una opción:");
                op = scan.nextInt();

                switch (op){
                    case 1:
                        ingresoDatosVuelo();
                        break;
                    case 2:
                        //cancelarVuelo
                        break;
                    default:
                        System.out.println("Opcion incorrecta. Intente nuevamente");
                }
            }
            else
                System.out.println("El usuario no existe");
        }while(op != 0);
    }

    public void imprimirOpcMenuPrincipal(){
        System.out.println("************* MENU USUARIO *************");
        System.out.println("1. Contratar vuelo");
        System.out.println("2. Cancelar vuelo");
        System.out.println("0. Salir");
    }

    public void ingresoDatosVuelo(){
        LocalDate fechaBuscada = datosFechaDelVuelo();
        System.out.println("Fecha: " + fechaBuscada);
        TipoVuelo tipoElegido = seleccionarTipoVuelo();
        System.out.println("Cantidad de acompañantes:");
        int cantAcompañantes = scan.nextInt();
        System.out.println("Aviones disponibles para esa fecha: ");
        aerotaxi.listarAvionesPorFecha(fechaBuscada);
        System.out.println("\nIngrese el numero del avion que desea seleccionar");
        int indice = scan.nextInt();
        Avion avionElegido = aerotaxi.getFlota().get(indice - 1);
        System.out.println("Ha escogido el siguiente avion: ");
        System.out.println(avionElegido.toString());
        System.out.println("Los vuelos disponibles son: ");
        aerotaxi.listarVuelosPorDatos(fechaBuscada, tipoElegido, avionElegido);
        scan.next();
    }

    public LocalDate datosFechaDelVuelo(){
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
        System.out.println("Origen: ");
        scan.nextLine();
        String origen = scan.nextLine();
        System.out.println("Destino: ");
        String destino = scan.nextLine();
        System.out.println(origen);
        System.out.println(destino);
        for (TipoVuelo tipo : TipoVuelo.values()){
            if(origen.equals(tipo.getOrigen()) && (destino.equals(tipo.getDestino())))
                rta = tipo;
        }
        return rta; //Retorna el tipo de vuelo elegido o null si no existe un vuelo con ese origen y destino
    }

}
