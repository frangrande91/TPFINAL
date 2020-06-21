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
    private int[]recorrido;
    private int avion;
    private Usuario cliente;
    private int cantPasajeros;

    public Vuelo() {
    }

    public Vuelo(LocalDate fechaVuelo, int[]recorrido, int avion, Usuario cliente, int cantPasajeros) {
        this.id = PersistenciaVuelos.cantidadVuelos() + 1;
        this.fechaVuelo = fechaVuelo;
        this.recorrido = recorrido;
        this.avion = avion;
        this.cliente = cliente;
        this.cantPasajeros = cantPasajeros;
    }

    public long getId(){ return id; }
    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }

    public int[]getRecorrido() {
        return recorrido;
    }
    public void setRecorrido(int[] recorrido) {
        this.recorrido = recorrido;
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

    public double costoTotal(Avion avion,int[]a) {
        int i=0;
        String b=Recorridos.getDistancia(a[0],a[1]);
        try {
            i = Integer.parseInt(b);
        }catch(NumberFormatException e){
        }

        return (i * avion.costoPorKm + (cantPasajeros * 3500) + (avion.obtenerTarifa()));
    }
    public double costoConPasajerosNuevos(int aAgregar, Avion avion, int []a) {
        int i=0;
        String b=Recorridos.getDistancia(a[0],a[1]);
        try {
            i = Integer.parseInt(b);
        }catch(NumberFormatException e){
        }
        return (i * avion.costoPorKm) + ((cantPasajeros+aAgregar) * 3500) + (avion.obtenerTarifa());
    }

    public String mostrarVuelo(Avion avion) {
        return "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Vuelo {" +
                "Numero de vuelo: <<<< " + id + " >>>>" +
                ", Fecha del vuelo: " + fechaVuelo +
                " " +Recorridos.recorridoToString(this.recorrido)+  "}\n"
                + avion.toString() +
                "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nCosto total: $" + costoTotal(avion,recorrido) +
                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

    }
}
