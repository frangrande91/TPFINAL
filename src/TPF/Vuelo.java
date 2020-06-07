package TPF;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {
    private Date fecha;
    private TipoVuelo tipoVuelo;
    private Avion avion;
    private Set<Usuario> pasajeros;
    private int cantPasajeros;

    public Vuelo() {
        this.fecha = new Date();
        this.tipoVuelo = null;
        this.avion = null;
        this.pasajeros = new HashSet<Usuario>();
        this.cantPasajeros = pasajeros.size();
    }

    public Vuelo(TipoVuelo tipoVuelo, Avion avion, int cantPasajeros) {
        this.fecha = new Date();
        this.tipoVuelo = tipoVuelo;
        this.avion = avion;
        this.pasajeros = new HashSet<Usuario>();
        this.cantPasajeros = pasajeros.size();
    }

    public Date getFecha() {
        return fecha;
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

    public double costoTotal(){
       return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + (cantPasajeros * 3500) + (avion.getTarifa());
    }
}
