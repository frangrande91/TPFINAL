package TPF;

import java.time.LocalDate;


public class Vuelo {
    public static int i = 1;

    private long id;
    private LocalDate fechaVuelo;
    private TipoVuelo tipoVuelo;
    private Avion avion;
    private Usuario cliente;
    private int cantPasajeros;


    public Vuelo(LocalDate fechaVuelo, TipoVuelo tipoVuelo, Avion avion, Usuario cliente, int cantPasajeros) {
        this.id = i++;
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
    public Avion getAvion() {
        return avion;
    }
    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    public long getNumeroDeVuelo() {
        return id;
    }
    public Usuario getCliente() { return cliente; }
    public void setTipoVuelo(TipoVuelo tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }
    public void setAvion(Avion avion) {
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

    public double costoTotal() {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + (cantPasajeros * 3500) + (avion.getTarifa());
    }
    public double costoConPasajerosNuevos(int aAgregar) {
        return (this.tipoVuelo.getDistancia() * avion.costoPorKm) + ((cantPasajeros+aAgregar) * 3500) + (avion.getTarifa());
    }

    @Override
    public String toString() {
        return "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Vuelo {" +
                "Numero de vuelo: <<<< " + id + " >>>>" +
                ", Fecha del vuelo: " + fechaVuelo +
                " " + tipoVuelo.toString() + "}\n"
                + avion.toString() +
                "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nCosto total: $" + costoTotal() +
                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

    }
}
