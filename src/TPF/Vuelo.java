package TPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {
    private LocalDate fechaVuelo;
    private TipoVuelo tipoVuelo;
    private Avion avion;
    private Set<Usuario> pasajeros;
    private int cantPasajeros;
    private int numeroDeVuelo;


    public Vuelo(LocalDate fechaVuelo, TipoVuelo tipoVuelo, Avion avion, int numeroDeVuelo) {
        this.fechaVuelo = fechaVuelo;
        this.tipoVuelo = tipoVuelo;
        this.avion = avion;
        this.pasajeros = new HashSet<Usuario>();
        this.cantPasajeros = pasajeros.size();
        this.numeroDeVuelo = numeroDeVuelo;
    }

    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }

    public TipoVuelo getTipoVuelo() {
        return tipoVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public Set<Usuario> getPasajeros() {
        return pasajeros;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setFechaVuelo(LocalDate fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public int getNumeroDeVuelo() {
        return numeroDeVuelo;
    }
    public void agregarPasajeros(int cant){
        this.cantPasajeros+=cant;
    }    public void quitarPasajeros(int cant){
        this.cantPasajeros-=cant;
    }
    public double costoTotal() {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + (cantPasajeros * 3500) + (avion.getTarifa());
    }

    @Override
    public String toString() {
        return "Vuelo {" +
                "Fecha del vuelo: " + fechaVuelo +
                " - Tipo de vuelo: " + tipoVuelo
                + avion.toString() +
                '}';
    }
}
