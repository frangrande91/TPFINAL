package TPF.Modelo;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private ArrayList<Integer> vuelos;
    private String mejorAvion;
    private double totalGastado;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.vuelos = new ArrayList<Integer>();
        this.mejorAvion = "";
        this.totalGastado = 0;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getDni() {
        return dni;
    }
    public int getEdad() {
        return edad;
    }
    public List<Integer> getVuelos() {return vuelos;}
    public String getMejorAvion() {
        return mejorAvion;
    }
    public double getTotalGastado() {
        return totalGastado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setVuelos(ArrayList<Integer> vuelos) {
        this.vuelos = vuelos;
    }
    public void setMejorAvion(String mejorAvion) {
        this.mejorAvion = mejorAvion;
    }
    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }


    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo.getId());
    }

    public void darDeBajaVuelo(Vuelo vuelo) {
        this.vuelos.remove(getIndexVuelo(vuelo.getId()));
    }

    public void mejorAvionContratado(Aerotaxi aerotaxi) {  //Compara el nuevo avion contratado con el atributo mejorAvion
        HashMap<String, Vuelo> vuelosUser = new HashMap<String, Vuelo>();

        if (this.getVuelos().size() > 0) {
            for (Integer id : this.getVuelos()) {
                for (Vuelo aux : aerotaxi.getVuelos()) {
                    if (aux.getId() == id)         //Agrego los vuelos del user a un HashMap auxiliar y depende el tipo de Avion lo identifico con keys(Strings) diferentes
                        if (aerotaxi.buscarAvion(aux.getAvion()) instanceof Gold)
                            vuelosUser.put("Gold", aux);
                        else if(aerotaxi.buscarAvion(aux.getAvion()) instanceof Silver)
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


    public int getIndexVuelo(int numVuelo){ //con el numero de vuelo obtengo el indice del arreglo ((en lista user))
        int index=-1;
        for (int vuelo: this.getVuelos()){
            if(vuelo == numVuelo) {
                index=this.getVuelos().indexOf(vuelo);
            }
        }return index;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}







