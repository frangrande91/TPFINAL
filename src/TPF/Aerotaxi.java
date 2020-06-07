package TPF;

import java.util.ArrayList;
import java.util.List;

public class Aerotaxi {
    private List <Avion> flota;
    private List <Usuario> clientes;
    private List <Vuelo> vuelos;

    public Aerotaxi (){
        this.flota=new ArrayList<Avion>();
        this.clientes=new ArrayList<Usuario>();
        this.vuelos=new ArrayList<Vuelo>();
    }
    public Aerotaxi (List<Avion> flota, List<Usuario>clientes, List<Vuelo>vuelos){
        this.flota=flota;
        this.clientes=clientes;
        this.vuelos=vuelos;
    }

    public List<Avion> getFlota() {
        return flota;
    }

    public void setFlota(List<Avion> flota) {
        this.flota = flota;
    }

    public List<Usuario> getClientes() {
        return clientes;
    }

    public void setClientes(List<Usuario> clientes) {
        this.clientes = clientes;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }



}
