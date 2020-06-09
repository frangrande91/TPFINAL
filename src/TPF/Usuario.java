package TPF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private List<Vuelo> vuelos;

    public Usuario() {
        this.nombre = "";
        this.apellido = "";
        this.dni = "";
        this.edad = 0;
        this.vuelos = new ArrayList<Vuelo>();
    }

    public Usuario(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.vuelos = new ArrayList<Vuelo>();
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


    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }   public void cancelarVuelo(int index) {
        int i=getIndexVuelo(index);
        this.vuelos.remove(i);
    }

    public int getIndexVuelo(int numVuelo){
        int index=-1;
        for (Vuelo vuelo:this.vuelos){
            System.out.println("numvuelo= " +vuelo.getNumeroDeVuelo());
            if(vuelo.getNumeroDeVuelo()==numVuelo)
                index=this.vuelos.indexOf(vuelo);
        }return index;
    }
}
