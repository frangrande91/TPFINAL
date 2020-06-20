package TPF.Modelo;

import TPF.Modelo.Avion;
import TPF.Modelo.TipoVuelo;
import TPF.Modelo.Usuario;
import TPF.Persistencia.PersistenciaVuelos;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;

public class Vuelo implements Serializable {
//    public int i = 1;

    private long id;
    private LocalDate fechaVuelo;
    private TipoVuelo tipoVuelo;
    private int avion;
    private Usuario cliente;
    private int cantPasajeros;

    public Vuelo() {
    }

    public Vuelo(LocalDate fechaVuelo, TipoVuelo tipoVuelo, int avion, Usuario cliente, int cantPasajeros) {
        this.id = PersistenciaVuelos.cantidadVuelos() + 1;
        this.fechaVuelo = fechaVuelo;
        this.tipoVuelo = tipoVuelo;
        this.avion = avion;
        this.cliente = cliente;
        this.cantPasajeros = cantPasajeros;
    }

    public long getId(){ return id; }
    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }
    public TipoVuelo getTipoVuelo() {
        return tipoVuelo;
    }

    public int getAvion() {
        return avion;
    }
    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    public void setId(long id) {
        this.id = id;
    }

      public Usuario getCliente() { return cliente; }
    public void setTipoVuelo(TipoVuelo tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }
    public void setAvion(int avion) {
        this.avion = avion;
    }
    public void setFechaVuelo(LocalDate fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public void agregarPasajero(Usuario nuevo){
        this.cantPasajeros++;
    }
    public void quitarPasajero(int dni){
        this.cantPasajeros--;
    }

    public double costoTotal(Avion avion) {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + (cantPasajeros * 3500) + (avion.obtenerTarifa());
    }
    public double costoConPasajerosNuevos(int aAgregar, Avion avion) {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + ((cantPasajeros+aAgregar) * 3500) + (avion.obtenerTarifa());
    }

    public String mostrarVuelo(Avion avion) {
        return "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Vuelo {" +
                "Numero de vuelo: <<<< " + id + " >>>>" +
                ", Fecha del vuelo: " + fechaVuelo +
                " " + tipoVuelo.toString() + "}\n"
                + avion.toString() +
                "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nCosto total: $" + costoTotal(avion) +
                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

    }
}
