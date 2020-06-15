package TPF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;
    private List<Long> vuelos;
    private String mejorAvion;
    private double totalGastado;


    public Usuario(String nombre, String apellido, int dni, int edad) {
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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
        /*if(avion instanceof Gold)
            this.mejorAvion = "Gold";
        else if(avion instanceof Silver){                       // }---->    no funcionaba si cancelas vuelo
            if(!this.mejorAvion.equals("Gold"))
                this.mejorAvion = "Silver";
        }
        else if(this.mejorAvion.equals(""))
            this.mejorAvion = "Bronze";*/
        List<Vuelo> vuelosUser = new ArrayList<Vuelo>();

        if (this.getVuelos().size() > 0) {          //copiar vuelos de user a lista auxiliar
            for (Long id : this.getVuelos()) {
                System.out.println(id);
                for (Vuelo aux : vuelos) {
                    if (aux.getId() == id)
                        vuelosUser.add(aux);
                }
                for (Vuelo vuelo : vuelosUser) {
                    if (vuelo.getAvion() instanceof Gold) {
                        this.mejorAvion = "Gold";
                        break;
                    } else if (vuelo.getAvion() instanceof Silver) {
                        if (!this.mejorAvion.equals("Gold"))
                            this.mejorAvion = "Silver";
                    } else if (this.mejorAvion.equals("") && vuelo.getAvion() instanceof Bronze)
                        this.mejorAvion = "Bronze";
                }
            }
        }
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
                ", Total gastado en AEROTAXI: " + totalGastado +
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


