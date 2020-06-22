package TPF.Modelo;

import TPF.Menu.Utilidades;
import java.time.LocalDate;
import java.util.*;

public class Aerotaxi {
    private HashSet<Avion> flota;
    private HashSet<Usuario> clientes;
    private ArrayList<Vuelo> vuelos;

    public Aerotaxi() {
        this.flota = new HashSet<Avion>();
        this.clientes = new HashSet<Usuario>();
        this.vuelos = new ArrayList<Vuelo>();
    }

    public HashSet<Avion> getFlota() {return flota; }
    public List<Vuelo> getVuelos() {return vuelos;}
    public HashSet<Usuario> getClientes() {return clientes;}
    public void setFlota(HashSet<Avion> flota) {this.flota = flota;}
    public void setClientes(HashSet<Usuario> clientes) {this.clientes = clientes;}
    public void setVuelos(ArrayList<Vuelo> vuelos) {this.vuelos = vuelos;}

    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    public void borrarVuelo(Vuelo vuelo) {
        this.vuelos.remove(vuelo);
    }

    public TreeSet<Avion> buscarAvionesDisponibles(LocalDate fecha, int cantPasajeros) {
        TreeSet<Avion> avionesDisponibles = new TreeSet<Avion>();
        if (vuelos != null) {
            HashSet<Avion> avionesOcupados = buscarAvionesPorFecha(fecha); //Busco los aviones que hay en esa fecha y los paso aun HashSet

            for (Avion avion : flota) {      //Recorro la flota de aviones de la empresa
                if (!avionesOcupados.contains(avion) && avion.capacidadMaxPasajeros >= cantPasajeros)   //Si el avion de la flota no se encuentra en la lista de aviones ocupados
                    avionesDisponibles.add(avion);                                                     // y no la cap. de pasajeros es suficiente, agrego el avion al TreeSet de aviones disponibles
            }
        } else {
            for (Avion avion : this.flota) {
                if (cantPasajeros <= avion.capacidadMaxPasajeros)    //Si no hay ningún vuelo agrego directamente los aviones con cap. de pasajeros suficiente al TreeSet de aviones disponibles
                    avionesDisponibles.add(avion);
            }
        }
        return avionesDisponibles;
    }

    public HashSet<Avion> buscarAvionesPorFecha(LocalDate fecha) {   //Busca los aviones por una fecha y retorna un HashSet con los mismos
        HashSet<Avion> avionesDeLaFecha = new HashSet<Avion>();
        for (Vuelo vuelo : this.vuelos) {
            if (fecha.isEqual(vuelo.getFechaVuelo()))
                avionesDeLaFecha.add(buscarAvion(vuelo.getAvion()));
        }
        return avionesDeLaFecha;
    }

    public Avion buscarAvion(int id) {
        Avion buscado = null;
        for (Avion avion : this.flota) {
            if (avion.getId() == id)
                buscado = avion;
        }
        return buscado;  //Retorna el avión o false si no está
    }

    public Vuelo buscarVuelo(long id) {
        Vuelo buscado = null;
        for (Vuelo vuelo : this.vuelos) {
            if (id == vuelo.getId())
                buscado = vuelo;
        }
        return buscado; //Retorna el vuelo buscado o null si no existe
    }

    public Usuario buscarUsuario(String dni) {
        Usuario buscado = null;
        for (Usuario aux : this.clientes) {
            if (dni.equals(aux.getDni()))
                buscado = aux;
        }
        return buscado; //Retorna el usuario si existe o null si no existe
    }

    public void listarClientes() {
        System.out.println("\n******************************* Clientes de AEROTAXI ******************************");
        for (Usuario user : clientes)
            System.out.println(user.toString());
    }

    public void listarAviones() {
        System.out.println("\n******************************* Aviones de AEROTAXI ******************************");
        TreeSet<Avion> flotaTreeSet = new TreeSet<Avion>();
        for (Avion avion : this.flota)
            flotaTreeSet.add(avion);          //Paso los aviones a un TreeSet para mostrarlos ordenados por id
        for (Avion avion : flotaTreeSet)
            System.out.println(avion.toString());
    }

    public void listarVuelos() {
        int i = 0;
        System.out.println("\n****************************** Vuelos de AEROTAXI ******************************\n");
        if (this.getVuelos().isEmpty()) {
            System.out.println("\nNo hay vuelos reservados\n");
        } else {
            double costo = 0;
            for (Vuelo v : this.getVuelos()) {
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                i++;
                if (i % 5 == 0)
                    Utilidades.pausar();
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
            System.out.println("\nCANTIDAD DE VUELOS: " + i + " - TOTAL: $ " + costo+"\n");

        }
    }

    public void listarVuelosPorFecha(LocalDate fecha) {
        boolean flag = false;
        double costo = 0;
        int i = 0;

        for (Vuelo v : this.vuelos) {
            if (fecha.isEqual(v.getFechaVuelo())) {
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                i++;
                flag = true;
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS " + fecha + ": " + i + " - TOTAL: $ " + costo+"\n");

        if (!flag)
            System.out.println("No hay vuelos para la fecha");
    }

    public void listarVuelosUser(Usuario usuario) {
        if (usuario.getVuelos().size() == 0)
            System.out.println("El usuario no tiene vuelos contratados");
        else {
            for (Integer aux : usuario.getVuelos())
                System.out.println(buscarVuelo(aux).mostrarVuelo(this.buscarAvion(buscarVuelo(aux).getAvion())));
        }
    }

    public void listarVuelosDesde(int i) {
        double costo = 0;
        int cant = 0;
        for (Vuelo v : this.getVuelos()) {
            int[] aux = v.getRecorrido();
            if (aux[0] == i) {
                cant++;
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS: " + cant + " - TOTAL: $ " + costo+"\n");
    }

    public void listarVuelosHacia(int i) {
        int cant = 0;
        double costo = 0;
        for (Vuelo v : this.getVuelos()) {
            int[] aux = v.getRecorrido();
            if (aux[1] == i) {
                cant++;
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS: " + cant + " - TOTAL: $ " + costo+"\n");

    }
}
