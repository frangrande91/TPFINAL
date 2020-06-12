package TPF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;
    private List<Vuelo> vuelos;

    public Usuario() {
        this.nombre = "";
        this.apellido = "";
        this.dni = 0;
        this.edad = 0;
        this.vuelos = new ArrayList<Vuelo>();
    }

    public Usuario(String nombre, String apellido, int dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.vuelos = new ArrayList<Vuelo>();
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public int getDni() {return dni;}
    public void setDni(int dni) {this.dni = dni;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public List<Vuelo> getVuelos() {return vuelos;}
    public void setVuelos(List<Vuelo> vuelos) {this.vuelos = vuelos;}

    public void agregarVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    public void darDeBajaVuelo(int numVuelo) {
        int i=getIndexVuelo(numVuelo);
       if(i > -1)
            this.getVuelos().remove(i);
            }

    public int getIndexVuelo(int numVuelo){ //con el numero de vuelo obtengo el indice del arreglo ((en lista user))
        int index=-1;
        for (Vuelo vuelo: this.getVuelos()){
            if(vuelo.getNumeroDeVuelo()==numVuelo) {
                index=this.getVuelos().indexOf(vuelo);
                return index;
            }
        }return index;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Nombre: "+ nombre +
                ", Apellido: " + apellido +
                ", DNI: " + dni +
                ", Edad: " + edad +
                '}';
    }
}
