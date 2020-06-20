package TPF.Modelo;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private ArrayList<Long> vuelos;
    private String mejorAvion;
    private double totalGastado;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.vuelos = new ArrayList<Long>();
        this.mejorAvion = "";
        this.totalGastado = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Long> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Long> vuelos) {
        this.vuelos = vuelos;
    }

    public String getMejorAvion() {
        return mejorAvion;
    }

    public void setMejorAvion(String mejorAvion) {
        this.mejorAvion = mejorAvion;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }






    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo.getId());
    }

    public void darDeBajaVuelo(Vuelo vuelo) {
        this.vuelos.remove(vuelo.getId());
    }


    public void mejorAvionContratado(List<Vuelo> vuelos) {  //Compara el nuevo avion contratado con el atributo mejorAvion
        HashMap<String, Vuelo> vuelosUser = new HashMap<String, Vuelo>();

        if (this.getVuelos().size() > 0) {
            for (Long id : this.getVuelos()) {
                for (Vuelo aux : vuelos) {
                    if (aux.getId() == id)         //Agrego los vuelos del user a un HashMap auxiliar y depende el tipo de Avion lo identifico con keys(Strings) diferentes
                        if (aux.getAvion() instanceof Gold)
                            vuelosUser.put("Gold", aux);
                        else if(aux.getAvion() instanceof Silver)
                            vuelosUser.put("Silver", aux);
                        else
                            vuelosUser.put("Bronze", aux);
                }
                    if (vuelosUser.containsKey("Gold"))        //Modifico el atributo mejorAvion según el avion que contiene la lista de vuelos del user
                        this.mejorAvion = "Gold";
                    else if (vuelosUser.containsKey("Silver"))
                        this.mejorAvion = "Silver";
                    else if (vuelosUser.containsKey("Bronze"))
                        this.mejorAvion = "Bronze";
                    else
                        this.mejorAvion = "";
            }
        }
        else
            this.mejorAvion = "";
    }



    public void listarVuelosUser() {
        for (Long aux : this.vuelos) {
            System.out.println(aux.toString());
        }
    }


    @Override
    public String toString() {
        return "Cliente {" +
                "Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", DNI: " + dni +
                ", Edad: " + edad +
                ", Mejor avión contratado: " + mejorAvion +
                ", Total gastado en AEROTAXI: $ " + totalGastado +
                '}';
    }

}

        /*
    public int getIndexVuelo(int numVuelo){ //con el numero de vuelo obtengo el indice del arreglo ((en lista user))
        int index=-1;
        for (Vuelo vuelo: this.getVuelos()){
            if(vuelo.getNumeroDeVuelo()==numVuelo) {
                index=this.getVuelos().indexOf(vuelo);
                return index;
            }
        }return index;
    }

     */


