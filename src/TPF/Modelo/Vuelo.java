package TPF.Modelo;

import TPF.Persistencia.PersistenciaVuelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Vuelo implements Serializable {

    private int id;
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

    public int getId(){ return id; }
    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }
    public int[]getRecorrido() {
        return recorrido;
    }
    public int getAvion() {
        return avion;
    }
    public int getCantPasajeros() {
        return cantPasajeros;
    }
    public Usuario getCliente() { return cliente; }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }
    public void setRecorrido(int[] recorrido) {
        this.recorrido = recorrido;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAvion(int avion) {
        this.avion = avion;
    }
    public void setFechaVuelo(LocalDate fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }


    public double costoTotal(Avion avion,int[]a) {
        int cantKilometros=0;
        String b= Recorrido.getDistancia(a[0],a[1]);  //retorna distancia en kilometros (string)
        try {
            cantKilometros = Integer.parseInt(b);                    //string a int
        }catch(NumberFormatException e){
        }

        return (cantKilometros * avion.costoPorKm) + (cantPasajeros * 3500) + (avion.obtenerTarifa());
    }

    public double costoConPasajerosNuevos(int aAgregar, Avion avion, int []a) {
        int cantKilometros=0;
        String b= Recorrido.getDistancia(a[0],a[1]);
        try {
            cantKilometros = Integer.parseInt(b);
        }catch(NumberFormatException e){
        }
        return (cantKilometros * avion.costoPorKm) + ((cantPasajeros+aAgregar) * 3500) + (avion.obtenerTarifa());
    }

    public String mostrarVuelo(Avion avion) {
        return "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Vuelo {" +
                "Numero de vuelo: <<<< " + id + " >>>>" +
                ", Fecha del vuelo: " + fechaVuelo +
                " " + Recorrido.recorridoToString(this.recorrido)+  "}\n"
                + avion.toString() +
                "\nCliente: " + cliente.getNombre() + " " + cliente.getApellido() +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nCosto total: $" + costoTotal(avion,recorrido) +
                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

    }
}
