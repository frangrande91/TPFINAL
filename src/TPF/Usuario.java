package TPF;

public class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;

    public Usuario(){
        this.nombre = "";
        this.apellido = "";
        this.dni = "";
        this.edad = 0;
    }

    public Usuario(String nombre, String apellido, String dni, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }



}
