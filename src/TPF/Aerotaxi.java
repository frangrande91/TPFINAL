package TPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Aerotaxi {
    private List<Avion> flota;
    private Set<Usuario> clientes;
    private List<Vuelo> vuelos;

    public Aerotaxi() {
        this.flota = new ArrayList<Avion>();
        this.clientes = new HashSet<Usuario>();
        this.vuelos = new ArrayList<Vuelo>();
    }

    public Aerotaxi(List<Avion> flota, Set<Usuario> clientes, List<Vuelo> vuelos) {
        this.flota = flota;
        this.clientes = clientes;
        this.vuelos = vuelos;
    }

    public List<Avion> getFlota() {
        return flota;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public Set<Usuario> getClientes() {
        return clientes;
    }

    public void setFlota(List<Avion> flota) {
        this.flota = flota;
    }

    public void setClientes(Set<Usuario> clientes) {
        this.clientes = clientes;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public void addAvion(Avion avion) {
        this.flota.add(avion);
    }

    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    public void addUsuario(Usuario usuario) {
        this.clientes.add(usuario);
    }

    public Usuario buscarUsuario(int dni) {
        Usuario buscado = null;
        for (Usuario aux : this.clientes) {
            if (dni == aux.getDni())
                buscado = aux;
        }
        return buscado; //Retorna el usuario si existe o null si no existe
    }

    public boolean isPasajero(Set<Usuario> pasajerosDelVuelo, int dni) {
        boolean rta = false;
        for (Usuario aux : pasajerosDelVuelo) {
            if (dni == aux.getDni())
                rta = true;
        }
        return rta; //Retorna true si el usuario ya está registrado en el vuelo y false si no lo está
    }


    public int listarAvionesPorFecha(LocalDate fechaElegida, int cantAcomp) {
        int cant = 0;
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getFechaVuelo().equals(fechaElegida)) {
                System.out.println(vuelo.getNumeroDeVuelo() + " - Avion " + vuelo.getAvion().getClass().getSimpleName() + " - [" + vuelo.getTipoVuelo().getOrigen() + "-" + vuelo.getTipoVuelo().getDestino() + "] - AsientosDisponibles: " + (vuelo.getAvion().getCapacidadMaxPasajeros() - vuelo.getCantPasajeros()) + " - Costo: $" + vuelo.costoTotal(cantAcomp));
                cant++;
            }
        }
        return cant;
    }

    public int listarAvionesPorRecorrido(TipoVuelo tv, int cantAcomp) {
        int cant = 0;
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getTipoVuelo().getOrigen().equals(tv.getOrigen()) && vuelo.getTipoVuelo().getDestino().equals(tv.getDestino())) {
                System.out.println(vuelo.getNumeroDeVuelo() + " - Avion " + vuelo.getAvion().getClass().getSimpleName() + " - Fecha salida: " + vuelo.getFechaVuelo() + " - Asientos disponibles: " + (vuelo.getAvion().getCapacidadMaxPasajeros() - vuelo.getCantPasajeros()) + " - Costo total: $" + vuelo.costoTotal(cantAcomp));
                cant++;
            }
        }
        return cant;
    }

    public void listarVuelosPorDatos(LocalDate fecha, TipoVuelo tipo, Avion avion) {
        for (Vuelo vuelo : this.vuelos) {
            if ((vuelo.getFechaVuelo().equals(fecha)) && (vuelo.getTipoVuelo().equals(tipo)) && (vuelo.getAvion().equals(avion)))
                System.out.println(vuelo.toString());    //Muestro todos los vuelos de esa fecha, con ese origen y destino y ese avion
        }
    }

    public void listarVuelosUser(Usuario usuario) {
        System.out.println("************** Vuelos del usuario ***************");
        if (usuario.getVuelos().size() == 0) {
            System.out.println("El usuario no tiene vuelos contratados");
        } else {
            for (Vuelo v : usuario.getVuelos()) {
                System.out.println(v.getNumeroDeVuelo() + " - " + v.toString());
            }
        }
    }

    public void listarClientes() {
        for (Usuario user : clientes)
            System.out.println(user.toString());
    }

    public int getIndexVuelo(int numVuelo) {    //con el numero de vuelo obtengo el indice del arreglo
        int index = -1;
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getNumeroDeVuelo() == numVuelo)
                index = this.getVuelos().indexOf(vuelo);
        }
        return index;
    }

    public boolean existenVuelos(TipoVuelo tipo, int cantPasajeros){
        for(Vuelo v: this.vuelos){
            if(v.getTipoVuelo().equals(tipo) && (cantPasajeros <= v.getAvion().getCapacidadMaxPasajeros()-v.getCantPasajeros()))
                return true;
        }return false;
    }
    public boolean existenVuelos(LocalDate fecha, int cantPasajeros){
        for(Vuelo v: this.vuelos){
            if(v.getFechaVuelo().equals(fecha) && (cantPasajeros <= v.getAvion().getCapacidadMaxPasajeros()-v.getCantPasajeros()))
                return true;
        }return false;
    }

}
