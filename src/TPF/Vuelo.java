package TPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {
    private int numeroDeVuelo;
    private LocalDate fechaVuelo;
    private TipoVuelo tipoVuelo;
    private Avion avion;
    private Set<Usuario> pasajeros;
    private int cantPasajeros;


    public Vuelo(int numeroDeVuelo, LocalDate fechaVuelo, TipoVuelo tipoVuelo, Avion avion) {
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fechaVuelo;
        this.tipoVuelo = tipoVuelo;
        this.avion = avion;
        pasajeros = new HashSet<Usuario>();
        this.cantPasajeros = 0;
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
    public int getCantPasajeros() {
        return cantPasajeros;
    }
    public int getNumeroDeVuelo() {
        return numeroDeVuelo;
    }
    public Set<Usuario> getPasajeros() { return pasajeros; }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    public void setFechaVuelo(LocalDate fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public void agregarPasajero(Usuario nuevo){
        this.pasajeros.add(nuevo);
        this.cantPasajeros++;
    }

    public void quitarPasajero(int dni){
        for(Usuario user : pasajeros){
            if(dni == user.getDni())
                pasajeros.remove(user);
        }
    }

    public double costoTotal(int cantAcomp) {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + (cantAcomp * 3500) + (avion.getTarifa());
    }

    @Override
    public String toString() {
        return "Vuelo {" +
                "Fecha del vuelo: " + fechaVuelo +
                " - Tipo de vuelo: " + tipoVuelo + ", "
                + avion.toString() +
                '}';
    }
}
