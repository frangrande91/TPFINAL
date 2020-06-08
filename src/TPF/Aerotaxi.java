package TPF;

import java.time.LocalDate;
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

    public void addAvion(Avion avion){
        this.flota.add(avion);
    }

    public void addVuelo(Vuelo vuelo){
        this.vuelos.add(vuelo);
    }

    public void addUsuario(Usuario usuario){
        this.clientes.add(usuario);
    }

    public Usuario buscarUsuario(String dni){
        Usuario buscado = null;
        for(Usuario aux : this.clientes){
            if(dni.equals(aux.getDni()))
                buscado = aux;
        }
        return buscado; //Retorna el usuario si existe o null si no existe
    }

    public void listarAvionesPorFecha(LocalDate fechaElegida){
        int count = 1;
        for(Vuelo vuelo : this.vuelos){
            if(vuelo.getFechaVuelo().equals(fechaElegida))
                System.out.println(count + " - " + vuelo.getAvion().toString());
            count++;
        }
    }

    public void listarVuelosPorDatos(LocalDate fecha, TipoVuelo tipo, Avion avion){
        for(Vuelo vuelo : this.vuelos){
            if((vuelo.getFechaVuelo().equals(fecha)) && (vuelo.getTipoVuelo().equals(tipo)) && (vuelo.getAvion().equals(avion)))
                System.out.println(vuelo.toString());    //Muestro todos los vuelos de esa fecha, con ese origen y destino y ese avion }
        }
    }




}